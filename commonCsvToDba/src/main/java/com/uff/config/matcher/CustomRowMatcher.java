package com.uff.config.matcher;

import javax.xml.bind.annotation.XmlAttribute;

import com.uff.CSVDaoException;

/**
 *  pour pesonaliser la liste des row a matcher
 * @author abdelbaki_mahmoudi
 *
 */
public class CustomRowMatcher extends RowMatcher {

    private String className;
    private RowMatcher customMatcher;

    public String getClassName() {
        return className;
    }

    @XmlAttribute(name = "matcherClass")
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean useLine(String[] cols) {
        RowMatcher matcher = getMatcher();
        return matcher.useLine(cols);
    }

    private RowMatcher getMatcher() {
        if (customMatcher == null) {
            try {
                customMatcher = (RowMatcher) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                throw new CSVDaoException("Cannot create custom row matcher : " + className, e);
            } catch (IllegalAccessException e) {
                throw new CSVDaoException("Cannot create custom row matcher : " + className, e);
            } catch (ClassNotFoundException e) {
                throw new CSVDaoException("Cannot create custom row matcher : " + className, e);
            }
        }
        return customMatcher;
    }
}
