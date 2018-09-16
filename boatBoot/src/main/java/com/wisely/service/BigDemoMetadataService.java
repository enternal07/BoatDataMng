package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BigDemoMetadataDao;
import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domainVO.ResultVO;
import com.wisely.util.Toolkit;

@Service
public class BigDemoMetadataService{

	@Autowired
	private BigDemoMetadataDao dao;
	
	/**
	 * 添加大样数据实体
	 * @param entity
	 * @return
	 */
	public BigDemoMetadata saveEntity(BigDemoMetadata entity) {
		return dao.save(entity);
		
	}
	/**
	 * 修改大样数据实体
	 * @param entity
	 * @return
	 */
	public BigDemoMetadata updateEntity(BigDemoMetadata entity){
		BigDemoMetadata result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			BigDemoMetadata temp = dao.findOne(entity.getPk());
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
	public ResultVO ifExist(BigDemoMetadata entity) {
		
		List<Object> resEntity = dao.CountBySamplenameAndTempartureAndPressAndTestNames(entity.getSampleName(), entity.getTemparture(), 
				entity.getPress(), entity.getTestmodelName(), entity.getTestsystemName());
		ResultVO res = new ResultVO();
		  Object ress=resEntity.get(0);
			Object[] arry = (Object[]) ress;
			long size = (long) arry[0];
			if(size>0) {
				res.setSuccess(true);
				res.setData(arry[1]);
			}else {
				res.setSuccess(false);
			}
			return res;
		}
	public List<BigDemoMetadata> findAll() {
		return  dao.findAll();
	}
}
