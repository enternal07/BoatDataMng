package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.TestModelObjDao;
import com.wisely.domain.big.TestModel;
import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.util.Toolkit;

@Service
public class TestModelObjService{

	@Autowired
	private TestModelObjDao dao;
	
	
	public  TestModelObjPO saveEntity(TestModelObjPO entity) {
		return dao.save(entity);
		
	}
	
	/**
	 * 修改外场测试数据实体
	 * @param entity
	 * @return
	 */
	public TestModelObjPO updateEntity(TestModelObjPO entity){
		TestModelObjPO result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			TestModelObjPO temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除外场测试
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
	}
	
	public TestModelObjPO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestModelObjPO> findAll() {
		return  dao.findAll();
	}

}
