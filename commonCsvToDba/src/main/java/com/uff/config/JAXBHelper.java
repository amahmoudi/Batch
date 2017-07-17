package com.uff.config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.uff.CSVDaoException;
/**
 * Classe utilitaire pour le load de des CSV-CONFIG file et chargement de toute la config
 * @author abdelbaki_mahmoudi
 *
 */
public class JAXBHelper {
	
    public static Object loadResource(String resource, Class<?> type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object data = jaxbUnmarshaller.unmarshal(type.getResourceAsStream(resource));
            return data;
        } catch (JAXBException e) {
            throw new CSVDaoException("problème de configuration " + resource, e);
        }
    }

    public static Object loadFile(String file, Class<?> type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object data = jaxbUnmarshaller.unmarshal(new File(file));
            return data;
        } catch (JAXBException e) {
            throw new CSVDaoException("problème de configuration  " + file, e);
        }
    }
}
