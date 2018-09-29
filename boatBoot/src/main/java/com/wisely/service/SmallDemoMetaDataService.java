package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaBackingDao;
import com.wisely.dao.ItemDao;
import com.wisely.dao.SmallDemoMetaDataDao;
import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.domain.small.Demometadata;
import com.wisely.domain.small.Item;
import com.wisely.domainVO.ResultVO;
import com.wisely.domainVO.mng.data.SmallItemVO;
import com.wisely.util.Toolkit;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class SmallDemoMetaDataService {

	@Autowired
	private SmallDemoMetaDataDao dao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private BaseMetaBackingDao backingDao;
	
	public ResultVO ifExits(Demometadata entity) {
		List<Object> resObj = dao.CountBySamplenameAndTempartureAndPressAndBackgroundType(entity.getSamplename(), entity.getTemparture(), entity.getPress(), entity.getBackingname(),entity.isSmall());
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
	/**
	 * 修改小样实体
	 * @param entity
	 * @return
	 */
	public Demometadata updateEntity(Demometadata entity){
		Demometadata result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			Demometadata temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除小样实体
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
	}
	/**
	 * 查询所有
	 */
	public List<SmallItemVO> findAllItem(){
		List<Item> allItems = itemDao.findAll();
		List<SmallItemVO> result = new ArrayList<>() ; 
		if(Toolkit.notEmpty(allItems)){
			for (Item item : allItems) {
				SmallItemVO smallItemVO = new SmallItemVO();
				Demometadata smallVO = item.getSmallPO(); 
				BaseMetaBacking backingVO = backingDao.findOne(smallVO.getBakingpk());
				smallItemVO.setPk(item.getPk()); 
				smallItemVO.setSamplename(smallVO.getSamplename());
				smallItemVO.setSamplepk(smallVO.getSamplepk());
				smallItemVO.setBackingname(backingVO.getName());
				smallItemVO.setBakingpk(smallVO.getBakingpk());
				smallItemVO.setTemparture(smallVO.getTemparture());
				smallItemVO.setPress(smallVO.getPress());
				smallItemVO.setBondacust(item.getBondacust());
				smallItemVO.setRate(item.getRate());
				smallItemVO.setRefect(item.getRefect());
				smallItemVO.setTransmission(item.getTransmission());
				result.add(smallItemVO);
			}
		}
		return result;
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
			back.put("backingname", li.getBackingname());
			arraySample.add(sample);
			arrayBack.add(back);
		}
		res.put("samples", arraySample);
		res.put("background", arrayBack);
		return res;
	}
	
	public Item saveItem(Item item){
		return itemDao.save(item);
	}
	
	public Item updateItem(Item item){
		Item result = null ; 
		if(Toolkit.notEmpty(item.getPk())){
			Item temp = itemDao.findOne(item.getPk());
			if(Toolkit.notEmpty(temp)){
				result = itemDao.save(item);
			}
		}
		return result;
	}
	
	public void deleteItem(String pk){
		if(Toolkit.notEmpty(pk)){
			itemDao.delete(pk);
		}
	}
	
	public void deleteItems(List<String> pks){
		if(Toolkit.notEmpty(pks)){
			itemDao.deleteByPks(pks);
		}
	}
	
	public List<Demometadata> queryFull(){
		return dao.findAll();
	}
	
	public Demometadata queryDetail(String pk){
		return dao.findOne(pk);
	}
	
}
