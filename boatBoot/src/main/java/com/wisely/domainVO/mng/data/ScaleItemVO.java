package com.wisely.domainVO.mng.data;

/**
 * 缩放模型列表VO
 * @author liqz
 *
 */
public class ScaleItemVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  pk; 
	private String  testModelObjName ; 
	private String  testModelObjPk ; 
	private String  layingSchemeName ; 
	private String  layingSchemePk ; 
	private String  testConditionName ; 
	private String  testConditionPk ; 
	private Float  lightShellTS ; 
	private Float  lightShellSP ; 
	private Float  layingShellTS ; 
	private Float  layingShellSP ; 
	private Float   reductionTS ; 
	private Float  reductionSP ;
	
	public String getTestModelObjName() {
		return testModelObjName;
	}
	public void setTestModelObjName(String testModelObjName) {
		this.testModelObjName = testModelObjName;
	}
	public String getTestModelObjPk() {
		return testModelObjPk;
	}
	public void setTestModelObjPk(String testModelObjPk) {
		this.testModelObjPk = testModelObjPk;
	}
	public String getLayingSchemeName() {
		return layingSchemeName;
	}
	public void setLayingSchemeName(String layingSchemeName) {
		this.layingSchemeName = layingSchemeName;
	}
	public String getLayingSchemePk() {
		return layingSchemePk;
	}
	public void setLayingSchemePk(String layingSchemePk) {
		this.layingSchemePk = layingSchemePk;
	}
	public String getTestConditionName() {
		return testConditionName;
	}
	public void setTestConditionName(String testConditionName) {
		this.testConditionName = testConditionName;
	}
	public String getTestConditionPk() {
		return testConditionPk;
	}
	public void setTestConditionPk(String testConditionPk) {
		this.testConditionPk = testConditionPk;
	}
	public Float getLightShellTS() {
		return lightShellTS;
	}
	public void setLightShellTS(Float lightShellTS) {
		this.lightShellTS = lightShellTS;
	}
	public Float getLightShellSP() {
		return lightShellSP;
	}
	public void setLightShellSP(Float lightShellSP) {
		this.lightShellSP = lightShellSP;
	}
	public Float getLayingShellTS() {
		return layingShellTS;
	}
	public void setLayingShellTS(Float layingShellTS) {
		this.layingShellTS = layingShellTS;
	}
	public Float getLayingShellSP() {
		return layingShellSP;
	}
	public void setLayingShellSP(Float layingShellSP) {
		this.layingShellSP = layingShellSP;
	}
	public Float getReductionTS() {
		return reductionTS;
	}
	public void setReductionTS(Float reductionTS) {
		this.reductionTS = reductionTS;
	}
	public Float getReductionSP() {
		return reductionSP;
	}
	public void setReductionSP(Float reductionSP) {
		this.reductionSP = reductionSP;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	} 
	

}
