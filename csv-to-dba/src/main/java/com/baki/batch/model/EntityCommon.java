package com.baki.batch.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class EntityCommon  implements Serializable {
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

}
