package com.epam.esm.service.validation;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @ParameterizedTest
    @MethodSource("checkName")
    public void checkPassword(String value) {
        assertTrue(Validator.validateName(value));
    }

    static Stream<String> checkName() {
        return Stream.of("Name", "Gggggg", "Aababababa8", "TetyaMotya", "ddueUYEYHD85");
    }

    @ParameterizedTest
    @MethodSource("checkNameFalse")
    public void checkPasswordFalse(String value) {
        assertFalse(Validator.validateName(value));
    }

    static Stream<String> checkNameFalse() {
        return Stream.of("ff", "", null, "dfhryTnnmddgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg9");
    }

    @Test
    void isValuePresent() {
        Certificate certificatePositive = new Certificate(2, "Pet store",
                "Pet store", 200.0, 12, "2022-04-03", "2022-04-03");
        Certificate certificateNegative = new Certificate(0, "",
                null, 0.0, 0, "", "");
        Certificate certificateNegative2 = new Certificate(1, "",
                "null", 0.0, 12, "", "");
        assertTrue(Validator.isValuePresent(certificatePositive));
        assertFalse(Validator.isValuePresent(certificateNegative));
        assertFalse(Validator.isValuePresent(certificateNegative2));

    }

    @Test
    void isGreaterZero() {
        assertTrue(Validator.isGreaterZero(1));
        assertFalse(Validator.isGreaterZero(0));
        assertFalse(Validator.isGreaterZero(-1));
    }


    @Test
    void validateListOfTags() {
        List<Tag> tagsPositive=new ArrayList<>();
        tagsPositive.add(new Tag(11, "TestTag"));
        tagsPositive.add(new Tag(1, "TestTag2"));

        List<Tag> tagsNegative=new ArrayList<>();
        tagsNegative.add(new Tag(11, ""));
        tagsNegative.add(new Tag(1, null));

        List<Tag> tagsNegative2=new ArrayList<>();
        tagsNegative2.add(new Tag(11, "Test tag"));
        tagsNegative2.add(new Tag(1, null));

        List<Tag> tagsNull=null;

        assertTrue(Validator.validateListOfTags(tagsPositive));
        assertFalse(Validator.validateListOfTags(tagsNegative));
        assertFalse(Validator.validateListOfTags(tagsNegative2));
        assertFalse(Validator.validateListOfTags(tagsNull));
    }
}