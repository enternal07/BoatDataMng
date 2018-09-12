package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.ItemBigDao;
import com.wisely.domain.big.ItemBig;
import com.wisely.domainVO.ItemBigVO;
import com.wisely.domainVO.QueryBigVO;

@Service
public class ItemBigService {

	@Autowired
	private ItemBigDao dao;
	
	
	public List<ItemBig> saveAll(List<ItemBig> pos){
		return dao.save(pos);
	}
	
	public List<ItemBigVO> getItemBigList(QueryBigVO queryVO){
		List<ItemBig> list = dao.getItemBigListByContions(queryVO.getRateMin(), queryVO.getRateMax(), 
				queryVO.getSamplename(), queryVO.getTestModelName(),queryVO.getTestSystemName(), queryVO.getPress(),queryVO.getTemparture());
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
	
	public void deleteAll(String metaPk){
		dao.deleteByBigMetaPK(metaPk);
		dao.flush();
	}
}
