package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.TestModelDao;
import com.wisely.domain.big.TestModel;
import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.util.Toolkit;

@Service
public class TestModelService{

	@Autowired
	private TestModelDao dao;
	
	
	public TestModel saveEntity(TestModel entity) {
		return dao.save(entity);
	}
	/**
	 * 修改背衬模型数据实体
	 * @param entity
	 * @return
	 */
	public TestModel updateEntity(TestModel entity){
		TestModel result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			TestModel temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除背衬实体
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
	}
	
	public TestModel getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestModel> findAll() {
		return  dao.findAll();
	}
}
