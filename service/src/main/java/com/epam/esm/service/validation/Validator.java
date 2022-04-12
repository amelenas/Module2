package com.epam.esm.service.validation;

import com.epam.esm.dao.entity.Certificate;
import com.epam.esm.dao.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private static final int MAX_LENGTH_NAME = 20;
    private static final int MIN_LENGTH_NAME = 3;

    public static boolean isValuePresent(Certificate certificate) {
        return certificate != null && certificate.getName() != null && !certificate.getName().isEmpty() && certificate.getDescription()
                != null && !certificate.getDescription().isEmpty()
                && certificate.getPrice() > 0 && certificate.getDuration() > 0;
    }

    public static boolean isGreaterZero(long id) {
        return id > 0;
    }


    public static boolean validateName(String name) {
        return name != null && name.length() > MIN_LENGTH_NAME && name.length() < MAX_LENGTH_NAME;

    }

    public static boolean validateListOfTags(List<Tag> tags) {
        if (tags == null) return false;
        List<Boolean> result = new ArrayList<>();
        for (Tag tag : tags) {
            result.add(validateName(tag.getName()));
        }
        return !result.contains(false);
    }
}
