package com.wisely.dao;

import com.wisely.domain.SysUser;
import com.wisely.support.CustomRepository;

public interface SysUserDao extends CustomRepository<SysUser, Integer> {
	
	/*根据用户名查询用户*/
	SysUser findByUsername(String username);
	
}
