package com.simple.test;

import com.simple.bean.BeanDefinitionResolver;
import com.simple.bean.RefPropertyDefinition;
import com.simple.context.config.support.DefaultResourceLoader;
import com.simple.core.io.ClassPathResource;
import com.simple.factory.support.DefaultBeanFactory;
import com.simple.factory.support.XMLBeanDefinitionReader;
import org.junit.Before;
import org.junit.Test;

public class BeanDefinitionResolverTest {

    private DefaultBeanFactory beanFactory = null;
    private XMLBeanDefinitionReader xmlBeanReader = null;

    @Before
    public void loadBean() {
        beanFactory = new DefaultBeanFactory(new DefaultResourceLoader());
        xmlBeanReader = new XMLBeanDefinitionReader(new ClassPathResource("application.xml"));
        xmlBeanReader.loadBeanDefinition(beanFactory);
    }

    @Test
    public void testBeanDefinitionResolver() {
        BeanDefinitionResolver beanDefinitionResolver = new BeanDefinitionResolver(beanFactory);
        RefPropertyDefinition refPropertyDefinition = new RefPropertyDefinition("companyDao");
        beanDefinitionResolver.resolveBeanDefinition(refPropertyDefinition);
    }

}
