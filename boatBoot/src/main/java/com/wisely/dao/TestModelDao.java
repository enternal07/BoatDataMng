package com.wisely.dao;

import java.util.List;

import com.wisely.domain.TestModel;
import com.wisely.support.CustomRepository;

public interface TestModelDao extends CustomRepository<TestModel, String> {
	
	List<TestModel> findByName(String name);
}
