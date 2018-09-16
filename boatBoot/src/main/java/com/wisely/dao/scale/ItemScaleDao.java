package com.wisely.dao.scale;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wisely.domain.scale.ItemScalePO;
import com.wisely.support.CustomRepository;

public interface ItemScaleDao extends CustomRepository<ItemScalePO, String> {

	@Query("select item from ItemScalePO item where item.rate between ?1 and ?2 and item.scaleMataPO.testModelObjName =?3 and item.scaleMataPO.layingSchemeName = ?4 and item.scaleMataPO.testConditionName = ?5 order by item.rate ASC")
	List<ItemScalePO> getItemScaleListByContions(int rateMin,int rateMax,String testModelObjName,String layingSchemeName,String testConditionName);

	@Modifying
	@Transactional
	@Query("delete from ItemScalePO item where item.scaleMataPO.pk = ?1")
	int deleteByScaleMetaPK(String metaPK);
	
	@Modifying
	@Transactional
	@Query("delete from ItemScalePO item where item.pk in (?1) ")
	int deleteByPks(List<String> pks);
	
	
}
