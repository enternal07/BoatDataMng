package com.wisely.service.scale;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.ItemScaleDao;
import com.wisely.domain.big.ItemBig;
import com.wisely.domain.scale.ItemScalePO;
import com.wisely.domainVO.ItemBigVO;
import com.wisely.domainVO.ItemScaleVO;
import com.wisely.domainVO.SacleQueryVO;

@Service
public class ItemScaleService {

	@Autowired
	private ItemScaleDao dao;

	public void saveAll(List<ItemScalePO> items) {
		 dao.save(items);
	}


	public void deleteAll(String metaPk){
		dao.deleteByScaleMetaPK(metaPk);
		dao.flush();
	}


	public Object getItemScaleList(SacleQueryVO queryVO) {
		List<ItemScalePO> list = dao.getItemScaleListByContions(queryVO.getRateMin(), queryVO.getRateMax(), queryVO.getTestModelObjName(),
				queryVO.getLayingSchemeName(), queryVO.getTestConditionName());
		List<ItemScaleVO> vos =  new ArrayList<ItemScaleVO>();
		for(ItemScalePO li:list) {
			ItemScaleVO vo = new ItemScaleVO();
			vo.setLayingShellSP(li.getLayingShellSP());
			vo.setLayingShellTS(li.getLayingShellTS());
			vo.setLayingShellTS(li.getLightShellTS());
			vo.setLightShellSP(li.getLightShellSP());
			vo.setReductionSP(li.getReductionSP());
			vo.setReductionTS(li.getReductionTS());
			vo.setRate(li.getRate());
			vos.add(vo);
		}
		
		return vos;
	}
	

}
