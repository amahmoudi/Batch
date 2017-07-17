package com.uff;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.uff.config.classes.CSVClassMapping;
import com.uff.logger.Message;
import com.uff.processing.CSVClassProcess;
import com.uff.reader.TextFileDataReader;
import com.uff.reflection.ReflectionHelper;
import com.uff.util.DateUtils;
import com.uff.util.UlysseConstants;

/**
 * 
 * @author abdelbaki_mahmoudi CSVDao
 */
public class CSVDao {

	@SuppressWarnings("rawtypes")
	/**
	 * map key:class correspond au fichier plat , value: liste des données de
	 * dans
	 **/
	private Map<Class, List> data = new HashMap<Class, List>();

	private Map<Class, List> messages = new HashMap<Class, List>();

	@SuppressWarnings("rawtypes")
	/** liste des classe : liste des fichiers plats **/
	private List<Class> classes = new ArrayList<Class>();
	/** csv dao factory **/
	private CSVDaoFactory factory;

	/**
	 * Constructeur pour faire initialiser le DAO
	 * 
	 * @param factory
	 */
	public CSVDao(CSVDaoFactory factory) {
		try {
			this.factory = factory;
			initialise(null);
		} catch (Exception e) {
			throw new CSVDaoException("Problème d'intialisation de DAO", e);
		}
	}

	/**
	 * Constructeur pour faire initialiser le DAO
	 * 
	 * @param factory
	 */
	public CSVDao(CSVDaoFactory factory, Properties properties) {
		try {
			this.factory = factory;
			initialise(properties);
		} catch (Exception e) {
			throw new CSVDaoException("Problème d'intialisation de DAO", e);
		}
	}

	/**
	 * Méthode d'initialisation de DAO
	 * 
	 * @throws Exception
	 */
	private void initialise(Properties properties) throws Exception {
		List<CSVClassMapping> mappings = factory.getConfig().getClassMappings();

		for (CSVClassMapping mapping : mappings) {
			// remplir la liste des class en recuperant le nom de l'entity
			classes.add(ReflectionHelper.createClass(mapping.getClassName()));
			CSVClassProcess pClass = new CSVClassProcess(mapping);

			TextFileDataReader reader = new TextFileDataReader();
			reader.register(pClass);
			String formatToYYYMMDD = null;
			if (properties == null) {
				Date myDate = new Date();
				formatToYYYMMDD = DateUtils.FormatToYYYMMDD(myDate);
			} else {
				formatToYYYMMDD = (String) properties.get("date.csvFile");
			}
			String mappinfFile = mapping.getCsvFile() + UlysseConstants.SEPERATORFILENAME + formatToYYYMMDD
					+ UlysseConstants.EXTENSIONCRE;
			reader.read(new FileInputStream(mappinfFile));
			// creation de class en utilisant la réflexion
			Class<?> type = ReflectionHelper.createClass(mapping.getClassName());
			// remplir la map
			// List<Object> lstFinal = new ArrayList<Object>();
			// lstFinal.add(pClass.getInstances());
			// lstFinal.add(pClass.getMessages());
			data.put(type, pClass.getInstances());
			messages.put(type, pClass.getMessages());

		}
		verifierEntitySaved();
	}

	/**
	 * Méthode pour vérifier les entity sauvegardés
	 */
	@SuppressWarnings("unchecked")
	private void verifierEntitySaved() {
		for (int i = 0; i < classes.size(); i++) {
			Class<?> classA = classes.get(i);

			for (@SuppressWarnings("rawtypes")
			Class classB : classes) {
				// a not egal a b
				if (!classA.equals(classB)) {
					// si b exends a ou b implements a
					if (classA.isAssignableFrom(classB)) {

						@SuppressWarnings("rawtypes")
						List a = data.get(classA);
						@SuppressWarnings("rawtypes")
						List b = data.get(classB);
						a.addAll(b);
					}
				}
			}

		}
	}

	/** recherche une entitée **/
	public <T> T get(Class<T> type) {
		return findUnique(type);
	}

	@SuppressWarnings("unchecked")
	/** recherche une liste d'entitées **/
	public <T> List<T> find(Class<T> type) {
		List<T> found = new ArrayList<T>();
		List<T> allData = this.data.get(type);
		for (T data : allData) {
			found.add(data);
		}
		return found;
	}

	@SuppressWarnings("unchecked")
	/** recherche une liste des messages **/
	public <T> List<Message> findMessages(Class<T> type) {
		return this.messages.get(type);
	}

	private <T> T findUnique(Class<T> type) {
		List<T> found = find(type);
		if (found.size() == 1) {
			return found.get(0);
		} else {
			throw new CSVDaoException("Expected: " + type + " trouvés:  " + found.size());
		}
	}

}
