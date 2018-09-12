package com.wisely.domain.scale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wisely.domain.common.BaseModel;



@Entity
@Inheritance
@Table(name="laying_scheme")
public class LayingSchemePO  extends BaseModel{
	
	@Id //2
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	private String name;
//外壳外表面
	@Column(name = "shell_surface_outer")
	private String shellSurfaceOuter;
	//外壳内表面
	@Column(name = "shell_surface_iner")
	private String shellSurfaceIner;
	
	//内壳
	@Column(name = "inner_shell")
	private String innerShell;
	
	private String ribs;
	
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

	public String getShellSurfaceOuter() {
		return shellSurfaceOuter;
	}

	public void setShellSurfaceOuter(String shellSurfaceOuter) {
		this.shellSurfaceOuter = shellSurfaceOuter;
	}

	public String getShellSurfaceIner() {
		return shellSurfaceIner;
	}

	public void setShellSurfaceIner(String shellSurfaceIner) {
		this.shellSurfaceIner = shellSurfaceIner;
	}

	public String getInnerShell() {
		return innerShell;
	}

	public void setInnerShell(String innerShell) {
		this.innerShell = innerShell;
	}

	public String getRibs() {
		return ribs;
	}

	public void setRibs(String ribs) {
		this.ribs = ribs;
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
