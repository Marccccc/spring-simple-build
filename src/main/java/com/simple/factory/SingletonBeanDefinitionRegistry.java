package com.simple.factory;

public interface SingletonBeanDefinitionRegistry {

    void registrySingleton(String beanName, Object object);

    Object getSingleton(String beanName);

}
