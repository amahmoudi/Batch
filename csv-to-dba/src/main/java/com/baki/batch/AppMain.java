package com.baki.batch;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.uff.CSVDao;
import com.baki.batch.configuration.AppConfig;
import com.baki.batch.model.CRE5A001;
import com.baki.batch.model.CRE5A010;
import com.baki.batch.service.GenericService;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public class AppMain {

	private static Logger logger = LogManager.getLogger(AppMain.class);
	private static GenericService service;

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		service = (GenericService) context.getBean("GenericService");
		CSVDao CSVDao = (CSVDao) context.getBean("CSVDao");

		logger.info("*************************** CRE5A001******************************************");
		// sauvegarder tous les entity
		service.saveAll(CRE5A001.class);

		/// find all clazz
		List<CRE5A001> clazz = CSVDao.find(CRE5A001.class);
		logger.info(clazz);

		// list des messages
		List findMessages = service.findMessages(CRE5A001.class);

		logger.info(findMessages);

		logger.info("*************************** CRE5A010******************************************");
		// sauvegarder tous les entity
		service.saveAll(CRE5A010.class);

		/// find all clazz
		List<CRE5A010> clazzCRE5A010 = CSVDao.find(CRE5A010.class);
		logger.info(clazzCRE5A010);

		// list des messages
		List findMsgCRE5A001 = service.findMessages(CRE5A010.class);

		logger.info(findMsgCRE5A001);

		// fermeture de context
		context.close();

		// envoie la liste des messages sous format json

		ObjectMapper mapper = new ObjectMapper();
		String outputAsJson = "";
		try {
			outputAsJson = mapper.writeValueAsString(findMessages);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(outputAsJson);

		/// on peut utiliser restTemplete pour envoyer service avec json data
		/**
		 * RESTServer mRESTServer = context.getBean(RESTServer.class);
		 * RestTemplate rt = new RestTemplate();
		 * rt.getMessageConverters().add(new
		 * MappingJacksonHttpMessageConverter());
		 * rt.getMessageConverters().add(new StringHttpMessageConverter());
		 * String uri = new String("http://" + mRESTServer.getHost() +
		 * ":8080/appliExemple/receptiondata"); Message returns =
		 * rt.postForObject(uri, findMessages, Message.class);
		 **/

	}
}
