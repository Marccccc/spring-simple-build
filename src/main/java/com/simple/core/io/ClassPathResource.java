package com.simple.core.io;

import com.simple.exception.BeanStoreException;
import com.simple.util.ClassUtils;

import java.io.InputStream;

public class ClassPathResource implements Resource {

    private String resource;

    private ClassLoader classLoader;

    public ClassPathResource(String resource) {
        this(resource, null);
    }

    public ClassPathResource(String resource, ClassLoader classLoader) {
        if (resource == null || "".equals(resource)) {
            throw new BeanStoreException(resource);
        }
        this.resource = resource;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() {
        return this.classLoader.getResourceAsStream(resource);
    }

    @Override
    public String getDescribe() {
        return "resource:" + resource;
    }

}
