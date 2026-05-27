package com.mborodin.javacourse.model.domain;

import lombok.Builder;

import java.time.DayOfWeek;

/**
 * TODO: add description.
 *
 * @author Mariia Borodin (HappyMary16)
 * @since 1.0
 */
@Builder(toBuilder = true)
public record Subject(
        String id,
        String name,
        DayOfWeek weekDay,
        String teacher) {
}
