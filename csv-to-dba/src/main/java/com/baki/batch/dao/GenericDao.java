package com.baki.batch.dao;

import java.util.List;

import com.uff.logger.Message;
import com.baki.batch.model.EntityCommon;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public interface GenericDao <T extends EntityCommon>{

	void save(Object CRE5A001);
	void saveAll(Class<T> type);
	void delete(Object ssn);
	List<Message> findMessages(Class<T> type);
	
}
