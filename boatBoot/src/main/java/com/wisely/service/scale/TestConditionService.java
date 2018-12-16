package com.wisely.service.scale;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisely.dao.PhotoDao;
import com.wisely.dao.scale.TestConditionDao;
import com.wisely.domain.common.Photo;
import com.wisely.domain.scale.TestConditionPO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.UpdateColumnService;
import com.wisely.util.Toolkit;

@Service
public class TestConditionService{

	@Autowired
	private TestConditionDao dao;
	
	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private UpdateColumnService updateColumnService;
	
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	public TestConditionPO saveEntity(TestConditionPO entity) {
		TestConditionPO result = null;
		if(Toolkit.notEmpty(entity)){
			List<Photo> photos = entity.getPhotos();
			entity.setPhotos(null); 
			result = dao.save(entity);
			if(Toolkit.notEmpty(result)){
				savePhoto(result.getPk(),photos);
			}
		}
		return result;
	}
	private void savePhoto(String modelPK,List<Photo> photos){
		if(Toolkit.notEmpty(photos) && photos.size()>0){
			List<String> photoPKs = new ArrayList<>();
			for (Photo photo : photos) {
				photoPKs.add(photo.getPk());
			}
			photoDao.modifyModelPK(modelPK, photoPKs);
		}
	}
	
	/**
	 * 修改实体
	 * @param entity
	 * @return
	 */
	public TestConditionPO updateEntity(TestConditionPO entity){
		TestConditionPO result = null ; 
		if(Toolkit.notEmpty(entity)&&Toolkit.notEmpty(entity.getPk())){
			List<Photo> photos = entity.getPhotos();
			TestConditionPO temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				entity.setPhotos(null); 
				result = dao.save(entity); 
				if(Toolkit.notEmpty(result)){
					updatePhoto(result.getPk(), photos);
					updateColumnService.updateValue("scale_mata", "test_condition_name", result.getName(), "test_condition_pk", result.getPk());
				}
			}
		}
		return result;
	}
	
	private void updatePhoto(String modelPK,List<Photo> photos ){
		List<String> photoPKs = new ArrayList<>();
		if(Toolkit.notEmpty(photos) && photos.size()>0){
			for (Photo photo : photos) {
				photoPKs.add(photo.getPk());
			}
			photoDao.modifyDeleted(modelPK,1);
			photoDao.modifyModelPK(modelPK, photoPKs);
		}
	}
	/**
	 * 删除实体
	 * @param pk
	 */
	public ResultVO deleteEntity(String pk){
		ResultVO re = new ResultVO(false);
		int count = updateColumnService.queryByColumn("scale_mata", "test_condition_pk", pk);
		if(Toolkit.notEmpty(pk)){
			if(count == 0){
				photoDao.modifyDeleted(pk,1);
				dao.delete(pk);
				re.setSuccess(true);
			}else{
				re.setMessage("该数据被引用，不能直接删除");
			}
		}else{
			re.setMessage("参数不能为空");
		}
		return re;
		
	}
	
	public TestConditionPO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestConditionPO> findAll() {
		return  dao.findAll(new Sort(Direction.DESC,"ts"));
	}
	
	public TestConditionPO getByName(String name) {
		return dao.findByName(name);
	}
	
	public int queryOtherNameCount(TestConditionPO entity){
		return dao.findOtherName(entity.getName(), entity.getPk());
	}
	

}
