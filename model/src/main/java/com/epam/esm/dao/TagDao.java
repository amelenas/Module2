package com.epam.esm.dao;

import com.epam.esm.dao.entity.Tag;

import java.util.List;
/**
 * Interface {@code TagDao} describes operations for working with Tag database table.
 */
public interface TagDao {
    /**
     * @return a list of all Tag entities.
     */
    List<Tag> findTags();

    /**
     * @return Tag entity that was found by @param id
     */
    Tag findTag(long id);

    /**
     * @param name used to create new tag
     */
    void createTag(String name);

    /**
     * @param id is used to delete tag.
     */
    void deleteTag(long id);

    /**
     * Returns {@code true} if tag with @param id exist.
     * @return {@code false} if tag with @param id doesn't exist.
     */
    boolean isTagExist(long id);

    /**
     * Returns {@code true} if tag with @param name exist.
     * @return {@code false} if tag with @param name doesn't exist.
     */
    boolean isTagExist(String name);
}