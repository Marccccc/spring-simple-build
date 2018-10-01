package com.simple.factory.support;

import com.simple.bean.BeanDefinition;
import com.simple.exception.BeanStoreException;
import com.simple.factory.BeanDefinitionRegistry;
import com.simple.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * 加载一个XML配置文件并注册到Bean注册器中
 */
public class XMLBeanDefinitionReader {

    private String resource;

    public XMLBeanDefinitionReader(String resource) {
        this.resource = resource;
    }

    public void loadBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry) {

        if (resource == null || "".equals(resource)) {
            throw new BeanStoreException(resource);
        }

        try {
            InputStream inputStream = ClassUtils.getDefaultClassLoader().getResourceAsStream(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> beanIterator = rootElement.elementIterator("bean");
            while (beanIterator.hasNext()) {
                Element beanElement = beanIterator.next();
                String id = beanElement.attribute("id").getValue();
                String className = beanElement.attribute("class").getValue();
                BeanDefinition beanDefinition = new BeanDefinition(id, className);
                beanDefinitionRegistry.registryBeanDefinition(beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanStoreException(resource);
        }

    }
}
