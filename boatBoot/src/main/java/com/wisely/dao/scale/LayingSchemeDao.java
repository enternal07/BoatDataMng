package com.wisely.dao.scale;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.support.CustomRepository;

public interface LayingSchemeDao extends CustomRepository<LayingSchemePO, String> {
	
	LayingSchemePO findByName(String name);
	
	@Query("select count(m) from LayingSchemePO m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
		
	
}
