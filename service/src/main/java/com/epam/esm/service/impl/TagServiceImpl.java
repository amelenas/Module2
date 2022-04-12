package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public void createTag(String name) throws ServiceException {
        if(!Validator.validateName(name)){
            throw new ServiceException("There is no such tag with name:" + name);
        }
        tagDao.createTag(name);
    }

    @Override
    public void deleteTag(long id) throws ServiceException {
        if (!Validator.isGreaterZero(id) || !isTagExist(id)) {
            throw new ServiceException("There is no such tag with id:" + id);
        }
        tagDao.deleteTag(id);
    }

    @Override
    public List<Tag> findTags() {
        return tagDao.findTags();
    }

    @Override
    public Tag findTag(long id) throws ServiceException {
        if (!Validator.isGreaterZero(id) || !isTagExist(id)) {
            throw new ServiceException("There is no such tag with id:" + id);
        }
        return tagDao.findTag(id);
    }

    @Override
    public boolean isTagExist(long id) throws ServiceException {
        if (!Validator.isGreaterZero(id)) {
            throw new ServiceException("There is no such tag with id:" + id);
        }
        return tagDao.isTagExist(id);
    }

    @Override
    public boolean isTagExist(String name) throws ServiceException {
        if(!Validator.validateName(name)){
            throw new ServiceException("There is no such tag with name:" + name);
        }
        return tagDao.isTagExist(name);
    }
}

