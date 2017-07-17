package com.baki.batch.service;

import java.util.List;

import com.uff.logger.Message;
import com.baki.batch.model.EntityCommon;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
public interface GenericService<T extends EntityCommon> {

	void save(Object entity);
	void saveAll(Class<T> type);
	void delete(Object entity);
	List<Message> findMessages(Class<T> type);
}
