package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.wisely.dao.BaseMetaSampleDao;
import com.wisely.dao.PhotoDao;
import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.common.Photo;
import com.wisely.domainVO.ResultVO;
import com.wisely.util.Toolkit;

@Service
public class BaseMetaSampleService{
	
	@Autowired
	private BaseMetaSampleDao dao;
	
	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private UpdateColumnService updateColumnService;
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
					//修改引用到的名称
					updateColumnService.updateValue("smallmetadata", "samplename", result.getName(), "sample_pk", result.getPk());
					updateColumnService.updateValue("bigmetadata", "sample_name", result.getName(), "sample_pk", result.getPk());
				}
			}
		}
		return result;
	}
	public int queryOtherNameCount(BaseMetaSample entity){
		return dao.findOtherName(entity.getName(), entity.getPk());
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
		int count = updateColumnService.queryByColumn("smallmetadata", "sample_pk", pk);
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
