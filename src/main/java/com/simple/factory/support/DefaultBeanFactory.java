package com.simple.factory.support;

import com.simple.bean.BeanDefinition;
import com.simple.exception.BeanCreationException;
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

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, Object> beanInstanceMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new BeanCreationException(beanName);
        }
        if (beanInstanceMap.containsKey(beanName)) {
            return beanInstanceMap.get(beanName);
        }
        try {
            Object obj = ClassUtils.getDefaultClassLoader().loadClass(beanDefinitionMap.get(beanName).getClassName()).newInstance();
            beanInstanceMap.put(beanName, obj);
        } catch (Exception e) {
            throw new BeanCreationException(beanName);
        }
        return beanInstanceMap.get(beanName);
    }

}
