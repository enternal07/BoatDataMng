package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.BigDemoMetadataDao;
import com.wisely.dao.ItemBigDao;
import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domain.big.ItemBig;
import com.wisely.domainVO.ResultVO;
import com.wisely.domainVO.mng.data.BigItemVO;
import com.wisely.util.Toolkit;

@Service
public class BigDemoMetadataService{

	@Autowired
	private BigDemoMetadataDao dao;
	
	@Autowired
	private  ItemBigDao itemDao;
	
	/**
	 * 添加大样数据实体
	 * @param entity
	 * @return
	 */
	public BigDemoMetadata saveEntity(BigDemoMetadata entity) {
		return dao.save(entity);
		
	}
	/**
	 * 修改大样数据实体
	 * @param entity
	 * @return
	 */
	public BigDemoMetadata updateEntity(BigDemoMetadata entity){
		BigDemoMetadata result = null ; 
		if(Toolkit.notEmpty(entity.getPk())){
			BigDemoMetadata temp = dao.findOne(entity.getPk());
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
	public ResultVO ifExist(BigDemoMetadata entity) {
		
		List<Object> resEntity = dao.CountBySamplenameAndTempartureAndPressAndTestNames(entity.getSampleName(), entity.getTemparture(), 
				entity.getPress(), entity.getTestmodelName(), entity.getTestsystemName());
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
	public List<BigDemoMetadata> findAll() {
		return  dao.findAll();
	}
	/**
	 * 查询所有
	 */
	public List<BigItemVO> findAllItem(){
		List<ItemBig> allItems = itemDao.findAll();
		List<BigItemVO> result = new ArrayList<>() ; 
		if(Toolkit.notEmpty(allItems)){
			for (ItemBig item : allItems) {
				BigItemVO bigItemVO = new BigItemVO();
				BigDemoMetadata bigVO = item.getBigDemoMetadata();
				bigItemVO.setSampleName(bigVO.getSampleName());
				bigItemVO.setSamplepk(bigVO.getSamplepk());
				bigItemVO.setTestModelName(bigVO.getTestmodelName());
				bigItemVO.setTestModelPk(bigVO.getTestModelPk());
				bigItemVO.setTestSystemName(bigVO.getTestsystemName());
				bigItemVO.setTestSystemPk(bigVO.getTestSystemPk());
				bigItemVO.setTemparture(bigVO.getTemparture());
				bigItemVO.setPress(bigVO.getPress());
				bigItemVO.setBondacust(item.getBondacust());
				bigItemVO.setRate(item.getRate());
				bigItemVO.setRefect(item.getRefect());
				bigItemVO.setTransmission(item.getTransmission());
				bigItemVO.setRadiation(item.getRadiation());
				bigItemVO.setRadiationlose(item.getRadiationlose());
				bigItemVO.setEchoes(item.getEchoes());
				result.add(bigItemVO);
			}
		}
		return result;
	}
	
}
