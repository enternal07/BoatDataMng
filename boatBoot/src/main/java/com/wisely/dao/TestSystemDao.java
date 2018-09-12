package com.wisely.dao;

import java.util.List;

import com.wisely.domain.big.TestSystem;
import com.wisely.support.CustomRepository;

public interface TestSystemDao extends CustomRepository<TestSystem, String> {
	
	List<TestSystem> findByName(String name);
}
