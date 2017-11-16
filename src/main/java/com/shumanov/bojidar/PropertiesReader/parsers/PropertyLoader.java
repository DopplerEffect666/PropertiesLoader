package com.shumanov.bojidar.PropertiesReader.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import com.shumanov.bojidar.PropertiesReader.annotations.PropertySource;
import com.shumanov.bojidar.PropertiesReader.annotations.PropertySource.Property;

public class PropertyLoader {

    /*
     * public static void main(String args[]) throws Exception {
     * 
     * App app = new App(); doIt(app); System.out.println(app.backUpSource_1); }
     */

    public PropertyLoader(Object obj) {
        try {
            loadProperties(obj);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public PropertyLoader(Object obj, String source) {
        try {
            loadProperties(obj, source);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void loadProperties(Object obj) throws InstantiationException, IllegalAccessException {
        loadProperties(obj, null);
    }
    
    public void loadProperties(Object obj, String source) throws InstantiationException, IllegalAccessException {
        PropertySource ps = obj.getClass().getAnnotation(PropertySource.class);
        if (obj.getClass().isAnnotationPresent(PropertySource.class)) {
            
            Properties props;
            Field[] fields = obj.getClass().getFields();
            if (ps.source().endsWith(".lng") && null!=source && !source.trim().equals("")) {
                props = readProperties(source);
            } else {
                props = readProperties(ps.source());
            }
            
            
            for (Field f : fields) {
                if (f.isAnnotationPresent(Property.class)) {
                    Property property = f.getAnnotation(Property.class);
                    f.set(obj, props.get(property.key()));
                }
            }
        }
    }

    // old way of getting properties
    protected Properties readProperties(String filename) {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

}
