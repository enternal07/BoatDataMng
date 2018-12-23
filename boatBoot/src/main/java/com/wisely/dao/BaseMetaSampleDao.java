package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.common.BaseMetaSample;
import com.wisely.support.CustomRepository;

public interface BaseMetaSampleDao extends CustomRepository<BaseMetaSample, String> {
	
	
	//List<BaseMetaSample> findBySmall(boolean small);
	@Query("select m from BaseMetaSample m  where m.small = ?1 or m.small != ?1 ")
	List<BaseMetaSample> findBySmall(boolean small);
	
	List<BaseMetaSample> findByName(String name);
	
	@Query("select count(m) from BaseMetaSample m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
	
}
