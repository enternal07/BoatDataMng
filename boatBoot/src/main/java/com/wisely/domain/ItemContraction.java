package com.wisely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity //
@Table(name="itemcontraction")
@Inheritance
public class ItemContraction  extends BaseModel{
	
	@Id //2
//	@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	
	@ManyToOne
	@JoinColumn(name="samplepk")
	private ContractionMetadata samplPO;
	
	//频率						
	private Integer rate;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//目标强度
	private Float target;
	
	//辐射声功率/dB
	private int radiation;
	
	//辐射声功率插入损失
	private Float radiationlose;
	
	

	public int getRadiation() {
		return radiation;
	}

	public void setRadiation(int radiation) {
		this.radiation = radiation;
	}

	public Float getRadiationlose() {
		return radiationlose;
	}

	public void setRadiationlose(Float radiationlose) {
		this.radiationlose = radiationlose;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Float getTarget() {
		return target;
	}

	public void setTarget(Float target) {
		this.target = target;
	}

	public ContractionMetadata getSamplPO() {
		return samplPO;
	}

	public void setSamplPO(ContractionMetadata samplPO) {
		this.samplPO = samplPO;
	}

	
	

}
