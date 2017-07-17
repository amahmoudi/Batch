package com.uff.config.classes.converter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public abstract class AbstractCSVPropertyConverter<T> implements CSVPropertyConverter<T> {

    private String name;

    @XmlAttribute(name = "converterName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
