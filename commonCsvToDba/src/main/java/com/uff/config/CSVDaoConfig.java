package com.uff.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.uff.config.classes.CSVClassMapping;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@XmlType(propOrder = { "classMappings", "classMappingFiles" })
@XmlRootElement(name = "CSVConfig")
public class CSVDaoConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CSVMappingFiles classMappingFiles;
	private List<CSVClassMapping> classMappings;

	public CSVDaoConfig() {
	}

	public List<CSVClassMapping> getClassMappings() {
		return classMappings;
	}

	@XmlElements({ @XmlElement(name = "mapping", type = CSVClassMapping.class)

	})
	@XmlElementWrapper(name = "mappings")
	public void setClassMappings(List<CSVClassMapping> classMappings) {
		this.classMappings = classMappings;
	}

	public CSVMappingFiles getClassMappingFiles() {
		return classMappingFiles;
	}

	@XmlElement(name = "mappingFiles")
	public void setClassMappingFiles(CSVMappingFiles classMappingFiles) {
		this.classMappingFiles = classMappingFiles;
	}

}
