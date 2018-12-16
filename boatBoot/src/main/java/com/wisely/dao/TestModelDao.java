package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.big.TestModel;
import com.wisely.support.CustomRepository;

public interface TestModelDao extends CustomRepository<TestModel, String> {
	
	List<TestModel> findListByName(String name);
	
	TestModel findByName(String name);
	
	@Query("select count(m) from TestModel m where m.name = ?1 and m.pk!=?2 ")
	int findOtherName(String name,String pk);
	
}
