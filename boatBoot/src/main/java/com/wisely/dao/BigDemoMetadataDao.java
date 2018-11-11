package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.support.CustomRepository;

public interface BigDemoMetadataDao extends CustomRepository<BigDemoMetadata, String> {

	
	@Query("select count(meta), meta.pk from BigDemoMetadata meta where meta.sampleName=?1 and meta.temparture=?2 and meta.press=?3 and meta.testModelName=?4 and meta.testSystemName=?5")
	List<Object> CountBySamplenameAndTempartureAndPressAndTestNames(String SmapleName,float Temparture ,float press,String testMname,String testSname);

}
