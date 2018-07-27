package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.ItemContractionDao;
import com.wisely.domain.ItemBig;
import com.wisely.domain.ItemContraction;
import com.wisely.domainVO.ItemConBaseVO;
import com.wisely.domainVO.QueryVO;

@Service
public class ItemContractionService {

	@Autowired
	private ItemContractionDao dao;
	
	
	public List<ItemContraction> saveAll(List<ItemContraction> pos){
		return dao.save(pos);
	}
	
	public List<ItemConBaseVO> getItemConList(QueryVO queryVO){
		List<ItemContraction> list = dao.getItemConListByContions(queryVO.getRateMin(), queryVO.getRateMax(), queryVO.getSamplename(), queryVO.getBackgroundtype());
		List<ItemConBaseVO> vos = new ArrayList<ItemConBaseVO>();
		for(ItemContraction li:list) {
			ItemConBaseVO vo = new ItemConBaseVO();
			vo.setRadiation(li.getRadiation());
			vo.setRadiationlose(li.getRadiationlose());
			vo.setTarget(li.getTarget());
			vo.setRate(li.getRate());
			vos.add(vo);
		}
		return vos;
	}
}
