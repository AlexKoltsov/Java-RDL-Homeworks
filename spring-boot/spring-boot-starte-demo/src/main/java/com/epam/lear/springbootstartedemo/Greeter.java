package com.epam.lear.springbootstartedemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Greeter {
    private String userName;
    private String message;
}
