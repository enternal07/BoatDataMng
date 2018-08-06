package com.wisely.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wisely.dao.ItemDao;
import com.wisely.domain.Item;
import com.wisely.domainVO.ItemBaseVO;
import com.wisely.domainVO.QueryVO;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class ItemService {

	@Autowired
	private ItemDao dao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Page<Item> findAll(Pageable page) {
		return dao.findAll(page);
	}
	
	public Page<Item> findAuto(Item item,Pageable page){
		return dao.findByAuto(item, page);
	}
	
	public List<Item> findByConditon(Item item,int min,int max){
		return dao.findAllByCondtion(item,min,max);
	}
	
	public JSONObject findByQueryCondtion(QueryVO queryVO){
		System.out.println(queryVO.getSamplename()+
				queryVO.getBackgroundtype()+queryVO.getRateMin()+queryVO.getRateMax()+
				queryVO.getTemparture()+queryVO.getPress());
		List<Item> list = 	dao.getObjectListByContions(queryVO.getRateMin(), queryVO.getRateMax(),
				queryVO.getSamplename(), queryVO.getBackgroundtype(), queryVO.getPress(),queryVO.getTemparture());
		//List<ItemBaseVO> vos = new ArrayList<ItemBaseVO>();
		ItemBaseVO vi = new ItemBaseVO();
		//List<Integer> rates = new ArrayList<Integer>();
//		Map<Integer,Float>   refects = new  HashMap<Integer,Float>();
//		Map<Integer,Float>   transmissions = new  HashMap<Integer,Float>();
//		Map<Integer,Float>   bondacusts = new  HashMap<Integer,Float>();
		JSONObject res = new JSONObject();
		
		JSONArray   refects = new JSONArray();
		JSONArray   transmissions = new  JSONArray();
		JSONArray  bondacusts = new  JSONArray();
		JSONArray   rates = new JSONArray();
		JSONArray   refect = new JSONArray();
		JSONArray   transmission = new JSONArray();
		JSONArray    bondacust = new JSONArray();
		float tatoulRef = 0;
		float tatoulTra = 0;
		float tatoulBon = 0;
		for(Item li:list) {
			tatoulRef = tatoulRef+li.getRefect();
			tatoulTra = tatoulTra+li.getTransmission();
			tatoulBon = tatoulBon+li.getBondacust();
			JSONArray ref = new JSONArray();
			ref.add(li.getRate());
			ref.add(li.getRefect());
			refects.add(ref);
			JSONArray tra = new JSONArray();
			tra.add(li.getRate());
			tra.add(li.getTransmission());
			transmissions.add(tra);
			JSONArray b = new JSONArray();
			b.add(li.getRate());
			b.add(li.getBondacust());
			bondacusts.add(b);
			rates.add(li.getRate());
			refect.add(li.getRefect());
			transmission.add(li.getTransmission());
			bondacust.add(li.getBondacust());
		}
//		vi.setRates(rates);
//		vi.setBondacusts(bondacusts);
//		vi.setTransmissions(transmissions);
//		vi.setRefects(refects);
		res.put("bondacusts", bondacusts);
		res.put("transmissions", transmissions);
		res.put("refects", refects);
		res.put("bondacust", bondacust);
		res.put("transmission", transmission);
		res.put("refect", refect);
		res.put("rate", rates);
		res.put("bondAvrg",tatoulBon /list.size());
		res.put("refectAvrg", tatoulRef/tatoulRef);
		res.put("traAvrg", tatoulTra/tatoulRef);
		return res;
	}
	public List<Item> saveAll(List<Item> pos){
		return dao.save(pos);
	}
}
