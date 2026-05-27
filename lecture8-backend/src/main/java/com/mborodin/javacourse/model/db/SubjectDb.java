package com.mborodin.javacourse.model.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

/**
 * TODO: add description.
 *
 * @author Mariia Borodin (HappyMary16)
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
@Entity
public class SubjectDb {
    @Id
    private String id;
    private String name;
    private DayOfWeek weekDay;
    private String teacher;
    @Version
    private int version;
}
