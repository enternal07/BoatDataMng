package com.wisely.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMeta extends BaseModel implements Serializable {

	private String samplename; 
	
	private String backgroundtype;

	
	
	
   public String getBackgroundtype() {
		return backgroundtype;
	}

	public void setBackgroundtype(String backgroundtype) {
		this.backgroundtype = backgroundtype;
	}



	public String getSamplename() {
		return samplename;
	}

	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}


	
}
