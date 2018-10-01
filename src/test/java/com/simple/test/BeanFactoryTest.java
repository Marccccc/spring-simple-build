package com.simple.test;

import com.simple.bean.BeanDefinition;
import com.simple.exception.BeanCreationException;
import com.simple.exception.BeanStoreException;
import com.simple.factory.BeanFactory;
import com.simple.factory.support.DefaultBeanFactory;
import com.simple.service.CompanyService;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanFactoryTest {

    @Test
    public void testLoadBean() {

        BeanFactory beanFactory = new DefaultBeanFactory("application.xml");

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
            BeanFactory beanFactory = new DefaultBeanFactory("test-fail.xml");
        }catch (BeanStoreException e){
            return;
        }
        fail();
    }

    @Test
    public void testInvalidBeanException() {
        try {
            BeanFactory beanFactory = new DefaultBeanFactory("application.xml");
            beanFactory.getBean("test-invalid");
        }catch (BeanCreationException e){
            return;
        }
        fail();
    }

}
