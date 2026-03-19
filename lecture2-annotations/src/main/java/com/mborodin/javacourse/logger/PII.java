package com.mborodin.javacourse.logger;

import java.lang.annotation.*;

/**
 * Annotation to mark Personally Identifiable Information.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE,  ElementType.PARAMETER})
@Inherited
public @interface PII {
}
