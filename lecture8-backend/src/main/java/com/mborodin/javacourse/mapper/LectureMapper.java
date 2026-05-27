package com.mborodin.javacourse.mapper;

import com.mborodin.javacourse.model.api.LectureApi;
import com.mborodin.javacourse.model.db.LectureDb;
import com.mborodin.javacourse.model.domain.Lecture;
import com.mborodin.javacourse.model.domain.Subject;
import com.mborodin.javacourse.service.SubjectService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LectureMapper {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectMapper subjectMapper;

    public abstract Lecture toLecture(LectureDb lectureDb);

    public abstract LectureDb toLectureDb(Lecture lecture);

    @Mapping(source = "subjectId", target = "subject.id")
    public abstract LectureApi toLectureApi(Lecture lecture);

    @Mapping(source = "subject.id", target = "subjectId")
    public abstract Lecture toLecture(LectureApi lectureApi);

    /**
     * Які недоліки такої реалізації мапперу?
     */
    @AfterMapping
    public void addSubjectToLectureApi(@MappingTarget LectureApi lectureApi) {
        String subjectId = lectureApi.getSubject().id();
        if (subjectId == null) {
            return;
        }

        Subject subject = subjectService.getSubject(subjectId);
        lectureApi.setSubject(subjectMapper.toSubjectApi(subject));
    }
}
