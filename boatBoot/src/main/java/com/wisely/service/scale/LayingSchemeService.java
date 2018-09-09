package com.wisely.service.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.scale.LayingSchemeDao;
import com.wisely.domain.scale.LayingSchemePO;

@Service
public class LayingSchemeService {

	@Autowired
	private LayingSchemeDao dao;
	

	public  LayingSchemePO saveEntity(LayingSchemePO entity) {
		return dao.save(entity);
		
	}
	public LayingSchemePO getByPK(String pk) {
		return dao.findOne(pk);
	}
	
	public List<LayingSchemePO> findAll() {
		return  dao.findAll();
	}
}
