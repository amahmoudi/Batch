package com.uff.config.matcher;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@XmlType(propOrder = {"expectedColumns"})
public class CountRowMatcher extends RowMatcher {

    private int expectedColumns;

    public int getExpectedColumns() {
        return expectedColumns;
    }

    @XmlAttribute
    public void setExpectedColumns(int expectedColumns) {
        this.expectedColumns = expectedColumns;
    }

    @Override
    public boolean useLine(String[] cols) {
        return cols.length == expectedColumns;
    }
}
