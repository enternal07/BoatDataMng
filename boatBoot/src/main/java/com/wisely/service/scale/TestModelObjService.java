package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.TestModelObjDao;
import com.wisely.domain.scale.TestModelObjPO;

@Service
public class TestModelObjService{

	@Autowired
	private TestModelObjDao dao;
	
	
	public  TestModelObjPO saveEntity(TestModelObjPO entity) {
		return dao.save(entity);
		
	}
	public TestModelObjPO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestModelObjPO> findAll() {
		return  dao.findAll();
	}

}
