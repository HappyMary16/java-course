package com.mborodin.javacourse.repository;

import com.mborodin.javacourse.model.db.SubjectDb;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<SubjectDb, String> {
}
