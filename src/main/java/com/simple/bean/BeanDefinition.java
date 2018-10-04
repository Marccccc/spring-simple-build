package com.simple.bean;

import java.util.List;

/**
 * 容器内的Bean定义
 */
public interface BeanDefinition {

    String getName();

    String getClassName();

    String getScope();

    Boolean isSingleton();

    Boolean isPrototype();

    List<PropertyDefinition> getPropertyValues();

}
