package com.wisely.domain.big;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BasePhotoModel;

/**
 * 大样数据测试系统
 * */
//大样模型测试系统
@Entity
@Inheritance
@Table(name="testsystem")
public class TestSystem  extends BasePhotoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
//	@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	@Column(name = "testsystem_name")
	private String name;
	
	@Column(name = "describ")
	private String describe;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	

}
