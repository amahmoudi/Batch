package com.uff.config.classes.converter;

import javax.xml.bind.annotation.XmlAttribute;

import com.uff.CSVDaoException;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class CustomCSVPropertyConverter extends AbstractCSVPropertyConverter<Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String className;

    private AbstractCSVPropertyConverter<Object> customConverter;


    public String getClassName() {
        return className;
    }

    @XmlAttribute(name = "converterClass")
    public void setClassName(String className) {
        this.className = className;
    }

    public Object convertFrom(String text) {
        AbstractCSVPropertyConverter<Object> converter = getConverter();
        return converter.convertFrom(text);
    }

    public String convertTo(Object object) {
        AbstractCSVPropertyConverter<Object> converter = getConverter();
        return converter.convertTo(object);
    }

    @SuppressWarnings("unchecked")
	private AbstractCSVPropertyConverter<Object> getConverter() {
        if (customConverter == null) {
            try {
                customConverter = (AbstractCSVPropertyConverter<Object>) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                throw new CSVDaoException("Cannot create custom converter : " + className, e);
            } catch (IllegalAccessException e) {
                throw new CSVDaoException("Cannot create custom converter : " + className, e);
            } catch (ClassNotFoundException e) {
                throw new CSVDaoException("Cannot create custom converter : " + className, e);
            }
        }
        return customConverter;
    }
}
