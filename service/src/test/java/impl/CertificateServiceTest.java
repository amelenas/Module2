package impl;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.impl.CertificateDaoImpl;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class CertificateServiceTest {
    Certificate certificate = new Certificate(2, "Pet Store",
            "Pet Store", 200.0, 12, "2022-04-03", "2022-04-03");
    Certificate certificateNotValid = new Certificate( null,
            null, 0, 0 );
    @Mock
    private CertificateDaoImpl certificateDao = Mockito.mock(CertificateDaoImpl.class);
    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Test
    void createCertificatesTrue() throws ServiceException {
        certificateService.createCertificate(certificate);
        Mockito.verify(certificateDao).createCertificate(certificate);
    }

    @Test
    void createCertificatesException() {
       Assertions.assertThrows(ServiceException.class, () -> certificateService.createCertificate(certificateNotValid));
    }

    @Test
    void updateCertificate() throws ServiceException {
        certificateService.updateCertificate(2, certificate);
        Mockito.verify(certificateDao).updateCertificate(2, certificate);
    }

    @Test
    void findCertificates () {
        certificateService.findCertificates();
        Mockito.verify(certificateDao).findCertificates();
    }

    @Test
    void deleteCertificate() throws ServiceException {
        certificateService.deleteCertificate(2);
        Mockito.verify(certificateDao).deleteCertificate(certificate.getId());
    }

    @Test
    void deleteCertificateException(){
        assertThrows(ServiceException.class, () -> certificateService.deleteCertificate(certificateNotValid.getId()));
    }

    @Test
    void findCertificate () throws ServiceException {
        Mockito.when(certificateService.findCertificate(2)).thenReturn(certificate);
        certificateService.findCertificate(2);
        Mockito.verify(certificateDao).findCertificate(2);
    }

    @Test
    void isCertificateExistException() {
        assertThrows(ServiceException.class, () -> certificateService.findCertificate(0));
    }
}
