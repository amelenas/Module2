package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    List<Optional<Tag>> findTags();
    Optional<Tag> findTag(long id);
    void createTag(String name);
    void deleteTag(long id);
    boolean isTagExist(long id);
    boolean isTagExist(String name);
}
