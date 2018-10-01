package com.simple.factory;

import com.simple.bean.BeanDefinition;

public abstract class BeanFactory {
    public abstract BeanDefinition getBeanDefinition(String companyService);

    public abstract Object getBean(String companyService);
}
