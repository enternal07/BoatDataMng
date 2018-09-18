package com.wisely.domain.big;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.common.BaseModel;
/**
 * 记录bigDemo的原数据信息,
 * 一张大样excle表的元数据信息。
 * @author dingqi
 *
 */
@Entity 
@Inheritance
@Table(name="bigmetadata")
public class BigDemoMetadata  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
	//@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	private Float temparture;
	
	private Integer press;
	
	@Column(name="sample_name")
	private String sampleName;
	
	@Column(name="testmodel_name")
	private String testmodelName;
	
	@Column(name="testsystem_name")
	private String testsystemName;
	
	
	@Column(name="sample_pk")
    private String samplepk;
	
    @Column(name="testmodel_pk")
	private String testModelPk;
    
    @Column(name="testsystem_pk")
	private String testSystemPk;
	/**
	 * 样品信息
	 */
	@Transient
	BaseMetaSample  sample;
	/**
	 * 试验模型
	 */
	@Transient
	TestModel  testModel;
	/**
	 * 测试系统
	 */
	@Transient
	TestSystem  testSystem;
	
	public String toString() {
		return "样品名称："+this.getSampleName()+",温度："+this.getTemparture()+",压力："+this.getPress();
		
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
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

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getTestmodelName() {
		return testmodelName;
	}

	public void setTestmodelName(String testmodelName) {
		this.testmodelName = testmodelName;
	}

	public String getTestsystemName() {
		return testsystemName;
	}

	public void setTestsystemName(String testsystemName) {
		this.testsystemName = testsystemName;
	}

	public String getSamplepk() {
		return samplepk;
	}

	public void setSamplepk(String samplepk) {
		this.samplepk = samplepk;
	}

	public String getTestModelPk() {
		return testModelPk;
	}

	public void setTestModelPk(String testModelPk) {
		this.testModelPk = testModelPk;
	}

	public String getTestSystemPk() {
		return testSystemPk;
	}

	public void setTestSystemPk(String testSystemPk) {
		this.testSystemPk = testSystemPk;
	}

	public BaseMetaSample getSample() {
		return sample;
	}

	public void setSample(BaseMetaSample sample) {
		this.sample = sample;
	}

	public TestModel getTestModel() {
		return testModel;
	}

	public void setTestModel(TestModel testModel) {
		this.testModel = testModel;
	}

	public TestSystem getTestSystem() {
		return testSystem;
	}

	public void setTestSystem(TestSystem testSystem) {
		this.testSystem = testSystem;
	}

}
