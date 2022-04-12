package impl;

import com.epam.esm.dao.config.TestConfig;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.impl.CertificateTagDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class TagDaoImplTest {
    Tag tagCosmetics = new Tag(1, "Cosmetics");
    Tag tagMusic = new Tag(2, "Music store");
    Tag tagFitness = new Tag(3, "Fitness");
    Tag tagFood = new Tag(4, "Food");

    @Autowired
    private TagDaoImpl tagDao;
    @Autowired
    CertificateTagDaoImpl certificateDao;
    @Test
    void findTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(tagCosmetics);
        tags.add(tagMusic);
        tags.add(tagFitness);
        tags.add(tagFood);

        assertArrayEquals(tags.toArray(), tagDao.findTags().toArray());
    }

    @Test
    void findTag() {
        assertEquals(tagCosmetics, tagDao.findTag(1));
    }

    @Test
    void isTagExist() {
        assertTrue(tagDao.isTagExist(1));
        assertFalse(tagDao.isTagExist(58));
    }

    @Test
    void testIsTagExist() {
        assertTrue(tagDao.isTagExist("Cosmetics"));
        assertFalse(tagDao.isTagExist(58));
    }

    @Test
    void deleteTag() {

        if (!tagDao.isTagExist("Clothes")) {
            tagDao.createTag("Clothes");
        }
        tagDao.deleteTag(5);
        assertFalse(tagDao.isTagExist(5));
        tagDao.createTag("Clothes");
    }
}