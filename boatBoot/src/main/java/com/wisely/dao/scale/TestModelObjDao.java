package com.wisely.dao.scale;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.support.CustomRepository;

public interface TestModelObjDao extends CustomRepository<TestModelObjPO, String> {
	
	TestModelObjPO findByName(String name);
	
	@Query("select count(m) from TestModelObjPO m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
		
	
	
}
