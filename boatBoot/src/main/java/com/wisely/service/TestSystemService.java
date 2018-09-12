package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.TestSystemDao;
import com.wisely.domain.big.TestSystem;

@Service
public class TestSystemService{

	@Autowired
	private TestSystemDao dao;
	
	
	public TestSystem saveEntity(TestSystem entity) {
		return dao.save(entity);
		
	}
	public TestSystem getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestSystem> findAll() {
		return  dao.findAll();
	}
}
