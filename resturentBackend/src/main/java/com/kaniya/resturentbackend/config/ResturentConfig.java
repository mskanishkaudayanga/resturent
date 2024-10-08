package com.kaniya.resturentbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResturentConfig {
    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

}
