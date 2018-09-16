package com.wisely.domainVO.mng.data;

/**
 * 小样数据列表VO
 * @author liqz
 *
 */
public class SmallItemVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String samplepk; 
	private String samplename ; 
	private String bakingpk ; 
	private String backingname ; 
	private Integer press ; 
	private Float temparture ; 
	private Float refect ; 
	private Float transmission ; 
	private Float bondacust ; 
	private Integer rate;
	
	public String getSamplepk() {
		return samplepk;
	}
	public void setSamplepk(String samplepk) {
		this.samplepk = samplepk;
	}
	public String getSamplename() {
		return samplename;
	}
	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}
	public String getBakingpk() {
		return bakingpk;
	}
	public void setBakingpk(String bakingpk) {
		this.bakingpk = bakingpk;
	}
	public String getBackingname() {
		return backingname;
	}
	public void setBackingname(String backingname) {
		this.backingname = backingname;
	}
	public Integer getPress() {
		return press;
	}
	public void setPress(Integer press) {
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
	
	
	
}
