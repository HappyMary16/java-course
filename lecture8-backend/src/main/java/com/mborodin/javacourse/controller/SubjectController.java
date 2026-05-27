package com.mborodin.javacourse.controller;

import com.mborodin.javacourse.mapper.SubjectMapper;
import com.mborodin.javacourse.model.api.SubjectApi;
import com.mborodin.javacourse.model.domain.Subject;
import com.mborodin.javacourse.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @GetMapping
    public List<Subject> getAll() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectApi getById(@PathVariable String id) {
        var subject = subjectService.getSubject(id);
        if (subject == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return subjectMapper.toSubjectApi(subject);
    }

    @PostMapping
    public SubjectApi create(@RequestBody SubjectApi subject) {
        if (subjectService.getSubject(subject.id()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return Optional.of(subject)
                       .map(subjectMapper::toSubject)
                       .map(subjectService::createSubject)
                       .map(subjectMapper::toSubjectApi)
                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        subjectService.deleteSubject(id);
    }
}
