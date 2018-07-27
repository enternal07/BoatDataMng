package com.wisely.dao;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.ContractionMetadata;
import com.wisely.support.CustomRepository;

public interface ContractionMetadataDao extends CustomRepository<ContractionMetadata, String> {
	
	@Query("select count(meta) from ContractionMetadata meta where meta.samplename=?1  and meta.backgroundtype=?2 and meta.testtime=?3")
	int CountByCons(String SmapleName,String backgroudType,String testtime );

}
