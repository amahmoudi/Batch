package com.uff.config.classes;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.uff.CSVDaoException;
import com.uff.config.classes.converter.AbstractCSVPropertyConverter;
import com.uff.config.classes.converter.BooleanCSVConverter;
import com.uff.config.classes.converter.CustomCSVPropertyConverter;
import com.uff.config.classes.converter.DateCSVPropertyConverter;
import com.uff.config.classes.converter.DecimalCSVConverter;
import com.uff.config.classes.converter.IntegerCSVConverter;
import com.uff.config.classes.converter.LongCSVConverter;
import com.uff.config.classes.converter.StringCSVConverter;
import com.uff.config.classes.property.CSVColumnProperty;
import com.uff.config.classes.property.UlysseCSVColumnProperty;
import com.uff.config.matcher.AllRowMatcher;
import com.uff.config.matcher.ColumnPropertyRowMatcher;
import com.uff.config.matcher.CountRowMatcher;
import com.uff.config.matcher.CustomRowMatcher;
import com.uff.config.matcher.RowMatcher;


/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@XmlType(propOrder = {"className", "rowMatcher", "properties", "converters"})
@XmlRootElement(name = "CSVMapping")
public class CSVClassMapping implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String className;
    private String csvFile;
    private String delimiter;
    private Boolean ignoreFirstLine;
    private String lineComment;

    private RowMatcher rowMatcher;
    private List<? extends CSVColumnProperty> properties;
    @SuppressWarnings("rawtypes")
	private List<AbstractCSVPropertyConverter> converters;

    public CSVClassMapping() {
        this.lineComment = "#";
        this.delimiter = ",";
    }

    public String getClassName() {
        return className;
    }

    @XmlAttribute(name = "className")
    public void setClassName(String className) {
        this.className = className;
    }


    public String getCsvFile() {
        return csvFile;
    }


    public String getLineComment() {
        return lineComment;
    }

    @XmlAttribute(name = "lineComment")
    public void setLineComment(String lineComment) {
        this.lineComment = lineComment;
    }

    @XmlAttribute(name = "csvFile")
    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public String getDelimiter() {
        return delimiter;
    }

    @XmlAttribute(name = "delimiter")
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public Boolean getIgnoreFirstLine() {
        return ignoreFirstLine;
    }

    @XmlAttribute(name = "ignoreFirstLine")
    public void setIgnoreFirstLine(Boolean ignoreFirstLine) {
        this.ignoreFirstLine = ignoreFirstLine;
    }

    public RowMatcher getRowMatcher() {
        return rowMatcher;
    }


    @XmlElements({
            @XmlElement(name = "matchByColumnCount", type = CountRowMatcher.class),
            @XmlElement(name = "matchByColumn", type = ColumnPropertyRowMatcher.class),
            @XmlElement(name = "matchCustom", type = CustomRowMatcher.class),
            @XmlElement(name = "matchAll", type = AllRowMatcher.class)

    })
    public void setRowMatcher(RowMatcher rowMatcher) {
        this.rowMatcher = rowMatcher;
    }

    public List<? extends CSVColumnProperty> getProperties() {
        return properties;
    }

    @XmlElements({ @XmlElement(name = "property", type = UlysseCSVColumnProperty.class)})
    @XmlElementWrapper(name = "properties")
    public void setProperties(List<? extends CSVColumnProperty> properties) {
        this.properties = properties;
    }

    @SuppressWarnings("rawtypes")
	public List<AbstractCSVPropertyConverter> getConverters() {
        return converters;
    }

    @XmlElements({
            @XmlElement(name = "customConverter", type = CustomCSVPropertyConverter.class),
            @XmlElement(name = "dateConverter", type = DateCSVPropertyConverter.class),
            @XmlElement(name = "decimalConverter", type = DecimalCSVConverter.class),
            @XmlElement(name = "booleanConverter", type = BooleanCSVConverter.class),
            @XmlElement(name = "integerConverter", type = IntegerCSVConverter.class),
            @XmlElement(name = "stringConverter", type = StringCSVConverter.class),
            @XmlElement(name = "longConverter", type = LongCSVConverter.class)
            
    })
    @XmlElementWrapper(name = "converters")
    public void setConverters(@SuppressWarnings("rawtypes") List<AbstractCSVPropertyConverter> converters) {
        this.converters = converters;
    }
}
