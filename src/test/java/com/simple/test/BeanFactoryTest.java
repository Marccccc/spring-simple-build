package com.simple.test;

import com.simple.bean.BeanDefinition;
import com.simple.bean.GenericBeanDefinition;
import com.simple.context.config.support.DefaultResourceLoader;
import com.simple.core.io.ClassPathResource;
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
        beanFactory = new DefaultBeanFactory(new DefaultResourceLoader());
        xmlBeanReader = new XMLBeanDefinitionReader(new ClassPathResource("application.xml"));
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
            xmlBeanReader = new XMLBeanDefinitionReader(new ClassPathResource("test-load-fail.xml"));
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


    /**
     * 测试单例唯一
     */
    @Test
    public void testSingletonOnlyBean() {
        try {
            beanFactory.registrySingleton("companyService", new CompanyService());
        } catch (BeanCreationException e) {
            return;
        }
        fail();

    }


    /**
     * 测试单例生成的是单例
     */
    @Test
    public void testSingletonBean() {
        CompanyService companyService1 = (CompanyService) beanFactory.getBean("companyService");
        CompanyService companyService2 = (CompanyService) beanFactory.getBean("companyService");
        assertEquals(companyService1, companyService2);
    }


    /**
     * 测试多实例
     */
    @Test
    public void testPrototypeBean() {
        BeanDefinition beanDefinition = new GenericBeanDefinition("companyServiceTest", "com.simple.service.CompanyService", GenericBeanDefinition.SCOPE_PROTOTYPE);
        beanFactory.registryBeanDefinition(beanDefinition);
        CompanyService companyService1 = (CompanyService) beanFactory.getBean("companyServiceTest");
        CompanyService companyService2 = (CompanyService) beanFactory.getBean("companyServiceTest");
        assertNotEquals(companyService1, companyService2);
    }

}
