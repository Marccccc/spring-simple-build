package com.simple.test;

import com.simple.bean.BeanDefinition;
import com.simple.context.support.ClassPathXmlApplicationContext;
import com.simple.service.CompanyService;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationContextTest {

    @Test
    public void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        CompanyService companyService = (CompanyService) context.getBean("companyService");
        assertNotNull(companyService);
    }

}
