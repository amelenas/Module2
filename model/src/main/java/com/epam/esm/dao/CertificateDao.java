package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CertificateDao {
    Optional<Certificate> createCertificate(Certificate certificate) throws DaoException;
    boolean deleteCertificate(long id);
    List<Optional<Certificate>> findCertificates();
    Optional<Certificate> findCertificate(long id);
    void updateCertificate(long id, Certificate certificate);

}

