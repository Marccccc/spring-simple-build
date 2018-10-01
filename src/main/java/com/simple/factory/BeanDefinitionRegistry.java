package com.simple.factory;

import com.simple.bean.BeanDefinition;

/**
 * Bean定义的注册接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 获取BeanName的对应定义
     * @param beanName bean的唯一名称
     * @return Bean定义
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 将Bean的定义注册
     * @param beanDefinition 注册beanDefinition
     */
    void registryBeanDefinition(BeanDefinition beanDefinition);

}
