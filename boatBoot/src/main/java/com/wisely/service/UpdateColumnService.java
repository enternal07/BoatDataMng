package com.wisely.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UpdateColumnService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate ; 
	
	public void updateValue(String tableName,String column,String value,String pkColumn,String pkValue){
		String sql = "update "+tableName+" set "+column +" =  '" +value+"' where "+pkColumn+" = '"+pkValue+"'";
		jdbcTemplate.update(sql);
	}
	
	public int queryByColumn(String tableName,String column,String value){
		String sql = "select count(*) from "+tableName+" where "+column+" = '"+value+"'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
}
