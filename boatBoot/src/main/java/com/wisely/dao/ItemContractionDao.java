package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.ItemContraction;
import com.wisely.support.CustomRepository;

public interface ItemContractionDao extends CustomRepository<ItemContraction, String> {
	
	
	@Query("select item from ItemContraction item where item.rate between ?1 and ?2 and item.samplPO.samplename =?3 and item.samplPO.backgroundtype = ?4  order by item.rate ASC")
	List<ItemContraction> getItemConListByContions(int rateMin,int rateMax,String sampleName,String backType);

}
