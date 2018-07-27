package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.Item;
import com.wisely.domain.ItemBig;
import com.wisely.support.CustomRepository;

public interface ItemBigDao extends CustomRepository<ItemBig, String> {
	
	@Query("select item from ItemBig item where item.rate between ?1 and ?2 and item.samplPO.samplename =?3 and item.samplPO.backgroundtype = ?4 and item.samplPO.press=?5 order by item.rate ASC")
	List<ItemBig> getItemBigListByContions(int rateMin,int rateMax,String sampleName,String backType,int press);


}
