package com.epam.esm.dao;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.exception.DaoException;

import java.util.List;
/**
 * Interface {@code CertificateTagDao} describes operations for working with CertificateTag database table.
 */
public interface CertificateTagDao {

    /**
     * @param tagId is used to find all certificates with current tag
     * @return a list certificates
     */
    List<Certificate> findCertificatesByTag(long tagId);

    /**
     * @param name is used to find certificates with current name or part of name
     * @return a list certificates
     */
    List<Certificate> findCertificatesByPartName(String name);

    /**
     * @param direction is used to determine the direction of sorting (ASC or DESC)
     * @param sortParam is used to determine by which database table column name the list will be sorted
     *
     * @return a list certificates
     * @throws DaoException in a dao layer
     */
    List<Certificate> findCertificatesSorted(String sortParam, String direction) throws DaoException;

}
