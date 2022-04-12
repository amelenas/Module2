package impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.impl.CertificateDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class CertificateDaoImplTest {
    Certificate certificate = new Certificate(2, "Music store",
            "Music store description", 200.0, 12, "2022-04-03", "2022-04-03");
    Certificate certificateCosmetics = new Certificate(
            1, "Cosmetics", "Cosmetics description", 100.0, 12, "2022-04-03", "2022-04-03");
    @Autowired
    private CertificateDaoImpl certificateDao;

    @Test
    void getCertificates() {
        assertEquals(5, certificateDao.findCertificates().size());
    }

    @Test
    void getCertificate() {
        ArrayList<String>tagNames = new ArrayList<>();
        tagNames.add("Music store");
        certificate.setTagNames(tagNames);
        Assertions.assertEquals(certificateDao.findCertificate(2), certificate);
    }

    @Test
    void createCertificate()  {
        List<String> tagList = new ArrayList<>();
        tagList.add("Sport");
        Certificate certificate = new Certificate("Gym",
                "1 hour of gym", 200.0, 12);
        certificate.setTagNames(tagList);
        Certificate certificateFromDB = certificateDao.createCertificate(certificate);
        certificate.setId(certificateFromDB.getId());
        certificate.setCreateDate(certificateFromDB.getCreateDate());
        certificate.setLastUpdateDate(certificateFromDB.getLastUpdateDate());
        certificate.setTagNames(certificateFromDB.getTagNames());
        assertEquals(certificate, certificateFromDB);
        certificateDao.deleteCertificate(6);
    }

    @Test
    void deleteCertificate(){
        assertTrue(certificateDao.deleteCertificate(1));
    }

    @Test
    void updateCertificates() {
        String date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Certificate certificate = new Certificate(1, "Cosmetics",
                "Cosmetics", 50.0, 12, "2022-04-03", date);
        certificateDao.updateCertificate(1, certificate);
        Certificate certificateFromDB = certificateDao.findCertificate(1);
        certificate.setCreateDate(certificateFromDB.getCreateDate());
        certificate.setLastUpdateDate(certificateFromDB.getLastUpdateDate());
        ArrayList<String>tagNames = new ArrayList<>();
        tagNames.add("Cosmetics");
        certificate.setTagNames(tagNames);

        assertEquals(certificateDao.findCertificate(1), certificate);
    }
}