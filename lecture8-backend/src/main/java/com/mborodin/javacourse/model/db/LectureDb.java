package com.mborodin.javacourse.model.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: add description.
 *
 * @author Mariia Borodin (HappyMary16)
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lectures")
@Entity
public class LectureDb {
    @Id
    private String id;
    private String theme;
    private String subjectId;
    private int number;
    @Version
    private int version;

}
