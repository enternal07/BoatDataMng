package com.wisely.domainVO;

import java.io.Serializable;

/*
 * 查询模型
 */
public class QueryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String samplename;
	
	//private String backgroundtype;
	private String backingname;
	
	private  Float temparture;
	
	//private Integer press;
	private Float press;
	
    private  Integer rateMin;
	
	private  Integer rateMax;
	
	public QueryVO(){}
	
	public QueryVO(String samplename, String backingname, Float temparture, Float press, Integer rateMin,
			Integer rateMax) {
		super();
		this.samplename = samplename;
		this.backingname = backingname;
		this.temparture = temparture;
		this.press = press;
		this.rateMin = rateMin;
		this.rateMax = rateMax;
	}



	public String getSamplename() {
		return samplename;
	}

	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}

	public String getBackingname() {
		return backingname;
	}

	public void setBackingname(String backingname) {
		this.backingname = backingname;
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


	public Float getPress() {
		return press;
	}


	public void setPress(Float press) {
		this.press = press;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
