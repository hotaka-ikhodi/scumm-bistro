package com.scumm.core.microservices;

import com.clitellum.ioc.Container;
import com.clitellum.services.handlers.ConfigurableHandler;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class MicroSpringContainer implements Container {

    private ApplicationContext context;

    public MicroSpringContainer(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public ConfigurableHandler getHandler(Class aClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (ConfigurableHandler) context.getBean(aClass);
    }
}
