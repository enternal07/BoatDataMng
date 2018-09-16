package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.ScaleMataDao;
import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domainVO.ResultVO;
import com.wisely.util.Toolkit;

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
	/**
	 * 添加缩放模型数据实体
	 * @param demoMeta
	 * @return
	 */
	public ScaleMataPO saveEntity(ScaleMataPO demoMeta) {
		return dao.save(demoMeta);
	}

	/**
	 * 修改缩放模型数据实体
	 * @param entity
	 * @return
	 */
	public ScaleMataPO updateEntity(ScaleMataPO entity){
		ScaleMataPO result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			ScaleMataPO temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				result = dao.save(entity);
			}
		}
		return result;
	}
	/**
	 * 删除大样实体
	 * @param pk
	 */
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			dao.delete(pk);
		}
	}
	
	
	
	
}
