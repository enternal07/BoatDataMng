package com.wisely.dao;

import java.util.List;

import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.support.CustomRepository;

public interface BaseMetaBackingDao extends CustomRepository<BaseMetaBacking, String> {
	
	List<BaseMetaBacking> findListByName(String small);
	
	BaseMetaBacking findByName(String name);
}
