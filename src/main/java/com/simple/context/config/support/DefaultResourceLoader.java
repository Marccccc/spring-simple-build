package com.simple.context.config.support;

import com.simple.context.config.ResourceLoader;
import com.simple.core.io.ClassPathResource;
import com.simple.core.io.Resource;
import com.simple.util.ClassUtils;

public class DefaultResourceLoader implements ResourceLoader {

    private ClassLoader classLoader;

    public DefaultResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public DefaultResourceLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader();
    }

    public DefaultResourceLoader setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        return this;
    }

    @Override
    public Resource getResource(String location) {
        return new ClassPathResource(location.substring("classpath:".length()), this.getClassLoader());
    }

}
