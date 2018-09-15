package com.wisely.domain.big;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;

/**
 * 大样数据Item
 */
@Entity //
@Table(name="itembig")
@Inheritance
public class ItemBig  extends BaseModel{
	
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
	private BigDemoMetadata bigDemoMetadata;
	
	//频率						
	private Integer rate;
	
	//反射系数
	private Float refect;
	
	//透射系数
	private Float transmission;
	
	//吸声系数
	private Float bondacust;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//回声降低
	private Float echoes;
	
	//辐射声功率/dB
	private int radiation;
	
	//辐射声功率插入损失
	private Float radiationlose;
	
	public Float getEchoes() {
		return echoes;
	}

	public void setEchoes(Float echoes) {
		this.echoes = echoes;
	}

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

	public BigDemoMetadata getBigDemoMetadata() {
		return bigDemoMetadata;
	}

	public void setBigDemoMetadata(BigDemoMetadata bigDemoMetadata) {
		this.bigDemoMetadata = bigDemoMetadata;
	}

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

	
	

}
