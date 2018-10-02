package com.simple.factory.support;

import com.simple.bean.BeanDefinition;
import com.simple.context.config.ResourceLoader;
import com.simple.context.config.support.DefaultResourceLoader;
import com.simple.core.io.Resource;
import com.simple.exception.BeanCreationException;
import com.simple.exception.BeanException;
import com.simple.exception.BeanStoreException;
import com.simple.factory.BeanDefinitionRegistry;
import com.simple.factory.BeanFactory;
import com.simple.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private ResourceLoader resourceLoader;

    public DefaultBeanFactory(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
        if (beanDefinition.isSingleton()) {
            this.registrySingleton(beanDefinition.getName(), createBean(beanDefinition));
        }
    }

    @Override
    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new BeanCreationException(beanName);
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.isSingleton()) {
            return this.getSingleton(beanName);
        } else {
            return createBean(beanDefinition);
        }

    }

    private Object createBean(BeanDefinition beanDefinition) {
        try {
            return this.resourceLoader.getClassLoader().loadClass(beanDefinition.getClassName()).newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("创建失败");
        }
    }

}
