package com.epam.esm.dao.impl.mapper;

import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TagMapper implements RowMapper<Optional<Tag>> {
    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Optional<Tag> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getInt(ID));
        tag.setName(rs.getString(NAME));
        return Optional.of(tag);
    }
}
