package com.wisely.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.domain.SysUser;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.ISysUserService;
import com.wisely.util.Toolkit;

/**
 * User Login
 * @author liqz
 *
 */
@RestController
@RequestMapping("user")
public class UserController{

	@Autowired
	private ISysUserService sysUserService;
	
	/*根据用户名和密码查询信息*/
	@RequestMapping("login")
	public  @ResponseBody ResultVO queryInfo(
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "pass", defaultValue = "") String pass,
			HttpServletRequest req){
		
		ResultVO res = new ResultVO(true);
		SysUser sysUser = sysUserService.queryByName(username, pass);
		if(Toolkit.isEmpty(sysUser)){
			res.setSuccess(false);
			res.setMessage("登录失败,用户名或者密码错误！");
		}else{
			sysUser.setPass(null);
			HttpSession session = req.getSession();
			session.setAttribute("username", sysUser.getUsername());
			res.setData(sysUser);
			res.setMessage("登录成功！");
		}
		return res;
	}
	
}
