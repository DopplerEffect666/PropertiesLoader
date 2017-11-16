package com.shumanov.bojidar.PropertiesReader.interfaces;

public interface Property {
    <P extends Property> P propertyFactory();
}