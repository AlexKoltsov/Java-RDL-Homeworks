package com.epam.learn.springbootdemo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer durationInMinutes;
    private LessonComplexity lessonComplexity;

}
