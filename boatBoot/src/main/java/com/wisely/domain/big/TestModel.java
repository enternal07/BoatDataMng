package com.wisely.domain.big;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;

/**
 * 大样数据试验模型
 * */

//大样模型的测试模型
@Entity
@Inheritance
@Table(name="testmodel")
public class TestModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //2
//	@GeneratedValue //3
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	//试验模型名称
	@Column(name="testmodel_name")
	private String name;
	
	//尺寸
	private String size;
	
	//双层壳间距
	@Column(name = "double_shell_spacing")
	private String doubleShellSpacing;
	
	//内壳厚度
	@Column(name = "inner_shell_thickness")
	private String innerShellThickness;
	
	//外壳厚度
	@Column(name = "shell_thickness")
	private String  shellThickness;
	
	//内壳后端
	@Column(name = "inner_shell_backend")
	private String innerShellBackend;
	
	//其他
	private String other;
	
	//logo
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDoubleShellSpacing() {
		return doubleShellSpacing;
	}

	public void setDoubleShellSpacing(String doubleShellSpacing) {
		this.doubleShellSpacing = doubleShellSpacing;
	}

	public String getInnerShellThickness() {
		return innerShellThickness;
	}

	public void setInnerShellThickness(String innerShellThickness) {
		this.innerShellThickness = innerShellThickness;
	}

	public String getShellThickness() {
		return shellThickness;
	}

	public void setShellThickness(String shellThickness) {
		this.shellThickness = shellThickness;
	}

	public String getInnerShellBackend() {
		return innerShellBackend;
	}

	public void setInnerShellBackend(String innerShellBackend) {
		this.innerShellBackend = innerShellBackend;
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
