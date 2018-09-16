package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.TestConditionDao;
import com.wisely.domain.scale.TestConditionPO;
import com.wisely.util.Toolkit;

@Service
public class TestConditionService{

	@Autowired
	private TestConditionDao dao;
	
	public  TestConditionPO saveEntity(TestConditionPO entity) {
		return dao.save(entity);
	}
	/**
	 * 修改试验情况数据实体
	 * @param entity
	 * @return
	 */
	public TestConditionPO updateEntity(TestConditionPO entity){
		TestConditionPO result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			TestConditionPO temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除试验情况
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
	}
	
	public TestConditionPO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestConditionPO> findAll() {
		return  dao.findAll();
	}

}
