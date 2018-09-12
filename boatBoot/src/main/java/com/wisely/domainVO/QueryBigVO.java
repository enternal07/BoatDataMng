package com.wisely.domainVO;

import java.io.Serializable;

/*
 * 查询模型
 */
public class QueryBigVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String samplename;
	
	private String testModelName;
	
	private String testSystemName;
	
	private  Float temparture;
	
	private Integer press;
	
    private  Integer rateMin;
	
	private  Integer rateMax;

	public String getSamplename() {
		return samplename;
	}

	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}


	public Integer getRateMin() {
		return rateMin;
	}

	public void setRateMin(Integer rateMin) {
		this.rateMin = rateMin;
	}

	public Integer getRateMax() {
		return rateMax;
	}

	public void setRateMax(Integer rateMax) {
		this.rateMax = rateMax;
	}

	public Float getTemparture() {
		return temparture;
	}

	public void setTemparture(Float temparture) {
		this.temparture = temparture;
	}

	public Integer getPress() {
		return press;
	}

	public void setPress(Integer press) {
		this.press = press;
	}

	public String getTestModelName() {
		return testModelName;
	}

	public void setTestModelName(String testModelName) {
		this.testModelName = testModelName;
	}

	public String getTestSystemName() {
		return testSystemName;
	}

	public void setTestSystemName(String testSystemName) {
		this.testSystemName = testSystemName;
	}
	
	
	
	

}
