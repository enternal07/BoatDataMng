package com.wisely.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.ContractionMetadataDao;
import com.wisely.dao.SmallDemoMetaDataDao;
import com.wisely.domain.ContractionMetadata;
import com.wisely.domain.Demometadata;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class ContractionMetadataService{

	@Autowired
	private ContractionMetadataDao dao;
	
	public boolean ifExits(ContractionMetadata entity) {
		int size = dao.CountByCons(entity.getSamplename(), entity.getBackgroundtype(), entity.getTesttime());
		if(size>0) {
			return true;
			
		}else {
			return false;
			
		}
	}
	public ContractionMetadata saveEntity(ContractionMetadata entity) {
		return dao.save(entity);
		
	}
	
	public JSONObject findAll() {
		List<ContractionMetadata> list = dao.findAll();
		JSONObject res = new JSONObject();
		JSONArray arraySample = new JSONArray();
		JSONArray arrayBack = new JSONArray();
		JSONArray arrayts = new JSONArray();
		for(ContractionMetadata li:list) {
			JSONObject sample = new JSONObject();
			JSONObject back = new JSONObject();
			JSONObject ts = new JSONObject();
			sample.put("samplename", li.getSamplename());
			back.put("backgroundtype", li.getBackgroundtype());
			ts.put("testtime", li.getTesttime());
			arraySample.add(sample);
			arrayBack.add(back);
			arrayts.add(ts);
		}
		res.put("samples", arraySample);
		res.put("background", arrayBack);
		res.put("testtime", arrayts);
		return  res;
	}
}
