package com.simple.factory.support;

import com.simple.bean.BeanDefinition;
import com.simple.exception.BeanCreationException;
import com.simple.factory.BeanDefinitionRegistry;
import com.simple.factory.SingletonBeanDefinitionRegistry;
import com.simple.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanDefinitionRegistry {

    private Map<String, Object> beanInstanceMap = new HashMap<>();

    @Override
    public void registrySingleton(String beanName, Object object) {
        if (beanInstanceMap.containsKey(beanName)) {
            throw new BeanCreationException("has been creation this bean");
        }
        this.beanInstanceMap.put(beanName, object);
    }

    @Override
    public Object getSingleton(String beanName) {
        return beanInstanceMap.get(beanName);
    }


}
