package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaBackingDao;
import com.wisely.domain.small.BaseMetaBacking;

@Service
public class BaseMetaBackingService{

	@Autowired
	private BaseMetaBackingDao dao;
	
	
	public BaseMetaBacking saveEntity(BaseMetaBacking entity) {
		return dao.save(entity);
		
	}
	
	public List<BaseMetaBacking> findAll() {
		return dao.findAll();
	}
}
