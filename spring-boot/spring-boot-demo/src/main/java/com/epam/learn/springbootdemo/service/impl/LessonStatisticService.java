package com.epam.learn.springbootdemo.service.impl;

import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Endpoint(id = "lessons")
@Component
@Data
public class LessonStatisticService {
    private AtomicLong getAllCounter = new AtomicLong(0L);

    public void call() {
        getAllCounter.incrementAndGet();
    }

    @ReadOperation
    public Long getStat() {
        return getAllCounter.get();
    }
}
