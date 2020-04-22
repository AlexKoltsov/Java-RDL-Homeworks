package com.epam.learn.springbootdemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@ConfigurationProperties(prefix = "lesson")
@Data
@Component
@Validated
public class LessonProperties {
    @Min(30)
    private Integer minSupportedDuration;
}
