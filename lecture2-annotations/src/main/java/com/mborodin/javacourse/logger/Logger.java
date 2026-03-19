package com.mborodin.javacourse.logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.StringJoiner;

/**
 * Logs entities without PII.
 *
 * @author Mariia Borodin (HappyMary16)
 */
public class Logger {

    /**
     * Logs object without PII. Object is inserted if format String.
     *
     * @param format to print and insert object without PII.
     * @param object to remove PII and print.
     */
    public static void logWithoutPii(String format, Object object) {
        var type = object.getClass();
        if (!type.isRecord()) {
            throw new IllegalArgumentException("Object is not a record. Object type: " + type.getSimpleName());
        }

        String objectWithoutPii;
        if (type.getAnnotation(PII.class) == null) {
            objectWithoutPii = object.toString();
        } else {
            objectWithoutPii = buildStringWithoutPii(object);
        }

        System.out.printf(format, objectWithoutPii);
        System.out.println();
    }

    private static String buildStringWithoutPii(Object object) {
        var type = object.getClass();
        var stringJoiner = new StringJoiner(", ", "[", "]");

        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(PII.class)) {
                continue;
            }

            String fieldName = field.getName();
            try {
                Object value = type.getMethod(fieldName).invoke(object);
                stringJoiner.add(fieldName + ": " + value);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                System.err.println("Error while trying to access method " + fieldName);
            }
        }

        return stringJoiner.toString();
    }
}
