package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaBackingDao;
import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.util.Toolkit;

/**
 * @author liqz
 */
@Service
public class BaseMetaBackingService{

	@Autowired
	private BaseMetaBackingDao dao;
	
	public BaseMetaBacking saveEntity(BaseMetaBacking entity) {
		return dao.save(entity);
	}
	
	/**
	 * 修改背衬模型数据实体
	 * @param entity
	 * @return
	 */
	public BaseMetaBacking updateEntity(BaseMetaBacking entity){
		BaseMetaBacking result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			BaseMetaBacking temp = dao.findOne(entity.getPk());
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
	
	public List<BaseMetaBacking> findAll() {
		return dao.findAll();
	}
}
