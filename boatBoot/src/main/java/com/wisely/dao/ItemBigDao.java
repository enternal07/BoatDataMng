package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wisely.domain.big.ItemBig;
import com.wisely.support.CustomRepository;

public interface ItemBigDao extends CustomRepository<ItemBig, String> {
	
	@Query("select item from ItemBig item where item.rate between ?1 and ?2 and item.bigDemoMetadata.sampleName =?3 and item.bigDemoMetadata.testModelName = ?4 and item.bigDemoMetadata.testSystemName = ?5 and item.bigDemoMetadata.press=?6 and item.bigDemoMetadata.temparture=?7 order by item.rate ASC")
	List<ItemBig> getItemBigListByContions(int rateMin,int rateMax,String sampleName,String testModelname,String testSysName,int press,float temparture);

	@Modifying
	@Transactional
	@Query("delete from ItemBig item where item.bigDemoMetadata.pk = ?1")
	int deleteByBigMetaPK(String metaPK);
	
	@Modifying
	@Transactional
	@Query("delete from ItemBig item where item.pk in (?1) ")
	int deleteByPks(List<String> pks);
	

}
