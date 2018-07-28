package com.wisely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity 
@Inheritance
@Table(name="item")
public class Item extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	@ManyToOne
	@JoinColumn(name="small_pk")
	private Demometadata smallPO;
	
//	@ManyToOne
//	@JoinColumn(name="sample_pk")
//	private BaseMetaSample samplePO;
	
//	@ManyToOne
//	@JoinColumn(name="backing_pk")
	//private BaseMetaBacking bakingPO;

	
	//频率						
	private Integer rate;
	
	//反射系数
	private Float refect;
	
	//透射系数
	private Float transmission;
	
	//吸声系数
	private Float bondacust;


	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

//	public BaseMetaSample getSamplePO() {
//		return samplePO;
//	}
//
//	public void setSamplePO(BaseMetaSample samplePO) {
//		this.samplePO = samplePO;
//	}

//	public BaseMetaBacking getBakingPO() {
//		return bakingPO;
//	}
//
//	public void setBakingPO(BaseMetaBacking bakingPO) {
//		this.bakingPO = bakingPO;
//	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
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

	public Demometadata getSmallPO() {
		return smallPO;
	}

	public void setSmallPO(Demometadata samllPO) {
		this.smallPO = samllPO;
	}
	
	
	
}
