package com.shumanov.bojidar.test.PropertyProcessors;

import com.shumanov.bojidar.PropertiesReader.annotations.PropertySource;
import com.shumanov.bojidar.PropertiesReader.annotations.PropertySource.Property;
import com.shumanov.bojidar.PropertiesReader.interfaces.PropertiesProcessor;
import com.shumanov.bojidar.PropertiesReader.parsers.PropertyLoader;

@PropertySource(source="config/testConfig.properties", target=TestPropertiesProcessor.class)
public class TestPropertiesProcessor implements PropertiesProcessor {
    
    @Property(key="cfg.testPropery1")
    public String testPropery1;
    
    @Property(key="cfg.testPropery2")
    public String testPropery2;
    
    @Property(key="cfg.testPropery3")
    public String testPropery3;
    
    public void processProperties() {
        @SuppressWarnings("unused")
        PropertyLoader pl = new PropertyLoader(this);
    }
}
