package com.wisely.dao.scale;

import com.wisely.domain.scale.TestConditionPO;
import com.wisely.support.CustomRepository;

public interface TestConditionDao extends CustomRepository<TestConditionPO, String> {
	
	TestConditionPO findByName(String name);
	
}
