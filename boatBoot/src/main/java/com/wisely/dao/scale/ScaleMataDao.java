package com.wisely.dao.scale;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domain.small.Demometadata;
import com.wisely.support.CustomRepository;

public interface ScaleMataDao extends CustomRepository<ScaleMataPO, String> {
	
	@Query("select count(meta), meta.pk from ScaleMataPO meta where  meta.testModelObjName=?1 and meta.layingSchemeName=?2 and meta.testConditionName=?3")
	List<Object> CountByames(String testModelObjName,String layingSchemeName,String testConditionName);

	List<ScaleMataPO> findByName(String name);
	
	@Query("select meta from ScaleMataPO meta where meta.testModelObjName=?1 and meta.layingSchemeName=?2 and meta.testConditionName=?3")
	List<ScaleMataPO> findByCondition(String testModelObjName,String layingSchemeName,String testConditionName);
	
	
}
