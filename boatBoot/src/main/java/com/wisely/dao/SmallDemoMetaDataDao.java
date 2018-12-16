package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.small.Demometadata;
import com.wisely.support.CustomRepository;

public interface SmallDemoMetaDataDao extends CustomRepository<Demometadata, String> {
	
	@Query("select count(meta), meta.pk from Demometadata meta where meta.samplename=?1 and meta.temparture=?2 and meta.press=?3 and meta.backingname=?4 and meta.small=?5")
	List<Object> CountBySamplenameAndTempartureAndPressAndBackgroundType(String SmapleName,float Temparture ,float press,String backgroudType,boolean small );
	
	List<Demometadata> findBySmall(boolean small);
	
	List<Demometadata> findByName(String name);
	
	@Query("select meta from Demometadata meta where meta.samplename=?1 and meta.temparture=?2 and meta.press=?3 and meta.backingname=?4 and meta.small=?5")
	List<Demometadata> findBySamplenameAndTempartureAndPressAndBackgroundType(String SmapleName,float Temparture ,float press,String backgroudType,boolean small );
	
	
}
