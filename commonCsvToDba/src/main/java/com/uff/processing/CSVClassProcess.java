package com.uff.processing;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.uff.CSVDaoException;
import com.uff.config.classes.CSVClassMapping;
import com.uff.config.classes.converter.AbstractCSVPropertyConverter;
import com.uff.config.classes.converter.CSVPropertyConverter;
import com.uff.config.classes.property.CSVColumnProperty;
import com.uff.config.classes.property.UlysseCSVColumnProperty;
import com.uff.config.matcher.RowMatcher;
import com.uff.logger.DetailMessage;
import com.uff.logger.Message;
import com.uff.reflection.ReflectionHelper;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class CSVClassProcess extends AbstractTextProcess {

	private static Logger logger = LogManager.getLogger(CSVClassProcess.class);
	private CSVClassMapping mapping;

	private List<Object> instances;
	
	private List<Message> messages;

	public CSVClassProcess(CSVClassMapping mapping) {
		super(mapping.getDelimiter(), mapping.getLineComment(), mapping.getIgnoreFirstLine());
		this.instances = new ArrayList<Object>();
		messages = new ArrayList<>();
		this.mapping = mapping;
	}

	public List<Object> getInstances() {
		return instances;
	}

	public List<Message> getMessages() {
		return messages;
	}
	
	@Override
	public void processLineValues(String[] values, int numLigne) {
		RowMatcher rowMatcher = mapping.getRowMatcher();
		if (rowMatcher.useLine(values)) {

			Object instance = ReflectionHelper.createInstance(mapping.getClassName());
			List<? extends CSVColumnProperty> properties = mapping.getProperties();

			for (int i = 0; i < properties.size(); i++) {
				CSVColumnProperty csvColumnProperty = properties.get(i);
				boolean isValid = true;

				if (csvColumnProperty instanceof UlysseCSVColumnProperty) {
					UlysseCSVColumnProperty columnProperty = (UlysseCSVColumnProperty) csvColumnProperty;
					String value = values[csvColumnProperty.getColumn()];

					try {
						CSVPropertyConverter<?> converter;
						// test sur le type de champs
						if (columnProperty.getConverter() != null) {
							converter = getCustomConverter(columnProperty.getConverter());
						} else {
							Class<?> propertyType = ReflectionHelper.getType(instance.getClass(),
									csvColumnProperty.getProperty());
							converter = getConverter(propertyType);
						}
						// test required
						if (columnProperty.getRequired() != null && StringUtils.isEmpty(value)
								&& columnProperty.getRequired()) {
							isValid = false;
							logger.error("Erreur dans la classe: Required(" + mapping.getClassName() + ", " +columnProperty.getProperty()+","+value+")");
							Message message = new Message();
							message.setCode(numLigne+"_Required");
							message.setDetailMessage(new DetailMessage(numLigne, value, "Erreur dans la classe: Required(" + mapping.getClassName() + ", " +columnProperty.getProperty()+","+value+")"));
							messages.add(message);
							// throw new CSVDaoException("Erreur dans la classe
							// : Required(" + mapping.getClassName() + ", " +
							// columnProperty.getProperty()+","+value+")");
						} 
						// test sur le minLength
						if (columnProperty.getMinLength() != null &&  value.length() < columnProperty.getMinLength()) {
							isValid = false;
							logger.error("Erreur dans la classe : MinLengt(" + mapping.getClassName() + ", " + columnProperty.getProperty()+","+value+")");
							Message message = new Message();
							message.setCode(numLigne+"_MinLength");
							message.setDetailMessage(new DetailMessage(numLigne, value, "Erreur dans la classe : MinLengt(" + mapping.getClassName() + ", " + columnProperty.getProperty()+","+value+")"));
							messages.add(message);
							// throw new CSVDaoException("Erreur dans la classe
							// : MinLengt(" + mapping.getClassName() + ", " +
							// columnProperty.getProperty()+","+value+")");
						}
						// test sur le maxLength
						if (columnProperty.getMaxLength() != null &&  value.length() > columnProperty.getMaxLength()) {
							isValid = false;
							logger.error("Erreur dans la classe : MaxLength(" + mapping.getClassName() + ", " + columnProperty.getProperty()+","+value+")");
							Message message = new Message();
							message.setCode(numLigne+"_MaxLength");
							message.setDetailMessage(new DetailMessage(numLigne, value, "Erreur dans la classe : MaxLength(" + mapping.getClassName() + ", " + columnProperty.getProperty()+","+value+")"));
							messages.add(message);
							// throw new CSVDaoException("Erreur dans la classe
							// : MaxLength(" + mapping.getClassName() + ", " +
							// columnProperty.getProperty()+","+value+")");
						}
						if (isValid) {
							Object actualValue = converter.convertFrom(value);
							ReflectionHelper.setProperty(instance, csvColumnProperty.getProperty(), actualValue);
						} else {
							return;
						}
					} catch (CSVDaoException e) {
						logger.error("La valeur de la column "+value+"ne correspont au type configuré "+csvColumnProperty.getProperty());
						Message message = new Message();
						message.setCode(numLigne+"_TypeColumn");
						message.setDetailMessage(new DetailMessage(numLigne, value, "La valeur de la column "+value+"ne correspont au type configuré "+csvColumnProperty.getProperty()));
						messages.add(message);
						e.printStackTrace();
						continue;
					}
				}
			}
			if (!instances.contains(instance)) {
				instances.add(instance);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private CSVPropertyConverter<?> getCustomConverter(String converterName) {
		List<AbstractCSVPropertyConverter> converters = mapping.getConverters();
		if (converters != null) {
			for (AbstractCSVPropertyConverter converter : converters) {
				if (converter.getName().equals(converterName)) {
					return converter;
				}
			}
		}
		throw new CSVDaoException("Type inconnu (" + converterName + " , " + mapping.getClassName() + ")");
	}
}
