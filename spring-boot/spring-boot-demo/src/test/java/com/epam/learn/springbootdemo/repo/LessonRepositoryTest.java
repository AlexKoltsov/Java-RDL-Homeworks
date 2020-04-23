package com.epam.learn.springbootdemo.repo;

import com.epam.learn.springbootdemo.data.Lesson;
import com.epam.learn.springbootdemo.data.LessonComplexity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest provides some standard setup needed for testing the persistence layer:
// configuring H2, an in-memory database
// setting Hibernate, Spring Data, and the DataSource
// performing an @EntityScan
// turning on SQL logging
@DataJpaTest
class LessonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LessonRepository lessonRepository;

    @Test
    void findByName() {
        // given
        Lesson java = new Lesson();
        java.setName("java");
        entityManager.persist(java);
        entityManager.flush();

        // when
        Optional<Lesson> found = lessonRepository.findByName(java.getName());

        // then
        assertAll(
                () -> assertTrue(found.isPresent()),
                () -> assertEquals(java, found.orElse(null))
        );
    }
}
