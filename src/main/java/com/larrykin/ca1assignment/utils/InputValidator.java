package com.larrykin.ca1assignment.utils;

import java.time.LocalDate;

public class InputValidator {
    private static final String[] CAKE_TYPES = {"Pineapple", "Strawberry", "Chocolate", "Vanilla", "Plain"};

    public static boolean isValidCakeType(String cakeType) {
        if (cakeType == null || cakeType.trim().isEmpty()) {
            return false;
        }

        for (String type : CAKE_TYPES) {
            if (type.equalsIgnoreCase(cakeType.trim())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidWeight(String weightStr) {
        try {
            double weight = Double.parseDouble(weightStr);
            return weight > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidBestBeforeDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusWeeks(2);

        return date != null && !date.isBefore(today) && !date.isAfter(maxDate);
    }
}
