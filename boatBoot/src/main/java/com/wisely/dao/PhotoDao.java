package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wisely.domain.common.Photo;
import com.wisely.support.CustomRepository;

public interface PhotoDao extends CustomRepository<Photo, String> {
	
	
	@Modifying
	@Transactional
	@Query("update Photo photo set photo.deleted = 1, photo.updatetime = ?2 where pk = ?1")
	int deleteByPK(String pk,String updatetime);
	
	@Modifying
	@Transactional
	@Query("update Photo photo set photo.modelPK = ?1,photo.deleted=0 where photo.pk in (?2)")
	int modifyModelPK(String pk,List<String> photoPKs);
	
	@Modifying
	@Transactional
	@Query("update Photo photo set photo.deleted = ?2 where photo.modelPK = ?1 ")
	int modifyDeleted(String pk,int deleled);
	
	
}
