package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaSampleDao;
import com.wisely.dao.PhotoDao;
import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.common.Photo;
import com.wisely.util.Toolkit;

@Service
public class BaseMetaSampleService{

	@Autowired
	private BaseMetaSampleDao dao;
	
	@Autowired
	private PhotoDao photoDao;
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	public BaseMetaSample saveEntity(BaseMetaSample entity) {
		BaseMetaSample result = null;
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
	public BaseMetaSample updateEntity(BaseMetaSample entity){
		BaseMetaSample result = null ; 
		if(Toolkit.notEmpty(entity)||Toolkit.notEmpty(entity.getPk())){
			List<Photo> photos = entity.getPhotos();
			BaseMetaSample temp = dao.findOne(entity.getPk());
			if(Toolkit.notEmpty(temp)){
				entity.setPhotos(null); 
				result = dao.save(entity); 
				if(Toolkit.notEmpty(result)){
					updatePhoto(result.getPk(), photos);
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
	public void deleteEntity(String pk){
		if(Toolkit.notEmpty(pk)){
			photoDao.modifyDeleted(pk,1);
			dao.delete(pk);
		}
	}
	
	
	public BaseMetaSample getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<BaseMetaSample> findAll() {
		return  dao.findAll(new Sort(Direction.DESC,"ts"));
	}
	
	public List<BaseMetaSample> findSmall() {
		return  dao.findBySmall(true);
	}
	
	public List<BaseMetaSample> findBig() {
		return  dao.findBySmall(false);
	}
	
	public BaseMetaSample getBySampleName(String name) {
		List<BaseMetaSample> result =  dao.findByName(name);
		if(Toolkit.notEmpty(result)&&result.size()>0){
			return result.get(0);
		}
		return null ; 
	}
	
}
