package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDao certificateDao;

    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    @Transactional(transactionManager = "transactionManager")
    public Certificate createCertificate(Certificate certificate) throws ServiceException {
        if (!Validator.isValuePresent(certificate)) {
            throw new ServiceException("Some of parameters are null");
        }
        return certificateDao.createCertificate(certificate);
    }

    @Override
    public boolean deleteCertificate(long id) throws ServiceException {
        if (!Validator.isGreaterZero(id)) {
            throw new ServiceException("No such certificate with id:" + id);
        }
        return certificateDao.deleteCertificate(id);
    }

    @Override
    public void updateCertificate(long id, Certificate certificate) throws ServiceException {
        if (!Validator.isGreaterZero(id) && !Validator.isValuePresent(certificate)) {
            throw new ServiceException("No such certificate with id:" + id + " or parameters are null ");
        }
        certificateDao.updateCertificate(id, certificate);
    }

    @Override
    public Certificate findCertificate(long id) throws ServiceException {
        if (!Validator.isGreaterZero(id)) {
            throw new ServiceException("No such certificate with id:" + id);
        }
        return certificateDao.findCertificate(id);
    }

    @Override
    public List<Certificate> findCertificates() {
        return certificateDao.findCertificates();
    }
}
