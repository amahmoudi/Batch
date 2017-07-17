package com.baki.batch.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uff.CSVDao;
import com.uff.logger.Message;
import com.baki.batch.model.EntityCommon;
/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@Repository("GenericDao")
public class GenericDaoImpl<T extends EntityCommon> extends AbstractDao implements GenericDao<T>{

	@Autowired
	private CSVDao csvDao;
	
	public void delete(Object entity) {
		delete(entity);
	}

	public void save(Object entity) {
		persist(entity);
		
	}

	@Override
	public  void saveAll(Class<T> type) {
		List<T> lstCre = csvDao.find(type);
		for (int i = 0; i < lstCre.size(); i++) {
			T entity = lstCre.get(i);
			save(entity);
		}
	}

	@Override
	public List<Message> findMessages(Class<T> type) {
		List<Message> findMessages = csvDao.findMessages(type);
		return findMessages;
	}
}
