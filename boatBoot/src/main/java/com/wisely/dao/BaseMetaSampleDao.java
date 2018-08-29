package com.wisely.dao;

import java.util.List;

import com.wisely.domain.BaseMetaSample;
import com.wisely.support.CustomRepository;

public interface BaseMetaSampleDao extends CustomRepository<BaseMetaSample, String> {
	
	
	List<BaseMetaSample> findBySmall(boolean small);
}
