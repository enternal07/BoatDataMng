package com.wisely.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.SmallDemoMetaDataDao;
import com.wisely.domain.Demometadata;
import com.wisely.domainVO.ResultVO;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class SmallDemoMetaDataService {

	@Autowired
	private SmallDemoMetaDataDao dao;
	
	public ResultVO ifExits(Demometadata entity) {
		List<Object> resObj = dao.CountBySamplenameAndTempartureAndPressAndBackgroundType(entity.getSamplename(), entity.getTemparture(), entity.getPress(), entity.getBackgroundtype(),entity.isSmall());
		ResultVO res = new ResultVO();
			
			for(Object met:resObj) {
				Object[] mest =(Object[]) met;
				long size = (long) mest[0];
				if(size>0) {
					res.setSuccess(true);
					res.setData(mest[1]);
				}else {
					res.setSuccess(false);
				}
				
			}
			return res;
			
	}
	public Demometadata saveEntity(Demometadata entity) {
		return dao.save(entity);
		
	}
	
	public JSONObject findBySmall(boolean small) {
		List<Demometadata> list = dao.findBySmall(small);
		JSONObject res = new JSONObject();
		JSONArray arraySample = new JSONArray();
		JSONArray arrayBack = new JSONArray();
	
		for(Demometadata li:list) {
			JSONObject sample = new JSONObject();
			JSONObject back = new JSONObject();
			sample.put("samplename", li.getSamplename());
			back.put("backgroundtype", li.getBackgroundtype());
			arraySample.add(sample);
			arrayBack.add(back);
		}
		res.put("samples", arraySample);
		res.put("background", arrayBack);
		return res;
	}
	
	
}
