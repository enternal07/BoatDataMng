package com.wisely.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity 
@Inheritance
@Table(name="spha_photo")
public class Photo extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "mcid")
	@GenericGenerator(name="mcid",strategy="uuid2")
	private String pk;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "prevname")
	private String prevName;
	
	@Column(name = "infotype")
	private String infotype;
	
	@Column(name = "model_pk")
	private String modelPK;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "deleted") //1删除 0 未删除
	private int deleted;
	
	@Column(name = "updatetime") //1删除 0 未删除
	private String updatetime;

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

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getModelPK() {
		return modelPK;
	}

	public void setModelPK(String modelPK) {
		this.modelPK = modelPK;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPrevName() {
		return prevName;
	}

	public void setPrevName(String prevName) {
		this.prevName = prevName;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
}