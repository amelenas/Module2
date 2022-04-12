package impl;

import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    Tag tag = new Tag(1, "Appliances");

    @Mock
    TagDaoImpl tagDao = Mockito.mock(TagDaoImpl.class);
    @InjectMocks
    TagServiceImpl tagServiceImpl;

    @Test
    void createTag() throws ServiceException {
        tagServiceImpl.createTag(tag.getName());
        Mockito.verify(tagDao).createTag(tag.getName());
    }
    @Test
    void createTagException(){
        assertThrows(ServiceException.class, () -> tagServiceImpl.createTag(null));
    }

    @Test
    void deleteTag() throws ServiceException {
        Mockito.when(tagServiceImpl.isTagExist(tag.getId())).thenReturn(true);
        tagServiceImpl.deleteTag(tag.getId());
        Mockito.verify(tagDao).deleteTag(tag.getId());
    }
    @Test
    void deleteTagException() {
        assertThrows(ServiceException.class, () -> tagServiceImpl.deleteTag(0));
    }

    @Test
    void findTags() {
        tagServiceImpl.findTags();
        Mockito.verify(tagDao).findTags();
    }

    @Test
    void findTag()  {
        assertThrows(ServiceException.class, () -> tagServiceImpl.findTag(0));
    }
    @Test
    void findTagException() throws ServiceException {
        Mockito.when(tagServiceImpl.isTagExist(tag.getId())).thenReturn(true);
        tagServiceImpl.findTag(tag.getId());
        Mockito.verify(tagDao).findTag(tag.getId());
    }
    @Test
    void isTagExist() throws ServiceException {
        tagServiceImpl.isTagExist(tag.getId());
        Mockito.verify(tagDao).isTagExist(tag.getId());
    }
    @Test
    void isTagExistName() throws ServiceException {
        tagServiceImpl.isTagExist(tag.getName());
        Mockito.verify(tagDao).isTagExist(tag.getName());
    }
    @Test
    void isTagExistException() {
        assertThrows(ServiceException.class, () -> tagServiceImpl.isTagExist(0));
    }

    @Test
    void testIsTagExist() {
        assertThrows(ServiceException.class, () -> tagServiceImpl.isTagExist(null));
    }
    @Test
    void isTagExistNameException() {
        assertThrows(ServiceException.class, () -> tagServiceImpl.isTagExist(""));
    }

}