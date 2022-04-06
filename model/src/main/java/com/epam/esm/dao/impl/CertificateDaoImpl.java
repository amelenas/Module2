package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.impl.mapper.CertificateMapper;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    private static final String CREATE_CERTIFICATE = "INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CERTIFICATE = "DELETE FROM gift_certificate WHERE id=?";
    private static final String GET_ALL_CERTIFICATES = "SELECT gift_certificate.*, group_concat(tag.name) FROM gift_certificate JOIN gift_certificate_tag ON gift_certificate_tag.gift_certificate_id = gift_certificate.id JOIN tag ON tag.id = gift_certificate_tag.tag_id GROUP BY gift_certificate.id";
    private static final String GET_CERTIFICATE_BY_ID = "SELECT gift_certificate.*, group_concat(tag.name) FROM gift_certificate JOIN gift_certificate_tag ON gift_certificate_tag.gift_certificate_id = gift_certificate.id JOIN tag ON gift_certificate_tag.tag_id = tag.id WHERE gift_certificate.id = ?";
    private static final String UPDATE_CERTIFICATE = "UPDATE gift_certificate SET name = ?, price = ?, description = ?, last_update_date = ? WHERE id = ?";
    private static final String INSERT_INTO_CERTIFICATE_TAG = "INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id) VALUES (?, (SELECT tag.id FROM tag WHERE tag.name = ?))";

    private final JdbcTemplate jdbcTemplate;
    private final TagDao tagDao;

    @Autowired
    public CertificateDaoImpl(TagDao tagDao, JdbcTemplate jdbcTemplate) {
        this.tagDao = tagDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Optional<Certificate> createCertificate(Certificate certificate) throws DaoException {
        String date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE_CERTIFICATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, certificate.getName());
            statement.setString(2, certificate.getDescription());
            statement.setDouble(3, certificate.getPrice());
            statement.setInt(4, certificate.getDuration());
            statement.setString(5, date);
            statement.setString(6, date);
            return statement;
        }, keyHolder);
        for (String string : certificate.getTagNames()) {
            if (!tagDao.isTagExist(string)) {
                tagDao.createTag(string);
            }
            jdbcTemplate.update(INSERT_INTO_CERTIFICATE_TAG, Objects.requireNonNull(keyHolder.getKey()).intValue(), string);
        }
        return findCertificate(keyHolder.getKey().longValue());
    }

    @Override
    public boolean deleteCertificate(long id){
        try {
            jdbcTemplate.update(DELETE_CERTIFICATE, id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Optional<Certificate>> findCertificates() {
        return jdbcTemplate.query(GET_ALL_CERTIFICATES, new CertificateMapper());
    }

    @Override
    public Optional<Certificate> findCertificate(long id) {
        return jdbcTemplate.queryForObject(GET_CERTIFICATE_BY_ID, new CertificateMapper(), id);
    }

    @Override
    public void updateCertificate(long id, Certificate certificate) {
        String date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        jdbcTemplate.update(UPDATE_CERTIFICATE, certificate.getName(), certificate.getPrice(), certificate.getDescription(), date, id);
    }
}
