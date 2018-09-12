package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.TestModelDao;
import com.wisely.domain.big.TestModel;

@Service
public class TestModelService{

	@Autowired
	private TestModelDao dao;
	
	
	public TestModel saveEntity(TestModel entity) {
		return dao.save(entity);
		
	}
	public TestModel getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestModel> findAll() {
		return  dao.findAll();
	}
}
