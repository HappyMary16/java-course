package com.mborodin.javacourse.mapper;

import com.mborodin.javacourse.model.api.SubjectApi;
import com.mborodin.javacourse.model.db.SubjectDb;
import com.mborodin.javacourse.model.domain.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toSubject(SubjectDb lectureDb);

    SubjectDb toSubjectDb(Subject subject);

    SubjectApi toSubjectApi(Subject subject);

    Subject toSubject(SubjectApi subjectApi);
}
