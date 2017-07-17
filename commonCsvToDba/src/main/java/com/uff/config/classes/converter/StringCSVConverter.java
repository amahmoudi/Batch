package com.uff.config.classes.converter;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class StringCSVConverter extends AbstractCSVPropertyConverter<String> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String convertFrom(String text) {
        return text;
    }

    public String convertTo(String object) {
        return object;
    }
}
