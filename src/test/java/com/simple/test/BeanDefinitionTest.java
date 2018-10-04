package com.simple.test;

import com.simple.bean.BeanDefinition;
import com.simple.bean.PropertyDefinition;
import com.simple.bean.RefPropertyDefinition;
import com.simple.context.AbstractApplicationContext;
import com.simple.context.config.support.DefaultResourceLoader;
import com.simple.context.support.ClassPathXmlApplicationContext;
import com.simple.core.io.ClassPathResource;
import com.simple.dao.CompanyDao;
import com.simple.dao.EmployeeDao;
import com.simple.factory.support.DefaultBeanFactory;
import com.simple.factory.support.XMLBeanDefinitionReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class BeanDefinitionTest {

    private DefaultBeanFactory beanFactory = null;
    private XMLBeanDefinitionReader xmlBeanReader = null;

    @Before
    public void loadBean() {
        beanFactory = new DefaultBeanFactory(new DefaultResourceLoader());
        xmlBeanReader = new XMLBeanDefinitionReader(new ClassPathResource("application.xml"));
        xmlBeanReader.loadBeanDefinition(beanFactory);
    }

    @Test
    public void testBeanDefinitionRefPropertyDefinition() {
        BeanDefinition companyServiceDefinition = beanFactory.getBeanDefinition("companyService");
        List<PropertyDefinition> propertyValues = companyServiceDefinition.getPropertyValues();
        assertEquals(propertyValues.size(), 2);

        propertyValues.forEach(propertyValue -> {
            if (propertyValue.getName().equals("companyDao")) {
                assertTrue(propertyValue.getValue() instanceof RefPropertyDefinition);
            }
            if (propertyValue.getName().equals("employeeDao")) {
                assertTrue(propertyValue.getValue() instanceof RefPropertyDefinition);
            }
        });

    }

}
