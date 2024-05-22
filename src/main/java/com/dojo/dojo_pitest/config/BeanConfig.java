package com.dojo.dojo_pitest.config;

import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.mapper.impl.MapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IMapper iMapper() {
        return new MapperImpl();
    }
}
