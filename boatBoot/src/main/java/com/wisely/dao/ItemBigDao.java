package com.wisely.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wisely.domain.big.ItemBig;
import com.wisely.domain.small.Item;
import com.wisely.support.CustomRepository;

public interface ItemBigDao extends CustomRepository<ItemBig, String> {
	
	@Query("select item from ItemBig item where item.rate between ?1 and ?2 and item.bigDemoMetadata.sampleName =?3 and item.bigDemoMetadata.testModelName = ?4 and item.bigDemoMetadata.testSystemName = ?5 and item.bigDemoMetadata.press=?6 and item.bigDemoMetadata.temparture=?7 order by item.rate ASC")
	List<ItemBig> getItemBigListByContions(int rateMin,int rateMax,String sampleName,String testModelname,String testSysName,float press,float temparture);

	@Modifying
	@Transactional
	@Query("delete from ItemBig item where item.bigDemoMetadata.pk = ?1")
	int deleteByBigMetaPK(String metaPK);
	
	@Modifying
	@Transactional
	@Query("delete from ItemBig item where item.pk in (?1) ")
	int deleteByPks(List<String> pks);
	
	@Query("select count(1) from ItemBig item where rate = ?1 and refect = ?2 and transmission=?3 and bondacust=?4 and echoes =?5 and radiation=?6 and radiationlose = ?7 and item.bigDemoMetadata.pk = ?8")
	int getCount(Integer rate,Float refect,Float transmission,Float bondacust,Float echoes,Integer radiation,Float radiationlose,String fpk);

	@Query("select item from ItemBig item where item.rate = ?1 and item.bigDemoMetadata.pk = ?2")
	ItemBig getByRate(Integer rate,String fpk);
	
}
