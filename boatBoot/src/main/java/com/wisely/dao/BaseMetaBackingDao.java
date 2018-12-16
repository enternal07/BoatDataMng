package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.support.CustomRepository;

public interface BaseMetaBackingDao extends CustomRepository<BaseMetaBacking, String> {
	
	List<BaseMetaBacking> findListByName(String small);
	
	List<BaseMetaBacking> findByName(String name);
	
	@Query("select count(m) from BaseMetaBacking m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
	
}
