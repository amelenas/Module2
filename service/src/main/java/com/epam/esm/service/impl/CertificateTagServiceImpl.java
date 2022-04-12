package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateTagDao;
import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateTagServiceImpl implements CertificateTagService {
    private final CertificateTagDao certificateTagDoa;
    private final TagService tagService;

    @Autowired
    public CertificateTagServiceImpl(CertificateTagDao certificateTagDoa, TagService tagService) {
        this.certificateTagDoa = certificateTagDoa;
        this.tagService = tagService;
    }

    @Override
    public List<Certificate> findCertificatesByTag(long tagId) throws ServiceException {
        if (!Validator.isGreaterZero(tagId) || !tagService.isTagExist(tagId)) {
            throw new ServiceException("Tag with such doesn't exist id: " + tagId);
        }
        return certificateTagDoa.findCertificatesByTag(tagId);
    }

    @Override
    public List<Certificate> findCertificatesByPartName(String name) throws ServiceException {
        if (!Validator.validateName(name)) {
            throw new ServiceException("Certificate with such name doesn't exist, name: " + name);
        }
        return certificateTagDoa.findCertificatesByPartName(name);

    }

    @Override
    public List<Certificate> findCertificatesSorted(String sortParam, String direction) throws ServiceException {
        if (sortParam == null || sortParam.isEmpty() ||
                direction == null || direction.isEmpty()) {
            throw new ServiceException("Such SortParam or direction doesn't exist ");
        }
        try {
            return certificateTagDoa.findCertificatesSorted(sortParam, direction);
        } catch (DaoException e) {
            throw new ServiceException("Something went wrong while sorting list ");
        }
    }
}

