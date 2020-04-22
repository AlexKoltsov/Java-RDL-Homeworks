package com.epam.learn.springbootdemo.service;

import com.epam.learn.springbootdemo.data.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getAll();
}
