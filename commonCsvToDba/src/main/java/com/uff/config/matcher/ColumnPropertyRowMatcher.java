package com.uff.config.matcher;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *  pour exclure un column
 * @author abdelbaki_mahmoudi
 *
 */
@XmlType(propOrder = {"expectedColumn", "expectedValue"})
public class ColumnPropertyRowMatcher extends RowMatcher {

    private int expectedColumn;
    private String expectedValue;

    public ColumnPropertyRowMatcher() {
    }

    public int getExpectedColumn() {
        return expectedColumn;
    }

    @XmlAttribute
    public void setExpectedColumn(int expectedColumn) {
        this.expectedColumn = expectedColumn;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    @XmlAttribute
    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean useLine(String[] cols) {

        if (expectedColumn < cols.length) {
            return expectedValue.equals(cols[expectedColumn]);
        }
        return false;
    }
}
