package com.wisely.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity //
//@MappedSuperclass
@Table(name="item")
public class Item extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
//	@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	/*样品*/
	//@Column(name="sample_name")
	//private String samplename;
	
	//@Column(name="sample_pk")
	@ManyToOne
	@JoinColumn(name="samplepk")
	private Demometadata samplPO;
	
	//频率						
	private Integer rate;
	
	//反射系数
	private Float refect;
	
	//透射系数
	private Float transmission;
	
	//吸声系数
	private Float bondacust;
	
	
	
	public Item() {
		
	}
	
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

//	public String getSamplename() {
//		return samplename;
//	}
//
//	public void setSamplename(String samplename) {
//		this.samplename = samplename;
//	}

	

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public float getRefect() {
		return refect;
	}

	public void setRefect(float refect) {
		this.refect = refect;
	}

	public float getTransmission() {
		return transmission;
	}

	public void setTransmission(float transmission) {
		this.transmission = transmission;
	}

	public Float getBondacust() {
		return bondacust;
	}

	public void setBondacust(Float bondacust) {
		this.bondacust = bondacust;
	}

	public Demometadata getSamplPO() {
		return samplPO;
	}

	public void setSamplPO(Demometadata samplPO) {
		this.samplPO = samplPO;
	}


	
	

	
	

}
