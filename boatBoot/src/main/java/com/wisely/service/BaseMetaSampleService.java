package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaSampleDao;
import com.wisely.domain.BaseMetaSample;

@Service
public class BaseMetaSampleService{

	@Autowired
	private BaseMetaSampleDao dao;
	
	
	public BaseMetaSample saveEntity(BaseMetaSample entity) {
		return dao.save(entity);
		
	}
	public BaseMetaSample getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<BaseMetaSample> findAll() {
		return  dao.findAll();
	}
	
	public List<BaseMetaSample> findSmall() {
		return  dao.findBySmall(true);
	}
	
	public List<BaseMetaSample> findBig() {
		return  dao.findBySmall(false);
	}
}
