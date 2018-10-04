package com.simple.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GenericBeanDefinition implements BeanDefinition {

    public final static String SCOPE_SINGLETON = "singleton";
    public final static String SCOPE_PROTOTYPE = "prototype";
    public final static String SCOPE_DEFAULT = SCOPE_SINGLETON;

    private String name;
    private String className;
    private String scope;
    private List<PropertyDefinition> propertyDefinitions=new ArrayList<>();

    public GenericBeanDefinition(String name, String className, String scope) {
        this.name = name;
        this.className = className;
        this.scope = scope;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public Boolean isSingleton() {
        return scope.equals(SCOPE_SINGLETON);
    }

    @Override
    public Boolean isPrototype() {
        return scope.equals(SCOPE_PROTOTYPE);
    }

    @Override
    public List<PropertyDefinition> getPropertyValues() {
        return this.propertyDefinitions;
    }

}
