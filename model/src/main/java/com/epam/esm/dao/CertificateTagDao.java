package com.epam.esm.dao;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CertificateTagDao {
    List<Optional<Certificate>> findCertificatesByTag(long id);
    List<Optional<Certificate>> findCertificatesByName(String name);
    List<Optional<Certificate>> findCertificatesSorted(String sortParam, String direction) throws DaoException;

}
