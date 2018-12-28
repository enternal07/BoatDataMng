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
import com.wisely.domain.small.Item;
import com.wisely.domainVO.ItemBaseVO;
import com.wisely.domainVO.QueryVO;
import com.wisely.util.Toolkit;

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
	
	public List<ItemBaseVO> findByQueryCondtionOld(QueryVO queryVO){
		System.out.println(queryVO.getSamplename()+
				queryVO.getBackingname()+queryVO.getRateMin()+queryVO.getRateMax()+
				queryVO.getTemparture()+queryVO.getPress());
		List<Item> list = 	dao.getObjectListByContions(queryVO.getRateMin(), queryVO.getRateMax(),
				queryVO.getSamplename(), queryVO.getBackingname(), queryVO.getPress(),queryVO.getTemparture());
		List<ItemBaseVO> vos = new ArrayList<ItemBaseVO>();
		for(Item li:list) {
			ItemBaseVO vi = new ItemBaseVO();
			vi.setPk(li.getPk()); 
			vi.setMetaPK(li.getSmallPO().getPk()); 
			vi.setRate(li.getRate());
			vi.setBondacust(li.getBondacust());
			vi.setTransmission(li.getTransmission());
			vi.setRefect(li.getRefect());
			vos.add(vi);
		}
		return vos;
	}
	
	public List<Item> saveAll(List<Item> pos){
		if(Toolkit.notEmpty(pos)){
			List<Item> lists = new ArrayList<>(); 
			for (Item item:pos) {
				Item temp = dao.getByRate(item.getRate(),item.getSmallPO().getPk());
				if(Toolkit.notEmpty(temp)){//存在
					item.setPk(temp.getPk()); 
				}
				lists.add(item);
			}
			if(lists.size()>0){
				return dao.save(lists); 
			}
		}
		return null ; 
	}
	
	public void deleteAll(String metaPk){
		dao.deleteByMetaPK(metaPk);
		dao.flush();
	}
}
