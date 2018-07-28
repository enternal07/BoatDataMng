package com.wisely.service;

import java.util.ArrayList;
import java.util.List;
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
	
	public List<ItemBaseVO> findByQueryCondtion(QueryVO queryVO){
		System.out.println(queryVO.getSamplename()+
				queryVO.getBackgroundtype()+queryVO.getRateMin()+queryVO.getRateMax()+
				queryVO.getTemparture()+queryVO.getPress());
		List<Item> list = 	dao.getObjectListByContions(queryVO.getRateMin(), queryVO.getRateMax(),
				queryVO.getSamplename(), queryVO.getBackgroundtype(), queryVO.getPress(),queryVO.getTemparture());
		List<ItemBaseVO> vos = new ArrayList<ItemBaseVO>();
		for(Item li:list) {
			ItemBaseVO vo = new ItemBaseVO();
			vo.setBondacust(li.getBondacust());
			vo.setRefect(li.getRefect());
			vo.setTransmission(li.getTransmission());
			vo.setRate(li.getRate());
			vos.add(vo);
		}
		return vos;
	}
	public List<Item> saveAll(List<Item> pos){
		return dao.save(pos);
	}
}
