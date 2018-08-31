package com.wisely.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.SysUserDao;
import com.wisely.domain.SysUser;
import com.wisely.service.ISysUserService;
import com.wisely.util.Toolkit;

@Service
public class SysUserServiceImpl implements ISysUserService{

	@Autowired
	private SysUserDao sysUserDao ; 
	
	@Override
	public SysUser queryByName(String username, String pass) {
		SysUser su = null ; 
		if(Toolkit.notEmpty(username)&&Toolkit.notEmpty(pass)){
			su = sysUserDao.findByUsername(username);
			if(Toolkit.notEmpty(su) && pass.equals(su.getPass())){
				return su; 
			}
		}
		return null ;
	}

}
