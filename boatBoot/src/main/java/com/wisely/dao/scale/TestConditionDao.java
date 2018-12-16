package com.wisely.dao.scale;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.scale.TestConditionPO;
import com.wisely.support.CustomRepository;

public interface TestConditionDao extends CustomRepository<TestConditionPO, String> {
	
	TestConditionPO findByName(String name);
	
	@Query("select count(m) from TestConditionPO m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
	
	
}
