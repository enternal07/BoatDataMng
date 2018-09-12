package com.wisely.domain.small;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;

/**
 * 背衬模型
背衬：阿波罗 
样品前端介质：1.05kg/cm3 
背衬后端介质：50MPa
其他：

 * @author dingqi
 *
 */
@Entity
@Inheritance
@Table(name="samllbacking")
public class BaseMetaBacking extends BaseModel implements Serializable {
	

	@Id //2
//	@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	@Column(name = "backing_name")
	private String name;
	
	@Column(name = "front_medium")
	private String frontMedium;
	
	@Column(name = "end_medium")
	private String endMedium;
	
	private String logo;
	
	private String other;
	
	@Column(name = "sample_pk")
	private String samplePk;

	public String getSamplePk() {
		return samplePk;
	}

	public void setSamplePk(String samplePk) {
		this.samplePk = samplePk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrontMedium() {
		return frontMedium;
	}

	public void setFrontMedium(String frontMedium) {
		this.frontMedium = frontMedium;
	}

	public String getEndMedium() {
		return endMedium;
	}

	public void setEndMedium(String endMedium) {
		this.endMedium = endMedium;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	} 
	


	

}
