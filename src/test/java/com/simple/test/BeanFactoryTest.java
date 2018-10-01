package com.simple.test;

import com.simple.bean.BeanDefinition;
import com.simple.exception.BeanCreationException;
import com.simple.exception.BeanStoreException;
import com.simple.factory.BeanFactory;
import com.simple.factory.support.DefaultBeanFactory;
import com.simple.factory.support.XMLBeanDefinitionReader;
import com.simple.service.CompanyService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    private DefaultBeanFactory beanFactory = null;
    private XMLBeanDefinitionReader xmlBeanReader = null;

    @Before
    public void loadBean() {
        beanFactory = new DefaultBeanFactory();
        xmlBeanReader = new XMLBeanDefinitionReader("application.xml");
        xmlBeanReader.loadBeanDefinition(beanFactory);
    }

    @Test
    public void testLoadBean() {

        // 测试获取BeanDefinition
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("companyService");
        assertNotNull(beanDefinition);

        // 测试获取Bean实例
        CompanyService companyService = (CompanyService) beanFactory.getBean("companyService");
        assertNotNull(companyService);

    }

    @Test
    public void testBeanStoreException() {
        try {
            xmlBeanReader = new XMLBeanDefinitionReader("test-load-fail.xml");
            xmlBeanReader.loadBeanDefinition(beanFactory);
        } catch (BeanStoreException e) {
            return;
        }
        fail();
    }

    @Test
    public void testInvalidBeanException() {
        try {
            beanFactory.getBean("test-invalid");
        } catch (BeanCreationException e) {
            return;
        }
        fail();
    }

}
