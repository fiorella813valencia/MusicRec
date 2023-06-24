package com.example.app.application.practice.mapping;

import org.springframework.context.annotation.Bean;

public class MappingConfiguration {
    @Bean
    public SongMapper productMapper(){
        return new SongMapper();
    }
}
