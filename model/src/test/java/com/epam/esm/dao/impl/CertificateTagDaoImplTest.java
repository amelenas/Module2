package com.epam.esm.dao.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.DaoException;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
class CertificateTagDaoImplTest {
    Certificate certificateCosmetics = new Certificate(
            1, "Cosmetics", "Cosmetics description", 100.0, 12, "2022-04-03", "2022-04-03");
    Certificate certificateMusic = new Certificate(
            2, "Music store", "Music store description", 200.0, 12, "2022-04-03", "2022-04-03");
    Certificate certificateFitness = new Certificate(
            3, "Fitness", "Fitness description", 90.0, 3, "2022-04-03", "2022-04-03");
    Certificate certificateSushi = new Certificate(
            4, "Sushi", "Sushi store description", 50.0, 3, "2022-04-03", "2022-04-03");
    Certificate certificatePizza = new Certificate(
            5, "Pizza", "Pizza store description", 30.0, 3, "2022-04-03", "2022-04-03");

    List<Optional<Certificate>> expected = new ArrayList<>();
    ArrayList<String> tagNames = new ArrayList<>();

    @Autowired
    private CertificateTagDaoImpl certificateTagDao;

    @Autowired
    CertificateDaoImpl certificateDaoImpl;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void beforeTest() throws FileNotFoundException, SQLException {
        RunScript.execute(dataSource.getConnection(), new FileReader(new File("src/test/resources/certificates_script.sql").getAbsolutePath()));

    }

    @Test
    void findCertificatesByTag() {
        tagNames.add("Food");
        certificateSushi.setTagNames(tagNames);
        certificatePizza.setTagNames(tagNames);
        expected.add(Optional.of(certificateSushi));
        expected.add(Optional.of(certificatePizza));
        List<Optional<Certificate>> result = certificateTagDao.findCertificatesByTag(4);
        assertEquals(expected, result);
    }

    @Test
    void findCertificatesByName() {
        tagNames.add("Food");
        certificateSushi.setTagNames(tagNames);
        expected.add(Optional.of(certificateSushi));
        List<Optional<Certificate>> result = certificateTagDao.findCertificatesByName("Sushi");
        assertEquals(expected, result);
    }

    @Test
    void findCertificatesSorted() throws DaoException {
        ArrayList<String> tagNamesCosmetics = new ArrayList<>();
        tagNamesCosmetics.add("Cosmetics");
        certificateCosmetics.setTagNames(tagNamesCosmetics);
        expected.add(Optional.of(certificateCosmetics));

        ArrayList<String> tagNamesFitness = new ArrayList<>();
        tagNamesFitness.add("Fitness");
        certificateFitness.setTagNames(tagNamesFitness);
        expected.add(Optional.of(certificateFitness));

        ArrayList<String> tagNamesMusic = new ArrayList<>();
        tagNamesMusic.add("Music store");
        certificateMusic.setTagNames(tagNamesMusic);
        expected.add(Optional.of(certificateMusic));

        ArrayList<String> tagNamesPizza = new ArrayList<>();
        tagNamesPizza.add("Food");
        certificatePizza.setTagNames(tagNamesPizza);
        expected.add(Optional.of(certificatePizza));

        ArrayList<String> tagNamesSushi = new ArrayList<>();
        tagNamesSushi.add("Food");
        certificateSushi.setTagNames(tagNamesSushi);
        expected.add(Optional.of(certificateSushi));
        assertEquals(expected, certificateTagDao.findCertificatesSorted("name", "asc"));
    }
}