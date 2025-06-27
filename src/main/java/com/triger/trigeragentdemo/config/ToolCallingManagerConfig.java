package com.triger.trigeragentdemo.config;

import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolCallingManagerConfig {

    @Bean
    ToolCallingManager toolCallingManager() {
        return ToolCallingManager.builder()
                .build();
    }
}
