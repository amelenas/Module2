package com.epam.esm.dao.impl.mapper;

import com.epam.esm.dao.entity.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CertificateMapper implements RowMapper<Certificate> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";
    private static final String CREATE_DATE = "create_date";
    private static final String LAST_UPDATE_DATE = "last_update_date";

    @Override
    public Certificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(resultSet.getInt(ID));
        certificate.setName(resultSet.getString(NAME));
        certificate.setDescription(resultSet.getString(DESCRIPTION));
        certificate.setPrice(resultSet.getDouble(PRICE));
        certificate.setDuration(resultSet.getInt(DURATION));
        certificate.setCreateDate(resultSet.getString(CREATE_DATE));
        certificate.setLastUpdateDate(resultSet.getString(LAST_UPDATE_DATE));
        String tags = resultSet.getString(8);
        if (tags!=null){
            List<String> tagList = Arrays.asList((resultSet.getString(8)).trim().split(","));
            certificate.setTagNames(tagList);}
        return certificate;
    }
}