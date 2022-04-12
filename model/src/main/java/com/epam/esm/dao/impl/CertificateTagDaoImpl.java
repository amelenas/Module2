package com.epam.esm.dao.impl;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.dao.impl.mapper.CertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateTagDaoImpl implements com.epam.esm.dao.CertificateTagDao {
    private static final String FIND_CERTIFICATE_BY_CONDITION = "SELECT gift_certificate.*, group_concat(tag.name) FROM gift_certificate JOIN gift_certificate_tag ON gift_certificate_tag.gift_certificate_id = gift_certificate.id JOIN tag ON tag.id = gift_certificate_tag.tag_id WHERE %s ";
    private static final String TAG_ID_GROUP_BY_CERTIFICATE_ID = "gift_certificate_tag.tag_id = ? GROUP BY gift_certificate.id";
    private static final String NAME_LIKE = "gift_certificate.name LIKE '%' ? '%' GROUP BY gift_certificate.id";
    private static final String FIND_CERTIFICATE_WHERE_CONDITIONS = "SELECT gift_certificate.*, group_concat(tag.name) FROM gift_certificate JOIN gift_certificate_tag ON gift_certificate_tag.gift_certificate_id = gift_certificate.id JOIN tag ON tag.id = gift_certificate_tag.tag_id GROUP BY gift_certificate.id";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateTagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> findCertificatesByTag(long tagId) {
        return jdbcTemplate.query(String.format(FIND_CERTIFICATE_BY_CONDITION, TAG_ID_GROUP_BY_CERTIFICATE_ID), new CertificateMapper(), tagId);
    }

    @Override
    public List<Certificate> findCertificatesByPartName(String name) {
        return jdbcTemplate.query(String.format(FIND_CERTIFICATE_BY_CONDITION, NAME_LIKE), new CertificateMapper(), name);
    }

    public List<Certificate> findCertificatesSorted(String sortParam, String direction) throws DaoException {
        String orderBy = FIND_CERTIFICATE_WHERE_CONDITIONS + " ORDER BY %s %s";
        try {
            return jdbcTemplate.query(String.format(orderBy, sortParam, direction), new CertificateMapper());
        } catch (BadSqlGrammarException e) {
            throw new DaoException(e.getMostSpecificCause().toString());
        }
    }
}
