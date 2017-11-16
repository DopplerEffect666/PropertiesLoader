package com.shumanov.bojidar.test.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.shumanov.bojidar.test.PropertyProcessors.TestPropertiesProcessor;

public class TestPropertyObject {

    public static TestPropertiesProcessor propProcessor; 
    
    public void loadTestProperties() {
        propProcessor = new TestPropertiesProcessor();
        propProcessor.processProperties();
    }
    
    @Test
    public void test() {

        loadTestProperties();
        
        assertEquals("test value 1", propProcessor.testPropery1);
        assertEquals("test value 2", propProcessor.testPropery2);
        assertEquals("test value 3", propProcessor.testPropery3);
    }

}
