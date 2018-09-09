package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.ScaleMataDao;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domainVO.ResultVO;

@Service
public class ScaleMataService {

	@Autowired
	private ScaleMataDao dao;

	public ResultVO ifExist(ScaleMataPO entity) {
		
		List<Object> resEntity = dao.CountByames(entity.getTestModelObjName(), entity.getLayingSchemeName(), entity.getTestConditionName());
		ResultVO res = new ResultVO();
		  Object ress=resEntity.get(0);
			Object[] arry = (Object[]) ress;
			long size = (long) arry[0];
			if(size>0) {
				res.setSuccess(true);
				res.setData(arry[1]);
			}else {
				res.setSuccess(false);
			}
			return res;
		}

	public ScaleMataPO saveEntity(ScaleMataPO demoMeta) {
		return dao.save(demoMeta);
	}

}
