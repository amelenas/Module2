package com.epam.esm.dao.impl;


import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.impl.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao {
    private static final String CREATE_TAG = "INSERT INTO tag (name) VALUE (?)";
    private static final String NAME_FROM_TAG = "SELECT * FROM tag";
    private static final String NAME_FROM_TAG_WHERE_ID = "SELECT * FROM tag WHERE id = ?";
    private static final String FIND_TAG_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    private static final String DELETE_TAG_BY_ID = "DELETE FROM tag WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createTag(String name)  {
        jdbcTemplate.update(CREATE_TAG, name);
    }

    @Override
    public List<Tag> findTags()  {
        return jdbcTemplate.query(NAME_FROM_TAG, new TagMapper());
    }

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag findTag(long id) {
        return jdbcTemplate.queryForObject(NAME_FROM_TAG_WHERE_ID, new TagMapper(), id);
    }

    @Override
    public void deleteTag(long id) {
        jdbcTemplate.update(DELETE_TAG_BY_ID, id);
    }

    @Override
    public boolean isTagExist(long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(NAME_FROM_TAG_WHERE_ID, new TagMapper(), id)).isPresent();
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean isTagExist(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_TAG_BY_NAME, new TagMapper(), name)).isPresent();
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
