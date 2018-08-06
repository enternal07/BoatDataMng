package com.wisely.dao;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.BigDemoMetadata;
import com.wisely.support.CustomRepository;

public interface BigDemoMetadataDao extends CustomRepository<BigDemoMetadata, String> {

	
	@Query("select count(meta) from BigDemoMetadata meta where meta.samplename=?1 and meta.temparture=?2 and meta.press=?3 and meta.testmodelName=?4 and meta.testsystemName=?5")
	int CountBySamplenameAndTempartureAndPressAndTestNames(String SmapleName,float Temparture ,int press,String testMname,String testSname);

}
