package com.mborodin.javacourse.repository;

import com.mborodin.javacourse.model.db.LectureDb;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<LectureDb, String> {
}
