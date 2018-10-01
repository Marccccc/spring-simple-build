package com.simple.test;

import com.simple.core.io.ClassPathResource;
import com.simple.core.io.Resource;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceTest {

    @Test
    public void testClassPathResource() {
        ClassPathResource classPathResource = new ClassPathResource("application.xml");
        assertNotNull(classPathResource.getInputStream());
    }

}
