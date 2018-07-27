package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.Item;
import com.wisely.support.CustomRepository;

public interface ItemDao extends CustomRepository<Item, String> {
	
	@Query("select item from Item item where item.rate between ?1 and ?2 and item.samplPO.samplename =?3 and item.samplPO.backgroundtype = ?4 and item.samplPO.press=?5 order by item.rate ASC")
	List<Item> getObjectListByContions(int rateMin,int rateMax,String sampleName,String backType,int press);

}
