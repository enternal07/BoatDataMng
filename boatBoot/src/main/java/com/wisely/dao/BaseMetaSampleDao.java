package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.BaseMetaSample;
import com.wisely.domain.Demometadata;
import com.wisely.support.CustomRepository;

public interface BaseMetaSampleDao extends CustomRepository<BaseMetaSample, String> {
	
	List<Demometadata> findByName(String small);
}
