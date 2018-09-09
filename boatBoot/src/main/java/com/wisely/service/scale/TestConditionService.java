package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.TestConditionDao;
import com.wisely.domain.scale.TestConditionPO;

@Service
public class TestConditionService{

	@Autowired
	private TestConditionDao dao;
	
	public  TestConditionPO saveEntity(TestConditionPO entity) {
		return dao.save(entity);
		
	}
	public TestConditionPO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestConditionPO> findAll() {
		return  dao.findAll();
	}

}
