package com.wisely.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.SmallDemoMetaDataDao;
import com.wisely.domain.Demometadata;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class SmallDemoMetaDataService {

	@Autowired
	private SmallDemoMetaDataDao dao;
	
	public boolean ifExits(Demometadata entity) {
		int size = dao.CountBySamplenameAndTempartureAndPressAndBackgroundType(entity.getSamplename(), entity.getTemparture(), entity.getPress(), entity.getBackgroundtype(),entity.isSmall());
		if(size>0) {
			return true;
			
		}else {
			return false;
			
		}
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
			sample.put("samplelogo", li.getSamplelogo());
			sample.put("sampledesc", li.getSampledescription());
			back.put("backgroundtype", li.getBackgroundtype());
			back.put("backgroundtyplogo", li.getBackgroundtyplogo());
			arraySample.add(sample);
			arrayBack.add(back);
		}
		res.put("samples", arraySample);
		res.put("background", arrayBack);
		return res;
	}
	
	
}
