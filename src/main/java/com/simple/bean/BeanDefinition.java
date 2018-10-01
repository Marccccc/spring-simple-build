package com.simple.bean;

/**
 * 容器内的Bean定义
 */
public class BeanDefinition {

    private String name;
    private String className;

    public BeanDefinition(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }
}
