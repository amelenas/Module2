package impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.impl.CertificateDaoImpl;
import com.epam.esm.dao.impl.CertificateTagDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
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

    List<Certificate> expected = new ArrayList<>();
    ArrayList<String> tagNames = new ArrayList<>();

    @Autowired
    private CertificateTagDaoImpl certificateTagDaoImpl;

    @Autowired
    CertificateDaoImpl certificateDaoImpl;


    @Test
    void findCertificatesByTag() {
        tagNames.add("Food");
        certificateSushi.setTagNames(tagNames);
        certificatePizza.setTagNames(tagNames);
        expected.add(certificateSushi);
        expected.add(certificatePizza);
        List<Certificate> result = certificateTagDaoImpl.findCertificatesByTag(4);
        assertEquals(expected, result);
    }

    @Test
    void findCertificatesByName() {
        tagNames.add("Food");
        certificateSushi.setTagNames(tagNames);
        expected.add(certificateSushi);
        List<Certificate> result = certificateTagDaoImpl.findCertificatesByPartName("Su");
        assertEquals(expected, result);
    }
}