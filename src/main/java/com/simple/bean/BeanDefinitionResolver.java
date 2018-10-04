package com.simple.bean;

import com.simple.exception.BeanCreationException;
import com.simple.factory.BeanFactory;

public class BeanDefinitionResolver {

    private BeanFactory beanFactory;

    public BeanDefinitionResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveBeanDefinition(Object object) {
        if (object instanceof RefPropertyDefinition) {
            return beanFactory.getBean(((RefPropertyDefinition) object).getValue().toString());
        } else if (object instanceof StringTypePropertyDefinition) {
            return ((StringTypePropertyDefinition) object).getValue();
        } else {
            throw new BeanCreationException("未实现");
        }
    }
}
