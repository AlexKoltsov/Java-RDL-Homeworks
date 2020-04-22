package com.epam.learn.springbootdemo.service.impl;

import com.epam.learn.springbootdemo.LessonProperties;
import com.epam.learn.springbootdemo.data.Lesson;
import com.epam.learn.springbootdemo.data.LessonComplexity;
import com.epam.learn.springbootdemo.repo.LessonRepository;
import com.epam.learn.springbootdemo.service.LessonService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    private static final List<Lesson> TEST_LESSONS = List.of(
            new Lesson(1L, "Java 8", 120, LessonComplexity.EASY),
            new Lesson(2L, "Maven/Gradle", 60, LessonComplexity.EASY),
            new Lesson(3L, "Hibernate", 90, LessonComplexity.MIDDLE),
            new Lesson(4L, "Spring core", 90, LessonComplexity.MIDDLE),
            new Lesson(5L, "Spring boot", 60, LessonComplexity.HARD)
    );

    private final LessonRepository lessonRepository;
    private final LessonProperties lessonProperties;

    public LessonServiceImpl(LessonRepository lessonRepository, LessonProperties lessonProperties) {
        this.lessonRepository = lessonRepository;
        this.lessonProperties = lessonProperties;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initTestData() {
        lessonRepository.saveAll(TEST_LESSONS);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll().stream()
                .filter(lesson -> lesson.getDurationInMinutes() > lessonProperties.getMinSupportedDuration())
                .collect(Collectors.toList());
    }
}
