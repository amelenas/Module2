package com.epam.esm.dao;

import com.epam.esm.dao.entity.Certificate;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code CertificateDao} describes operations for working with Certificate database table.
 */
public interface CertificateDao {

    /**
     * @param certificate entity used to create Certificate
     * @return created certificate
     */
    Certificate createCertificate(Certificate certificate);

    /**
     * Returns {@code true} if certificate was deleted.
     * @return {@code false} if if certificate wasn't deleted.
     */
    boolean deleteCertificate(long id);

    /**
     * @return a list of all Certificates entities.
     */
    List<Certificate> findCertificates();

    /**
     * @return certificate entity that was found by @param id
     */
    Certificate findCertificate(long id);

    /**
     *  @param certificate entity used to update Certificate by @param id
     */
    void updateCertificate(long id, Certificate certificate);

}
