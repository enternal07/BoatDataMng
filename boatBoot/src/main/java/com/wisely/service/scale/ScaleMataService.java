package com.wisely.service.scale;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.ItemScaleDao;
import com.wisely.dao.scale.ScaleMataDao;
import com.wisely.domain.scale.ItemScalePO;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domainVO.ResultVO;
import com.wisely.domainVO.mng.data.ScaleItemVO;
import com.wisely.util.Toolkit;

@Service
public class ScaleMataService {

	@Autowired
	private ScaleMataDao dao;

	@Autowired
	private ItemScaleDao itemDao;
	
	
	//出我之外名称是否存在
	public ScaleMataPO existName(ScaleMataPO dt){
		List<ScaleMataPO> results =  dao.findByName(dt.getName());
		ScaleMataPO result = null ; 
		if(Toolkit.notEmpty(results) && results.size()>0){
			for (ScaleMataPO demometadata : results) {
				if(!demometadata.getPk().equals(dt.getPk())){
					result = demometadata;
					break;
				}
			}
		}
		return result ; 
	}
	//出我之外重复是否存在
	public ScaleMataPO existUniqueCondition(ScaleMataPO entity){
		List<ScaleMataPO> results = dao.
				findByCondition(
						entity.getTestModelObjName(),entity.getLayingSchemeName(),entity.getTestConditionName());
		ScaleMataPO result = null ; 
		if(Toolkit.notEmpty(results) && results.size()>0){
			for (ScaleMataPO demometadata : results) {
				if(!demometadata.getPk().equals(entity.getPk())){
					result = demometadata;
					break;
				}
			}
		}
		return result ; 
	}
	
	public ScaleMataPO findByName(String name){
		List<ScaleMataPO> results =  dao.findByName(name);
		if(Toolkit.notEmpty(results)&&results.size()>0){
			return results.get(0);
		}
		return null;
	}
	
	public ScaleMataPO findByUniqueCondition(ScaleMataPO entity){
		List<ScaleMataPO> results = dao.
				findByCondition(
						entity.getTestModelObjName(),entity.getLayingSchemeName(),entity.getTestConditionName());
		ScaleMataPO result = null ; 
		if(Toolkit.notEmpty(results)&&results.size()>0){
			result = results.get(0);
		}
		return result ; 
	}
	
	public List<ScaleMataPO> findByUniqueConditionList(ScaleMataPO entity){
		List<ScaleMataPO> results = dao.
				findByCondition(
						entity.getTestModelObjName(),entity.getLayingSchemeName(),entity.getTestConditionName());
		ScaleMataPO result = null ; 
		return results ; 
	}
	
	//删除所有的样本数据
	public void deleteAllItems(String metaPk){
		itemDao.deleteByScaleMetaPK(metaPk);
		itemDao.flush();
	}
	
	
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
	/**
	 * 查询所有
	 */
	public List<ScaleItemVO> findAllItem(){
		List<ItemScalePO> allItems = itemDao.findAll();
		List<ScaleItemVO> result = new ArrayList<>() ; 
		if(Toolkit.notEmpty(allItems)){
			for (ItemScalePO item : allItems) {
				ScaleItemVO bigItemVO = new ScaleItemVO();
				ScaleMataPO bigVO = item.getScaleMataPO();
				bigItemVO.setPk(item.getPk());
				bigItemVO.setName(bigVO.getName()); 
				bigItemVO.setTestModelObjName(bigVO.getTestModelObjName());
				bigItemVO.setTestModelObjPk(bigVO.getTestModelObjPk());
				bigItemVO.setLayingSchemeName(bigVO.getLayingSchemeName());
				bigItemVO.setLayingSchemePk(bigVO.getLayingSchemePk());
				bigItemVO.setTestConditionName(bigVO.getTestConditionName());
				bigItemVO.setTestConditionPk(bigVO.getTestConditionPk());
				bigItemVO.setLightShellTS(item.getLightShellTS());
				bigItemVO.setLightShellSP(item.getLightShellSP());
				bigItemVO.setLayingShellTS(item.getLayingShellTS());
				bigItemVO.setLayingShellSP(item.getLayingShellSP());
				bigItemVO.setReductionSP(item.getReductionSP());
				bigItemVO.setReductionTS(item.getReductionTS());
				result.add(bigItemVO);
			}
		}
		return result;
	}
	
	public ItemScalePO saveItem(ItemScalePO item){
		return itemDao.save(item);
	}
	
	public ItemScalePO updateItem(ItemScalePO item){
		ItemScalePO result = null ; 
		if(Toolkit.notEmpty(item.getPk())){
			ItemScalePO temp = itemDao.findOne(item.getPk());
			if(Toolkit.notEmpty(temp)){
				item.setScaleMataPO(temp.getScaleMataPO());
				result = itemDao.save(item);
			}
		}
		return result;
	}
	
	public void deleteItem(String pk){
		if(Toolkit.notEmpty(pk)){
			itemDao.delete(pk);
		}
	}
	
	public void deleteItems(List<String> pks){
		if(Toolkit.notEmpty(pks)){
			itemDao.deleteByPks(pks);
		}
	}
	
	public List<ScaleMataPO> queryFull(){
		return dao.findAll();
	}
	
	public ScaleMataPO queryDetail(String pk){
		return dao.findOne(pk);
	}
	
}
