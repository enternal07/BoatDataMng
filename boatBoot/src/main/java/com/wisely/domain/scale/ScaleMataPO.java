package com.wisely.domain.scale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;

/**
 * 缩比模型的元数据，一组excle表的唯一标识
 * @author dingqi
 *
 */
@Entity
@Inheritance
@Table(name="scale_mata")
public class ScaleMataPO extends BaseModel{
	
	@Id //2
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	@Column(name = "test_model_obj_name")
	private String testModelObjName;
	
	@Column(name = "test_model_obj_pk")
	private String testModelObjPk;
	
	@Column(name = "laying_scheme_name")
	private String layingSchemeName;
	
	@Column(name = "laying_scheme_pk")
	private String layingSchemePk;
	
	@Column(name = "test_condition_name")
	private String testConditionName;

	@Column(name = "test_condition_pk")
	private String testConditionPk;
	
	@Transient
	private LayingSchemePO layingSchemePO;
	
	@Transient
	private TestConditionPO testConditionPO;
	
	@Transient
	private TestModelObjPO testModelObjPO;
	
	
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getTestModelObjName() {
		return testModelObjName;
	}

	public void setTestModelObjName(String testModelObjName) {
		this.testModelObjName = testModelObjName;
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

	public String getTestModelObjPk() {
		return testModelObjPk;
	}

	public void setTestModelObjPk(String testModelObjPk) {
		this.testModelObjPk = testModelObjPk;
	}

	public String getLayingSchemePk() {
		return layingSchemePk;
	}

	public void setLayingSchemePk(String layingSchemePk) {
		this.layingSchemePk = layingSchemePk;
	}

	public String getTestConditionPk() {
		return testConditionPk;
	}

	public void setTestConditionPk(String testConditionPk) {
		this.testConditionPk = testConditionPk;
	}

	public LayingSchemePO getLayingSchemePO() {
		return layingSchemePO;
	}

	public void setLayingSchemePO(LayingSchemePO layingSchemePO) {
		this.layingSchemePO = layingSchemePO;
	}

	public TestConditionPO getTestConditionPO() {
		return testConditionPO;
	}

	public void setTestConditionPO(TestConditionPO testConditionPO) {
		this.testConditionPO = testConditionPO;
	}

	public TestModelObjPO getTestModelObjPO() {
		return testModelObjPO;
	}

	public void setTestModelObjPO(TestModelObjPO testModelObjPO) {
		this.testModelObjPO = testModelObjPO;
	}
	

}
