package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.LayingSchemeDao;
import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.util.Toolkit;

@Service
public class LayingSchemeService {

	@Autowired
	private LayingSchemeDao dao;

	public  LayingSchemePO saveEntity(LayingSchemePO entity) {
		return dao.save(entity);
	}
	
	/**
	 * 修改敷设方案数据实体
	 * @param entity
	 * @return
	 */
	public LayingSchemePO updateEntity(LayingSchemePO entity){
		LayingSchemePO result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			LayingSchemePO temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除敷设方案
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
	}
	
	
	public LayingSchemePO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<LayingSchemePO> findAll() {
		return  dao.findAll();
	}
}
