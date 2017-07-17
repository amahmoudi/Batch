package com.uff.config.classes.converter;

import com.uff.CSVDaoException;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class LongCSVConverter extends AbstractCSVPropertyConverter<Long> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long convertFrom(String text) {
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            throw new CSVDaoException("Unble to convert long " + text, e);
        }
    }

    public String convertTo(Long object) {
        return String.valueOf(object);
    }
}
