package com.wisely.service.scale;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.ItemScaleDao;
import com.wisely.domain.scale.ItemScalePO;
import com.wisely.domainVO.ItemScaleVO;
import com.wisely.domainVO.SacleQueryVO;
import com.wisely.util.Toolkit;

@Service
public class ItemScaleService {

	@Autowired
	private ItemScaleDao dao;

	public List<ItemScalePO> saveAll(List<ItemScalePO> pos) {
		
		if(Toolkit.notEmpty(pos)){
			List<ItemScalePO> items = new ArrayList<>(); 
			for (ItemScalePO item:pos) {
				if(dao.getCount(item.getLightShellTS(),item.getLightShellSP(),
						item.getLayingShellTS(),item.getLayingShellSP(),
						item.getReductionTS(),item.getReductionSP(),item.getScaleMataPO().getPk())==0){
					items.add(item);
				}
			}
			if(items.size()>0){
				return dao.save(items);
			}
		}
		return null ; 
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
			vo.setPk(li.getPk());
			vo.setMetaPK(li.getScaleMataPO().getPk()); 
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
