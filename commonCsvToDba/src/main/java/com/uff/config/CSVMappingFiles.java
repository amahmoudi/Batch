package com.uff.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * class pour mapper la liste des fichier de config
 * @author abdelbaki_mahmoudi
 *
 */
public class CSVMappingFiles {

    private String type;
    private List<String> files;

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "fileType")
    public void setType(String type) {
        this.type = type;
    }

    public List<String> getFiles() {
        return files;
    }

    @XmlElement(name = "mappingFile")
    public void setFiles(List<String> files) {
        this.files = files;
    }
}
