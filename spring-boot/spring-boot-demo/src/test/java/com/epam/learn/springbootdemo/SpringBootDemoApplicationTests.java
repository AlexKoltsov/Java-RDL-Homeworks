package com.epam.learn.springbootdemo;

import com.epam.learn.springbootdemo.data.Lesson;
import com.epam.learn.springbootdemo.data.LessonComplexity;
import com.epam.learn.springbootdemo.repo.LessonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class SpringBootDemoApplicationTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void integrationTest() throws Exception {
        List<Lesson> expected = lessonRepository.findAll();

        String respJson = mvc.perform(MockMvcRequestBuilders.get("/api/v1/lessons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Lesson> actual = objectMapper.readValue(respJson, new TypeReference<>() {
        });

        assertIterableEquals(expected, actual);
    }

}
