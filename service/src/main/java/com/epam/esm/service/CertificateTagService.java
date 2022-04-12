package com.epam.esm.service;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

public interface CertificateTagService {

    /**
     * @param tagId is used to find all certificates with current tag
     * @return a list certificates
     * @throws ServiceException in Service layer
     */
    List<Certificate> findCertificatesByTag(long tagId) throws ServiceException;

    /**
     * @param name is used to find certificates with current name or part of name
     * @return a list certificates
     * @throws ServiceException in Service layer
     */
    List<Certificate> findCertificatesByPartName(String name) throws ServiceException;

    /**
     * @param direction is used to determine the direction of sorting (ASC or DESC)
     * @param sortParam is used to determine by which database table column name the list will be sorted
     *
     * @return a list certificates
     * @throws ServiceException in Service layer
     */
    List<Certificate> findCertificatesSorted(String sortParam, String direction) throws ServiceException;

}
