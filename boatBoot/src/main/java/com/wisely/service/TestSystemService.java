package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.TestSystemDao;
import com.wisely.domain.big.TestModel;
import com.wisely.domain.big.TestSystem;
import com.wisely.util.Toolkit;

@Service
public class TestSystemService{

	@Autowired
	private TestSystemDao dao;
	
	public TestSystem saveEntity(TestSystem entity) {
		return dao.save(entity);
	}
	
	/**
	 * 修改背衬模型数据实体
	 * @param entity
	 * @return
	 */
	public TestSystem updateEntity(TestSystem entity){
		TestSystem result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			TestSystem temp = dao.findOne(entity.getPk());
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
	
	public TestSystem getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestSystem> findAll() {
		return  dao.findAll();
	}
}
