package com.wisely.service;

import com.wisely.domain.SysUser;

public interface ISysUserService {
	
	//根据用户名和密码查找用户
	SysUser queryByName(String username,String pass);

}
