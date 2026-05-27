package com.mborodin.javacourse.model.domain;

import lombok.Builder;

/**
 * TODO: add description.
 *
 * @author Mariia Borodin (HappyMary16)
 * @since 1.0
 */
@Builder(toBuilder = true)
public record Lecture(
        String id,
        String theme,
        String subjectId,
        int number) {
}
