package com.uff.config.classes.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;
import javax.xml.bind.annotation.XmlAttribute;

import com.uff.CSVDaoException;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class DateCSVPropertyConverter extends AbstractCSVPropertyConverter<Date> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String format;
    private DateFormatter dateFormatter;

    public String getFormat() {
        return format;
    }

    @XmlAttribute(name = "format")
    public void setFormat(String format) {
        this.format = format;
    }

    public Date convertFrom(String text) {

        try {
            if (this.dateFormatter == null) {
                this.dateFormatter = new DateFormatter(new SimpleDateFormat(this.format));
            }

            return (Date) this.dateFormatter.stringToValue(text);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new CSVDaoException("Cannot convert text to date", e);
        }
    }

    public String convertTo(Date object) {
        try {
            if (this.dateFormatter == null) {
                this.dateFormatter = new DateFormatter(new SimpleDateFormat());
            }
            return this.dateFormatter.valueToString(object);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new CSVDaoException("Cannot convert date to text",e);
        }
    }


}
