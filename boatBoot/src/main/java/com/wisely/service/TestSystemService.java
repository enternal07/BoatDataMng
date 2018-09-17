package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.PhotoDao;
import com.wisely.dao.TestSystemDao;
import com.wisely.domain.big.TestSystem;
import com.wisely.domain.common.Photo;
import com.wisely.util.Toolkit;

@Service
public class TestSystemService{

	@Autowired
	private TestSystemDao dao;
	
	@Autowired
	private PhotoDao photoDao;
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	public TestSystem saveEntity(TestSystem entity) {
		TestSystem result = null;
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
	public TestSystem updateEntity(TestSystem entity){
		TestSystem result = null ; 
		if(Toolkit.notEmpty(entity)&&Toolkit.notEmpty(entity.getPk())){
			List<Photo> photos = entity.getPhotos();
			TestSystem temp = dao.findOne(entity.getPk());
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
	
	public TestSystem getByName(String pk) {
		return dao.findOne(pk);
	}
	
	public List<TestSystem> findAll() {
		return  dao.findAll();
	}
}
