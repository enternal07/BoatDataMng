package com.wisely.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

/**
 * 用户表
 * @author liqz

drop table if exists spha_base_user;
create table spha_base_user(
	id int auto_increment primary key,
	username varchar(60) comment '用户名',
	pass varchar(60) comment '密码',
	usertype varchar(60) comment '用户类别',
	updatetime varchar(30) comment '更新时间',
	ts timestamp default CURRENT_TIMESTAMP()
);
 */

@Entity 
@Inheritance
@Table(name="spha_base_user")
public class SysUser extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	//权限标识
	@Column(name="username")
	private String username;
	
	@Column(name="pass")
	private String pass;
	//管理员、普通用户
	@Column(name="usertype")
	private String usertype;
	
	public SysUser(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
