package com.epam.learn.springbootdemo.service.impl;

import com.epam.learn.springbootdemo.LessonProperties;
import com.epam.learn.springbootdemo.data.Lesson;
import com.epam.learn.springbootdemo.data.LessonComplexity;
import com.epam.learn.springbootdemo.repo.LessonRepository;
import com.epam.learn.springbootdemo.service.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

// We need mock our dependencies
@ExtendWith(SpringExtension.class)
class LessonServiceImplTest {

    @TestConfiguration
    static class LessonServiceImplTestContextConfiguration {

        @Bean
        public LessonService lessonService(LessonRepository lessonRepository,
                                           LessonProperties lessonProperties) {
            return new LessonServiceImpl(lessonRepository, lessonProperties);
        }
    }

    @Autowired
    private LessonService lessonService;

    @MockBean
    private LessonRepository lessonRepository;

    @MockBean
    private LessonProperties lessonProperties;

    @Test
    void getAll() {
        List<Lesson> expectedList = List.of(new Lesson(0L, "java", 120, LessonComplexity.EASY));
        when(lessonRepository.findAll()).thenReturn(expectedList);
        when(lessonProperties.getMinSupportedDuration()).thenReturn(30);

        List<Lesson> actualList = lessonService.getAll();

        assertIterableEquals(expectedList, actualList);
    }
}