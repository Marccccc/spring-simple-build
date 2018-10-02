package com.simple.context;

import com.simple.context.config.support.DefaultResourceLoader;
import com.simple.core.io.Resource;
import com.simple.factory.BeanFactory;
import com.simple.factory.support.DefaultBeanFactory;
import com.simple.factory.support.XMLBeanDefinitionReader;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements BeanFactory {

    private DefaultBeanFactory beanFactory;

    public abstract Resource getResource();

    @Override
    public Object getBean(String beanName) {
        beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader xmlBeanReader = new XMLBeanDefinitionReader(getResource());
        xmlBeanReader.loadBeanDefinition(beanFactory);
        return beanFactory.getBean(beanName);
    }
}
