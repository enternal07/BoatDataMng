package com.wisely.dao;

import java.util.List;

import com.wisely.domain.big.TestModel;
import com.wisely.support.CustomRepository;

public interface TestModelDao extends CustomRepository<TestModel, String> {
	
	List<TestModel> findListByName(String name);
	
	TestModel findByName(String name);
}
