package com.wisely.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 样品模型
 * 名称：阿波罗 
密度：1.05kg/cm3 
弹性模量：50MPa
泊松比：0.497
声速：1580m/s 
厚度：50mm 
其他：
 * @author dingqi
 *
 */
@Entity
@Inheritance
@Table(name="sample")
public class BaseMetaSample extends BaseModel implements Serializable {


	@Id //2
//	@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	@Column(name = "sample_name")
	private String name; 

	private String logo;
	
	private String  density;
	
	@Column(name = "flexible_model")
	private String flexibleModel;
	
	@Column(name = "poisson_ratio")
	private String poissonRatio;
	
	@Column(name = "sound_speed")
	private String  soundSpeed;
	
	private String thickness;
	
	private String other;
	
	private String userpk;
	
	private boolean small = true;

	public boolean isSmall() {
		return small;
	}

	public void setSmall(boolean small) {
		this.small = small;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getFlexibleModel() {
		return flexibleModel;
	}

	public void setFlexibleModel(String flexibleModel) {
		this.flexibleModel = flexibleModel;
	}

	public String getPoissonRatio() {
		return poissonRatio;
	}

	public void setPoissonRatio(String poissonRatio) {
		this.poissonRatio = poissonRatio;
	}

	public String getSoundSpeed() {
		return soundSpeed;
	}

	public void setSoundSpeed(String soundSpeed) {
		this.soundSpeed = soundSpeed;
	}

	public String getThickness() {
		return thickness;
	}

	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getUserpk() {
		return userpk;
	}

	public void setUserpk(String userpk) {
		this.userpk = userpk;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	
	


	
}
