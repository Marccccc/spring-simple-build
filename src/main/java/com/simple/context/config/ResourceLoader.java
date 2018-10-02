package com.simple.context.config;

import com.simple.core.io.Resource;

public interface ResourceLoader {

    ClassLoader getClassLoader();

    Resource getResource(String location);
}
