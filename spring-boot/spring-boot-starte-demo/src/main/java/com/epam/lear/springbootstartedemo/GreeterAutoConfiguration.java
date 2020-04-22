package com.epam.lear.springbootstartedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Slf4j
@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {

    @Autowired
    private GreeterProperties greeterProperties;
    @Autowired
    private Greeter greeter;

    @Bean
    @ConditionalOnMissingBean
    public Greeter greeterConfig() {

        String userName = greeterProperties.getUserName() == null
                ? System.getProperty("user.name")
                : greeterProperties.getUserName();

        return new Greeter(userName, greeterProperties.getMessage());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void greet() {
        log.info("{}, {}", greeter.getMessage(), greeter.getUserName());
    }
}
