package com.wisely.domainVO.mng.data;

/**
 * 大样数据列表VO
 * @author liqz
 *
 */
public class BigItemVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pk;
	private String name;
	private String sampleName;
	private String samplepk;
	private String testModelName;
	private String testModelPk;
	private String testSystemName;
	private String testSystemPk;
	//private Integer press ; 
	private Float press ; 
	private Float temparture ; 
	private Float refect ; 
	private Float transmission ; 
	private Float bondacust ; 
	private Integer rate;
	private Integer radiation;
	private Float   radiationlose;
	private Float  echoes;
	
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getSamplepk() {
		return samplepk;
	}
	public void setSamplepk(String samplepk) {
		this.samplepk = samplepk;
	}
	public String getTestModelName() {
		return testModelName;
	}
	public void setTestModelName(String testModelName) {
		this.testModelName = testModelName;
	}
	public String getTestModelPk() {
		return testModelPk;
	}
	public void setTestModelPk(String testModelPk) {
		this.testModelPk = testModelPk;
	}
	public String getTestSystemName() {
		return testSystemName;
	}
	public void setTestSystemName(String testSystemName) {
		this.testSystemName = testSystemName;
	}

	public Float getPress() {
		return press;
	}
	public void setPress(Float press) {
		this.press = press;
	}
	public Float getTemparture() {
		return temparture;
	}
	public void setTemparture(Float temparture) {
		this.temparture = temparture;
	}
	public Float getRefect() {
		return refect;
	}
	public void setRefect(Float refect) {
		this.refect = refect;
	}
	public Float getTransmission() {
		return transmission;
	}
	public void setTransmission(Float transmission) {
		this.transmission = transmission;
	}
	public Float getBondacust() {
		return bondacust;
	}
	public void setBondacust(Float bondacust) {
		this.bondacust = bondacust;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Integer getRadiation() {
		return radiation;
	}
	public void setRadiation(Integer radiation) {
		this.radiation = radiation;
	}
	public Float getRadiationlose() {
		return radiationlose;
	}
	public void setRadiationlose(Float radiationlose) {
		this.radiationlose = radiationlose;
	}
	public Float getEchoes() {
		return echoes;
	}
	public void setEchoes(Float echoes) {
		this.echoes = echoes;
	}
	public String getTestSystemPk() {
		return testSystemPk;
	}
	public void setTestSystemPk(String testSystemPk) {
		this.testSystemPk = testSystemPk;
	}
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
	

}
