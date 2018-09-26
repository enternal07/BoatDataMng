package com.wisely.dao.scale;

import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.support.CustomRepository;

public interface LayingSchemeDao extends CustomRepository<LayingSchemePO, String> {
	
	LayingSchemePO findByName(String name);
}
