package com.bal.user_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {
@Bean
    public ModelMapper getModelMapperMethod(){
    return new ModelMapper();
}
}
