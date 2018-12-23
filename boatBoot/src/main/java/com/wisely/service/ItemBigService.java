package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.ItemBigDao;
import com.wisely.domain.big.ItemBig;
import com.wisely.domainVO.ItemBigVO;
import com.wisely.domainVO.QueryBigVO;
import com.wisely.util.Toolkit;

@Service
public class ItemBigService {

	@Autowired
	private ItemBigDao dao;
	
	public List<ItemBig> saveAll(List<ItemBig> pos){
		if(Toolkit.notEmpty(pos)){
			List<ItemBig> items = new ArrayList<>(); 
			for (ItemBig item:pos) {
				if(dao.getCount(item.getRate(), item.getRefect(), 
						item.getTransmission(), item.getBondacust(),
						item.getEchoes(),item.getRadiation(),
						item.getRadiationlose(),item.getBigDemoMetadata().getPk())==0){
					items.add(item);
				}
			}
			if(items.size()>0){
				return dao.save(items);
			}
		}
		return null ; 
	}
	
	public List<ItemBigVO> getItemBigList(QueryBigVO queryVO){
		List<ItemBig> list = dao.getItemBigListByContions(queryVO.getRateMin(), queryVO.getRateMax(), 
				queryVO.getSamplename(), queryVO.getTestModelName(),queryVO.getTestSystemName(), queryVO.getPress(),queryVO.getTemparture());
		List<ItemBigVO> vos =  new ArrayList<ItemBigVO>();
		for(ItemBig li:list) {
			ItemBigVO vo = new ItemBigVO();
			vo.setPk(li.getPk());
			vo.setMetaPK(li.getBigDemoMetadata().getPk()); 
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
