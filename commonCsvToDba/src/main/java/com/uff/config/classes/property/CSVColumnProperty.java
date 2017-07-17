package com.uff.config.classes.property;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */

@XmlType(propOrder = {"column", "property"})
public class CSVColumnProperty implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int column;
    private String property;

    public CSVColumnProperty() {
        column = -1; //default -1 si column non mappé
    }

    public int getColumn() {
        return column;
    }

    @XmlAttribute(name = "index")
    public void setColumn(int column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    @XmlAttribute(name = "property")
    public void setProperty(String property) {
        this.property = property;
    }

	@Override
	public String toString() {
		return String.format("CSVColumnProperty [column=%s, property=%s]", column, property);
	}

    
}
