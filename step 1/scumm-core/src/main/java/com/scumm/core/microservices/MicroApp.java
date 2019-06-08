package com.scumm.core.microservices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class MicroApp implements IMicroApp {

    @Autowired
    private ApplicationContext context;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MicroSpringContainer container() {
        return new MicroSpringContainer(context);
    }
}
