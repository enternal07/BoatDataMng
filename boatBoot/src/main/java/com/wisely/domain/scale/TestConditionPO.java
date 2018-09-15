package com.wisely.domain.scale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;

/**
 * 试验情况
 */
@Entity
@Inheritance
@Table(name="test_condition")
public class TestConditionPO  extends BaseModel{
	
	@Id //2
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	private String name;
	
	private String duration;
	
	@Column(name = "test_time")
	private String testTime;
	
	@Column(name = "test_place")
	private String testPlace;
	
	@Column(name = "water_depth")
	private String waterDepth;
	
	@Column(name = "test_depth")
	private String testDepth;
	
	private String other;
	
	private String logo;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getTestPlace() {
		return testPlace;
	}

	public void setTestPlace(String testPlace) {
		this.testPlace = testPlace;
	}

	public String getWaterDepth() {
		return waterDepth;
	}

	public void setWaterDepth(String waterDepth) {
		this.waterDepth = waterDepth;
	}

	public String getTestDepth() {
		return testDepth;
	}

	public void setTestDepth(String testDepth) {
		this.testDepth = testDepth;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	

}
