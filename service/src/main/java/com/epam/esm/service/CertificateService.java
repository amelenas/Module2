package com.epam.esm.service;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

public interface CertificateService {

    /**
     * @param certificate entity used to create Certificate
     * @return created certificate
     * @throws ServiceException in Service layer
     */
    Certificate createCertificate(Certificate certificate) throws ServiceException;

    /**
     * Returns {@code true} if certificate was deleted.
     * @return {@code false} if if certificate wasn't deleted.
     * @throws ServiceException in Service layer
     */
    boolean deleteCertificate(long id) throws ServiceException;

    /**
     * @return a list of all Certificates entities.
     * @throws ServiceException in Service layer
     */
    List<Certificate> findCertificates () throws ServiceException;

    /**
     * @return certificate entity that was found by @param id
     * @throws ServiceException in Service layer
     */
    Certificate findCertificate(long id) throws ServiceException;

    /**
     * @param certificate entity used to update Certificate by @param id
     * @throws ServiceException in Service layer
     */
    void updateCertificate(long id, Certificate certificate) throws ServiceException;
}