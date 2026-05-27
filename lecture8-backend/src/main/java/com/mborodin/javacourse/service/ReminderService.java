package com.mborodin.javacourse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Надсилає повідомлення з нагадуванням про лекцію.
 *
 * @author Mariia Borodin (HappyMary16)
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ReminderService {

    private final SubjectService subjectService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void sendReminders() {
        log.info("Sending reminders");
        subjectService.getAllSubjects()
                      .stream()
                      .filter(s -> s.weekDay().equals(getTodaysDayOfWeek()))
                      .forEach(s -> log.info("Sending reminder for teacher: {}", s.teacher()));
    }

    private DayOfWeek getTodaysDayOfWeek() {
        final Calendar c = Calendar.getInstance();
        return DayOfWeek.of(c.get(Calendar.DAY_OF_WEEK));
    }
}
