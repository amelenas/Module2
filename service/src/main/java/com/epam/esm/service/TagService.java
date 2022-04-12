package com.epam.esm.service;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

public interface TagService {

    /**
     * @return a list of all Tag entities.
     */
    List<Tag> findTags();

    /**
     * @return Tag entity that was found by @param id
     * @throws ServiceException in Service layer
     */
    Tag findTag(long id) throws ServiceException;

    /**
     * @param name used to create new tag
     * @throws ServiceException in Service layer
     */
    void createTag(String name) throws ServiceException;

    /**
     * @param id is used to delete tag.
     * @throws ServiceException in Service layer
     */
    void deleteTag(long id) throws ServiceException;

    /**
     * Returns {@code true} if tag with @param id exist.
     * @return {@code false} if tag with @param id doesn't exist.
     * @throws ServiceException in Service layer
     */
    boolean isTagExist(long id) throws ServiceException;

    /**
     * Returns {@code true} if tag with @param name exist.
     * @return {@code false} if tag with @param name doesn't exist.
     * @throws ServiceException in Service layer
     */
    boolean isTagExist(String name) throws ServiceException;

}
