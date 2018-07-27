package com.wisely.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wisely.dao.ItemBigDao;
import com.wisely.dao.ItemDao;
import com.wisely.domain.Item;
import com.wisely.domain.ItemBig;
import com.wisely.domainVO.ItemBigVO;
import com.wisely.domainVO.QueryVO;

@Service
public class ItemBigService {

	@Autowired
	private ItemBigDao dao;
	
	
	public List<ItemBig> saveAll(List<ItemBig> pos){
		return dao.save(pos);
	}
	
	public List<ItemBigVO> getItemBigList(QueryVO queryVO){
		List<ItemBig> list = dao.getItemBigListByContions(queryVO.getRateMin(), queryVO.getRateMax(), 
				queryVO.getSamplename(), queryVO.getBackgroundtype(), queryVO.getPress());
		List<ItemBigVO> vos =  new ArrayList<ItemBigVO>();
		for(ItemBig li:list) {
			ItemBigVO vo = new ItemBigVO();
			vo.setBondacust(li.getBondacust());
			vo.setEchoes(li.getEchoes());
			vo.setRadiation(li.getRadiation());
			vo.setRadiationlose(li.getRadiationlose());
			vo.setRefect(li.getRefect());
			vo.setTransmission(li.getTransmission());
			vo.setRate(li.getRate());
			vos.add(vo);
		}
		
		return vos;
	}
}
