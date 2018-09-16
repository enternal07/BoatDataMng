package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaSampleDao;
import com.wisely.domain.common.BaseMetaSample;
import com.wisely.util.Toolkit;

@Service
public class BaseMetaSampleService{

	@Autowired
	private BaseMetaSampleDao dao;
	
	public BaseMetaSample saveEntity(BaseMetaSample entity) {
		return dao.save(entity);
	}

	/**
	 * 修改缩放模型数据实体
	 * @param entity
	 * @return
	 */
	public BaseMetaSample updateEntity(BaseMetaSample entity){
		BaseMetaSample result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			BaseMetaSample temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除大样实体
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
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
