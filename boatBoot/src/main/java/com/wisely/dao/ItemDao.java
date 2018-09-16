package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wisely.domain.small.Item;
import com.wisely.support.CustomRepository;

public interface ItemDao extends CustomRepository<Item, String> {
	
	@Query("select item from Item item where item.rate between ?1 and ?2 and item.smallPO.samplename =?3 and item.smallPO.backgroundtype = ?4  and item.smallPO.temparture = ?6 and item.smallPO.press=?5  order by item.rate ASC")
	List<Item> getObjectListByContions(int rateMin,int rateMax,String sampleName,String backType,int press,float temparture);

	@Modifying
	@Transactional
	@Query("delete from Item item where item.smallPO.pk = ?1")
	int deleteByMetaPK(String metaPK);
	
	@Modifying
	@Transactional
	@Query("delete from Item item where item.pk in (?1) ")
	int deleteByPks(List<String> pks);
	
	
}
