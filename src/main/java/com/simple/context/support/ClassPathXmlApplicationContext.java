package com.simple.context.support;

import com.simple.context.AbstractApplicationContext;
import com.simple.context.config.ResourceLoader;
import com.simple.context.config.support.DefaultResourceLoader;
import com.simple.core.io.ClassPathResource;
import com.simple.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String resource;

    public ClassPathXmlApplicationContext(String rescource) {
        this.resource = rescource;
    }

    @Override
    public Resource getResource() {
        return this.getResourceLoader().getResource(resource);
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return new DefaultResourceLoader();
    }

}
