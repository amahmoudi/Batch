package com.uff;

import java.util.ArrayList;
import java.util.List;

import com.uff.config.CSVDaoConfig;
import com.uff.config.CSVMappingFiles;
import com.uff.config.JAXBHelper;
import com.uff.config.classes.CSVClassMapping;

/**
 * 
 * @author abdelbaki_mahmoudi
 *CSVDaoFactory
 */
public class CSVDaoFactory {

	private static CSVDaoConfig config;
	
//par défaut le fichier de configuration :csv-config.xml
	public CSVDaoFactory() {
		this("/csv-config.xml");
	}

	/**
	 * Load config files ou ressources
	 * 
	 * @param configurationFile
	 */
	public CSVDaoFactory(String configurationFile) {

		// load ressources
		config = (CSVDaoConfig) JAXBHelper.loadResource(configurationFile, CSVDaoConfig.class);

		if (config.getClassMappings() == null) {
			CSVMappingFiles mappingFiles = config.getClassMappingFiles();
			List<String> files = mappingFiles.getFiles();
			List<CSVClassMapping> classMappings = new ArrayList<CSVClassMapping>();
			if (mappingFiles.getType().equals("resource")) {
				for (String s : files) {
					classMappings.add((CSVClassMapping) JAXBHelper.loadResource(s, CSVClassMapping.class));
				}
			} 
			config.setClassMappings(classMappings);
		}
	}

	public CSVDaoConfig getConfig() {
		return config;
	}

	public void setConfig(CSVDaoConfig freshConfig) {
		config = freshConfig;
	}

	public CSVClassMapping getMapping(String className) {
		List<CSVClassMapping> mappings = config.getClassMappings();
		for (int i = 0; i < mappings.size(); i++) {
			CSVClassMapping mapping = mappings.get(i);
			if (mapping.getClassName().equals(className)) {
				return mapping;
			}
		}
		throw new CSVDaoException("Mapping introuvable: " + className);
	}

	public CSVClassMapping getMapping(Class<?> clazz) {
		return getMapping(clazz.getName());
	}

}
