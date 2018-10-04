package com.simple.test;

import com.simple.bean.BeanDefinitionResolver;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ResourceTest.class,
        ApplicationContextTest.class,
        BeanDefinitionTest.class,
        BeanDefinitionResolverTest.class
})
public class AllTest {
}
