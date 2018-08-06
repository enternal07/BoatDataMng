package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BigDemoMetadataDao;
import com.wisely.dao.TestModelDao;
import com.wisely.domain.BigDemoMetadata;
import com.wisely.domain.TestModel;

@Service
public class BigDemoMetadataService{

	@Autowired
	private BigDemoMetadataDao dao;
	
	
	public BigDemoMetadata saveEntity(BigDemoMetadata entity) {
		return dao.save(entity);
		
	}
	public boolean ifExist(BigDemoMetadata entity) {
		if(dao.CountBySamplenameAndTempartureAndPressAndTestNames(entity.getSamplename(), entity.getTemparture(), 
				entity.getPress(), entity.getTestmodelName(), entity.getTestsystemName())>0){
			return true;
		}else {
			return false;
		}
		
	}
	
	public List<BigDemoMetadata> findAll() {
		return  dao.findAll();
	}
}
