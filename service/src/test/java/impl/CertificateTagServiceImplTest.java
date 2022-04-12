package impl;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.dao.impl.CertificateTagDaoImpl;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.impl.CertificateTagServiceImpl;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CertificateTagServiceImplTest {
    private static final Tag tag = new Tag(5, "Appliances");
    @Mock
    private TagServiceImpl tagServiceImpl;
    @Mock
    private CertificateTagDaoImpl certificateTagDaoImpl;
    @InjectMocks
    private CertificateTagServiceImpl certificateTagService;

    @Test
    void findCertificatesByTag() throws ServiceException {
        Mockito.when(tagServiceImpl.isTagExist(tag.getId())).thenReturn(true);
        certificateTagService.findCertificatesByTag(tag.getId());
        Mockito.verify(certificateTagDaoImpl).findCertificatesByTag(tag.getId());
    }

    @Test
    void findCertificatesByTagException() {
        assertThrows(ServiceException.class, () -> certificateTagService.findCertificatesByTag(tag.getId()));

    }

    @Test
    void findCertificatesByPartName() throws ServiceException {
        certificateTagService.findCertificatesByPartName(tag.getName());
        Mockito.verify(certificateTagDaoImpl).findCertificatesByPartName(tag.getName());
    }

    @Test
    void findCertificatesByPartNameException() {
        assertThrows(ServiceException.class, () -> certificateTagService.findCertificatesByPartName(null));
    }

    @Test
    void findCertificatesSorted() throws ServiceException, DaoException {
        String param = "name";
        String direction = "asc";
        certificateTagService.findCertificatesSorted(param, direction);
        Mockito.verify(certificateTagDaoImpl).findCertificatesSorted(param, direction);   }

    @Test
    void findCertificatesSortedException() {
        String param = null;
        String direction = null;
        assertThrows(ServiceException.class, () -> certificateTagService.findCertificatesSorted(param, direction));   }
}