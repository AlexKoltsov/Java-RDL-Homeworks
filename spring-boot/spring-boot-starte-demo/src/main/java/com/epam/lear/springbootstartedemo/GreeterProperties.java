package com.epam.lear.springbootstartedemo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "greeter")
@Data
public class GreeterProperties {
    private String userName;
    private String message = "Hello";
}

