package com.wisely.domainVO;

import java.io.Serializable;

/*
 * 查询模型
 */
public class SacleQueryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String layingSchemeName;
	
	private String testConditionName;
	
	private  String testModelObjName;
	
    private  Integer rateMin;
	
	private  Integer rateMax;

	public SacleQueryVO(){}
	
    public SacleQueryVO(String layingSchemeName, String testConditionName, String testModelObjName, Integer rateMin,
			Integer rateMax) {
		super();
		this.layingSchemeName = layingSchemeName;
		this.testConditionName = testConditionName;
		this.testModelObjName = testModelObjName;
		this.rateMin = rateMin;
		this.rateMax = rateMax;
	}



	public String getLayingSchemeName() {
		return layingSchemeName;
	}

	public void setLayingSchemeName(String layingSchemeName) {
		this.layingSchemeName = layingSchemeName;
	}

	public String getTestConditionName() {
		return testConditionName;
	}

	public void setTestConditionName(String testConditionName) {
		this.testConditionName = testConditionName;
	}

	public String getTestModelObjName() {
		return testModelObjName;
	}

	public void setTestModelObjName(String testModelObjName) {
		this.testModelObjName = testModelObjName;
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


	

}
