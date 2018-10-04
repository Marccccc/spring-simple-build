package com.simple.factory.support;

import com.simple.bean.*;
import com.simple.core.io.Resource;
import com.simple.exception.BeanCreationException;
import com.simple.exception.BeanStoreException;
import com.simple.factory.BeanDefinitionRegistry;
import com.simple.util.ClassUtils;
import javafx.beans.property.Property;
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

    private final static String ELEMENT_BEAN = "bean";
    private final static String ATTRIBUTE_ID = "id";
    private final static String ATTRIBUTE_CLASS = "class";
    private final static String ATTRIBUTE_SCOPE = "scope";
    private final static String ATTRIBUTE_PROPERTY = "property";
    private final static String ATTRIBUTE_REF = "ref";
    private final static String ATTRIBUTE_VALUE = "value";
    private final static String ATTRIBUTE_NAME = "name";


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
            Iterator<Element> beanIterator = rootElement.elementIterator(ELEMENT_BEAN);
            while (beanIterator.hasNext()) {
                Element beanElement = beanIterator.next();
                String id = beanElement.attribute(ATTRIBUTE_ID).getValue();
                String className = beanElement.attribute(ATTRIBUTE_CLASS).getValue();
                Attribute scope = beanElement.attribute(ATTRIBUTE_SCOPE);
                GenericBeanDefinition beanDefinition = null;
                if (scope != null) {
                    beanDefinition = new GenericBeanDefinition(id, className, scope.getValue());
                } else {
                    beanDefinition = new GenericBeanDefinition(id, className, GenericBeanDefinition.SCOPE_DEFAULT);
                }
                Iterator<Element> propertyIterator = beanElement.elementIterator(ATTRIBUTE_PROPERTY);
                while (propertyIterator.hasNext()) {
                    Element propertyElement = propertyIterator.next();
                    PropertyDefinition propertyDefinition = new PropertyDefinition(propertyElement.attributeValue(ATTRIBUTE_NAME),getPropertyDefinition(propertyElement));
                    beanDefinition.getPropertyValues().add(propertyDefinition);
                }
                beanDefinitionRegistry.registryBeanDefinition(beanDefinition);
            }
        } catch (Exception e) {
            throw new BeanStoreException(resource.getDescribe());
        }

    }

    private Object getPropertyDefinition(Element element) {
        Attribute refAttribute = element.attribute(ATTRIBUTE_REF);
        Attribute valueAttribute = element.attribute(ATTRIBUTE_VALUE);
        if (refAttribute != null) {
            RefPropertyDefinition refPropertyDefinition = new RefPropertyDefinition(refAttribute.getValue());
            return refPropertyDefinition;
        } else if (valueAttribute != null) {
            StringTypePropertyDefinition stringTypePropertyDefinition = new StringTypePropertyDefinition(valueAttribute.getValue());
            return stringTypePropertyDefinition;
        } else {
            throw new BeanCreationException("property未找到ref或value");
        }
    }

}
