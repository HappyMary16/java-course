package com.mborodin.javacourse.service;

import com.mborodin.javacourse.mapper.LectureMapper;
import com.mborodin.javacourse.model.domain.Lecture;
import com.mborodin.javacourse.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    public List<Lecture> getAllLectures() {
        List<Lecture> lectures = new ArrayList<>();
        lectureRepository.findAll().forEach(l -> lectures.add(lectureMapper.toLecture(l)));

        // В справжній програмі, а не прикладі, в сервісі у вас ще буде знаходитись вся бізнес логіка додатку.

        return lectures;
    }

    public Lecture getLecture(String id) {
        return lectureRepository.findById(id)
                                .map(lectureMapper::toLecture)
                                .orElse(null);
    }

    public Lecture createLecture(Lecture lecture) {
        return lectureMapper.toLecture(lectureRepository.save(lectureMapper.toLectureDb(lecture)));
    }

    public void deleteLecture(String id) {
        lectureRepository.deleteById(id);
    }
}
