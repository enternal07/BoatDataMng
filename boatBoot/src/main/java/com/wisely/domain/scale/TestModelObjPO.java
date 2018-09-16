package com.wisely.domain.scale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BasePhotoModel;

/***
 * 外场试验模型
 */
@Entity
@Inheritance
@Table(name="test_model_obj")
public class TestModelObjPO  extends BasePhotoModel{
	
	@Id //2
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	
	private String name;
	 
	//壳体类型
	@Column(name = "shell_type")
	 private String shellType;
	 
	//尺寸
	@Column(name = "shape_size")
	 private String shapeSize;
	 
	 private String weight;
	 
	 /**
	  * 排水量
	  */
	 private String displacement;
	 
	 private String other;
	 
	 private String logo;
	
	
	
	 public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShellType() {
		return shellType;
	}

	public void setShellType(String shellType) {
		this.shellType = shellType;
	}

	public String getShapeSize() {
		return shapeSize;
	}

	public void setShapeSize(String shapeSize) {
		this.shapeSize = shapeSize;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	
	

}


