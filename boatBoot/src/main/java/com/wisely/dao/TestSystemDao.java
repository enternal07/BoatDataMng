package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.big.TestSystem;
import com.wisely.support.CustomRepository;

public interface TestSystemDao extends CustomRepository<TestSystem, String> {
	
	List<TestSystem> findListByName(String name);
	
	TestSystem findByName(String name);
	
	@Query("select count(m) from TestSystem m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
	
}
