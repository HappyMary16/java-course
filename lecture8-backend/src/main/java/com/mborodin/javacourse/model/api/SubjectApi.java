package com.mborodin.javacourse.model.api;

import lombok.Builder;

import java.time.DayOfWeek;

/**
 * TODO: add description.
 *
 * @author Mariia Borodin (HappyMary16)
 * @since 1.0
 */
@Builder
public record SubjectApi(
        String id,
        String name,
        DayOfWeek weekDay,
        String teacher) {
}
