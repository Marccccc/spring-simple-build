package com.simple.factory.support;

import com.simple.bean.BeanDefinition;
import com.simple.core.io.Resource;
import com.simple.exception.BeanStoreException;
import com.simple.factory.BeanDefinitionRegistry;
import com.simple.util.ClassUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * 加载一个XML配置文件并注册到Bean注册器中
 */
public class XMLBeanDefinitionReader {

    private Resource resource;

    public XMLBeanDefinitionReader(Resource resource) {
        this.resource = resource;
    }

    public void loadBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry) {
        try {
            InputStream inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> beanIterator = rootElement.elementIterator("bean");
            while (beanIterator.hasNext()) {
                Element beanElement = beanIterator.next();
                String id = beanElement.attribute("id").getValue();
                String className = beanElement.attribute("class").getValue();
                Attribute scope = beanElement.attribute("scope");
                BeanDefinition beanDefinition = null;
                if (scope != null) {
                    beanDefinition = new BeanDefinition(id, className, scope.getValue());
                } else {
                    beanDefinition = new BeanDefinition(id, className, BeanDefinition.SCOPE_DEFAULT);
                }
                beanDefinitionRegistry.registryBeanDefinition(beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanStoreException(resource.getDescribe());
        }

    }
}
