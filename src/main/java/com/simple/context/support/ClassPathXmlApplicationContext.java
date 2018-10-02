package com.simple.context.support;

import com.simple.context.ApplicationContext;
import com.simple.core.io.ClassPathResource;
import com.simple.core.io.Resource;

public class ClassPathXmlApplicationContext extends ApplicationContext {

    private String rescource;

    public ClassPathXmlApplicationContext(String rescource) {
        this.rescource = rescource;
    }

    @Override
    public Resource getResource() {
        return new ClassPathResource(rescource);
    }
}
