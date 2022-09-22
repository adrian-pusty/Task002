package com.jms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync // To be used together with @Configuration classes
@EnableScheduling // To be used on @Configuration classes
@Configuration
public class TaskConfig
{
    @Bean
    TaskExecutor taskExecutor()
    {
        return new SimpleAsyncTaskExecutor();
    }
}

