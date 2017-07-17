package com.uff.config.classes.converter;

import com.uff.CSVDaoException;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class IntegerCSVConverter extends AbstractCSVPropertyConverter<Integer> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer convertFrom(String text) {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            throw new CSVDaoException("Unble to convert int " + text, e);
        }
    }

    public String convertTo(Integer object) {
        return String.valueOf(object);
    }
}
