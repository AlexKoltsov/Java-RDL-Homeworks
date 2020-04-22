package com.epam.learn.springbootdemo.rest;

import com.epam.learn.springbootdemo.data.Lesson;
import com.epam.learn.springbootdemo.data.LessonComplexity;
import com.epam.learn.springbootdemo.service.LessonService;
import com.epam.learn.springbootdemo.service.impl.LessonStatisticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LessonController.class)
class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonService lessonService;

    @MockBean
    private LessonStatisticService lessonStatisticService;

    @Test
    void getLessons() throws Exception {
        Lesson java = new Lesson(0L, "java", 120, LessonComplexity.EASY);
        List<Lesson> allLessons = List.of(java);

        when(lessonService.getAll()).thenReturn(allLessons);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lessons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("java"));
    }
}