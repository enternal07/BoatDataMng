package com.wisely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance
@Table(name="contractionmetadata")
public class ContractionMetadata extends BaseMeta{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id //2
	//@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	private String testtime;

	

	public String getTesttime() {
		return testtime;
	}

	public void setTesttime(String testtime) {
		this.testtime = testtime;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	
	public String toString(){
		return "样品名称："+this.getSamplename()+",背衬："+this.getBackgroundtype()+",测试时间："+this.getTesttime();
		
	}
}

