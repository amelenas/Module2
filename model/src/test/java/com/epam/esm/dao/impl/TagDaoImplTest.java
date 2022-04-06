package com.epam.esm.dao.impl;

import com.epam.esm.entity.Tag;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
class TagDaoImplTest {
    Tag tagCosmetics = new Tag(1, "Cosmetics");
    Tag tagMusic = new Tag(2, "Music store");
    Tag tagFitness = new Tag(3, "Fitness");
    Tag tagFood = new Tag(4, "Food");
    Tag tagClothes = new Tag(5, "Clothes");

    @Autowired
    private TagDaoImpl tagDao;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void beforeTest() throws FileNotFoundException, SQLException {
        RunScript.execute(dataSource.getConnection(), new FileReader(new File("src/test/resources/certificates_script.sql").getAbsolutePath()));
    }

    @Test
    void findTags() {
        List<Optional<Tag>> tags = new ArrayList<>();
        tags.add(Optional.of(tagCosmetics));
        tags.add(Optional.of(tagMusic));
        tags.add(Optional.of(tagFitness));
        tags.add(Optional.of(tagFood));
        assertArrayEquals(tags.toArray(), tagDao.findTags().toArray());
    }

    @Test
    void createTag() {
        tagDao.createTag("Clothes");
        assertTrue(tagDao.findTags().contains(Optional.of(tagClothes)));
    }

    @Test
    void findTag() {
        assertEquals(tagCosmetics, tagDao.findTag(1).get());
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
        tagDao.deleteTag(1);
        assertFalse(tagDao.isTagExist(1));
    }
}