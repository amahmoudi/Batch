package com.uff.config.classes.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@XmlType(propOrder = {"primaryKey", "converter","minLength","maxLength","required"})
public class UlysseCSVColumnProperty extends CSVColumnProperty {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String converter;
    private Boolean primaryKey;
    private Integer minLength;
    private Integer maxLength;
    private Boolean required;
    
    
    public String getConverter() {
        return converter;
    }

    @XmlAttribute(name = "converter")
    public void setConverter(String converter) {
        this.converter = converter;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    @XmlAttribute(name = "primaryKey")
    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

	public Integer getMinLength() {
		return minLength;
	}
	@XmlAttribute(name = "minLength")
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	
	public Integer getMaxLength() {
		return maxLength;
	}
	@XmlAttribute(name = "maxLength")
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	
	public Boolean getRequired() {
		return required;
	}
	@XmlAttribute(name = "required")
	public void setRequired(Boolean required) {
		this.required = required;
	}

	@Override
	public String toString() {
		return String.format(
				"SimpleCSVColumnProperty [converter=%s, primaryKey=%s, minLength=%s, maxLength=%s, required=%s]",
				converter, primaryKey, minLength, maxLength, required);
	}
    
}
