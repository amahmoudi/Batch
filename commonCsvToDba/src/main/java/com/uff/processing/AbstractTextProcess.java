package com.uff.processing;

import java.util.HashMap;
import java.util.Map;

import com.uff.config.classes.converter.BooleanCSVConverter;
import com.uff.config.classes.converter.CSVPropertyConverter;
import com.uff.config.classes.converter.DecimalCSVConverter;
import com.uff.config.classes.converter.IntegerCSVConverter;
import com.uff.config.classes.converter.LongCSVConverter;
import com.uff.config.classes.converter.StringCSVConverter;
import com.uff.util.UlysseConstants;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public abstract class AbstractTextProcess implements TextProcess {

    private int lineCount;
    private String delimiter;
    private boolean ignoreFirstLine;
    private String comment;

    @SuppressWarnings("rawtypes")
	private Map<Class, CSVPropertyConverter> converterMap;

    @SuppressWarnings("rawtypes")
	public AbstractTextProcess(String delimiter, String comment, Boolean ignoreHeader) {
        this.delimiter = delimiter;
        this.comment = comment;
        ignoreFirstLine = Boolean.TRUE.equals(ignoreHeader);

        this.converterMap = new HashMap<Class, CSVPropertyConverter>();

        this.converterMap.put(Boolean.class, new BooleanCSVConverter());
        this.converterMap.put(String.class, new StringCSVConverter());
        this.converterMap.put(Integer.class, new IntegerCSVConverter());
        this.converterMap.put(Long.class, new LongCSVConverter());
        this.converterMap.put(Double.class, new DecimalCSVConverter());
    }

    protected CSVPropertyConverter<?> getConverter(Class<?> type){
        return this.converterMap.get(type);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public boolean isIgnoreFirstLine() {
        return ignoreFirstLine;
    }

    public void setIgnoreFirstLine(boolean ignoreFirstLine) {
        this.ignoreFirstLine = ignoreFirstLine;
    }

    final public void processLine(String line) {

        if ((lineCount<= (UlysseConstants.START_LINE-1)) && ignoreFirstLine && !line.startsWith(comment)) {
            lineCount++;
        } else if (!line.startsWith(comment)) {
            lineCount++;
            String[] values = line.split(delimiter);
            trimAll(values);
            if(values.length > UlysseConstants.MIN_NOMBRE_CHAMPS) {
                processLineValues(values, lineCount);
            }
        }
    }

    protected void trimAll(String[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = list[i].trim();
        }
    }

    public abstract void processLineValues(String[] values, int numLigne);
}
