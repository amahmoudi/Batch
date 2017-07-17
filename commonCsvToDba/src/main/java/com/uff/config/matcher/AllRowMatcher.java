package com.uff.config.matcher;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * la cas match all data dans le fichier csv
 * @author abdelbaki_mahmoudi
 *
 */
@XmlRootElement(name = "allRowMatcher")
public class AllRowMatcher extends RowMatcher {
    @Override
    public boolean useLine(String[] cols) {
        return true;
    }
}
