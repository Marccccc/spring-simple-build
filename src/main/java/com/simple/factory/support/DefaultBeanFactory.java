package com.simple.factory.support;

import com.simple.bean.BeanDefinition;
import com.simple.exception.BeanCreationException;
import com.simple.exception.BeanStoreException;
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

public class DefaultBeanFactory extends BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, Object> beanInstanceMap = new HashMap<>();

    public DefaultBeanFactory(String resource) {
        this.loadBean(resource);
    }

    private void loadBean(String resource) {

        if (resource == null || "".equals(resource)) {
            throw new BeanStoreException(resource);
        }

        try {
            InputStream   inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(resource); SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> beanIterator = rootElement.elementIterator("bean");
            while (beanIterator.hasNext()) {
                Element beanElement = beanIterator.next();
                String id = beanElement.attribute("id").getValue();
                String className = beanElement.attribute("class").getValue();
                BeanDefinition beanDefinition = new BeanDefinition(id, className);
                beanDefinitionMap.put(id, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanStoreException(resource);
        }

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new BeanCreationException(beanName);
        }
        if (beanInstanceMap.containsKey(beanName)) {
           return beanInstanceMap.get(beanName);
        }
        Object obj = null;
        try {
            obj = ClassUtils.getDefaultClassLoader().loadClass(beanDefinitionMap.get(beanName).getClassName()).newInstance();
        } catch (Exception e) {
            throw new BeanCreationException(beanName);
        }
        beanInstanceMap.put(beanName, obj);
        return beanInstanceMap.get(beanName);
    }

}
