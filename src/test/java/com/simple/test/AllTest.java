package com.simple.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ResourceTest.class,
        ApplicationContextTest.class
})
public class AllTest {
}
