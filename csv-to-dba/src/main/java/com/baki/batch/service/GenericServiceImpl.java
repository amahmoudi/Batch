package com.baki.batch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uff.logger.Message;
import com.baki.batch.dao.GenericDao;
import com.baki.batch.model.EntityCommon;

/**
 * 
 * @author abdelbaki_mahmoudi
 *
 */
@Service("GenericService")
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class GenericServiceImpl<T extends EntityCommon> implements GenericService<T> {

	@Autowired
	private GenericDao dao;

	public void save(Object CRE5A001) {
		dao.save(CRE5A001);
	}

	public void delete(Object ssn) {
		dao.delete(ssn);
	}

	@Override
	public void saveAll(Class<T> type) {
		dao.saveAll(type);
	}
	@Override
	public List<Message> findMessages(Class<T> type) {
		return dao.findMessages(type);
	}
	

}
