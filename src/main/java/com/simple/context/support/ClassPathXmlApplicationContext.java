package com.simple.context.support;

import com.simple.context.AbstractApplicationContext;
import com.simple.core.io.ClassPathResource;
import com.simple.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String rescource;

    public ClassPathXmlApplicationContext(String rescource) {
        this.rescource = rescource;
    }

    @Override
    public Resource getResource() {
        return new ClassPathResource(rescource);
    }
}
