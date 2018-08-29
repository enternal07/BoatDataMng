package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BigDemoMetadataDao;
import com.wisely.dao.TestModelDao;
import com.wisely.domain.BigDemoMetadata;
import com.wisely.domain.TestModel;
import com.wisely.domainVO.ResultVO;

@Service
public class BigDemoMetadataService{

	@Autowired
	private BigDemoMetadataDao dao;
	
	
	public BigDemoMetadata saveEntity(BigDemoMetadata entity) {
		return dao.save(entity);
		
	}
	public ResultVO ifExist(BigDemoMetadata entity) {
		
		List<Object> resEntity = dao.CountBySamplenameAndTempartureAndPressAndTestNames(entity.getSamplename(), entity.getTemparture(), 
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
