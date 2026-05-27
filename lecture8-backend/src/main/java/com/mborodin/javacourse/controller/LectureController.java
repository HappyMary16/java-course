package com.mborodin.javacourse.controller;

import com.mborodin.javacourse.mapper.LectureMapper;
import com.mborodin.javacourse.model.api.LectureApi;
import com.mborodin.javacourse.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;
    private final LectureMapper lectureMapper;

    @GetMapping
    public List<LectureApi> getAll() {
        return lectureService.getAllLectures()
                .stream()
                .map(lectureMapper::toLectureApi)
                .toList();
    }

    @GetMapping("/{id}")
    public LectureApi getById(@PathVariable String id) {
        var lecture = lectureService.getLecture(id);
        if (lecture == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return lectureMapper.toLectureApi(lecture);
    }

    @PostMapping
    public LectureApi create(@RequestBody LectureApi lecture) {
        if (lectureService.getLecture(lecture.getId()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return Optional.of(lecture)
                .map(lectureMapper::toLecture)
                .map(lectureService::createLecture)
                .map(lectureMapper::toLectureApi)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        lectureService.deleteLecture(id);
    }
}
