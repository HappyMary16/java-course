package com.mborodin.javacourse.service;

import com.mborodin.javacourse.mapper.SubjectMapper;
import com.mborodin.javacourse.model.domain.Subject;
import com.mborodin.javacourse.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjectRepository.findAll().forEach(l -> subjects.add(subjectMapper.toSubject(l)));

        // В справжній програмі, а не прикладі, в сервісі у вас ще буде знаходитись вся бізнес логіка додатку.

        return subjects;
    }

    public Subject getSubject(String id) {
        return subjectRepository.findById(id)
                                .map(subjectMapper::toSubject)
                                .orElse(null);
    }

    public Subject createSubject(Subject subject) {
        return subjectMapper.toSubject(subjectRepository.save(subjectMapper.toSubjectDb(subject)));
    }

    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }
}
