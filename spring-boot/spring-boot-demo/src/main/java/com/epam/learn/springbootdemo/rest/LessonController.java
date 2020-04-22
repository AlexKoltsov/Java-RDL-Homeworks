package com.epam.learn.springbootdemo.rest;

import com.epam.learn.springbootdemo.data.Lesson;
import com.epam.learn.springbootdemo.service.LessonService;
import com.epam.learn.springbootdemo.service.impl.LessonStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/lessons")
@RestController
public class LessonController {

    private final LessonService lessonService;
    private final LessonStatisticService lessonStatisticService;

    public LessonController(LessonService lessonService, LessonStatisticService lessonStatisticService) {
        this.lessonService = lessonService;
        this.lessonStatisticService = lessonStatisticService;
    }

    @GetMapping
    public List<Lesson> getLessons() {
        lessonStatisticService.call();
        return lessonService.getAll();
    }
}
