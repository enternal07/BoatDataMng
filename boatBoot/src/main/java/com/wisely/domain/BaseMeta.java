package com.wisely.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMeta extends BaseModel implements Serializable {

	private String samplename; 
	
	private String sampledescription;
	
	private String samplelogo;
	
	private String backgroundtype;
	
	private String backgroundtyplogo;
	
	
	
   public String getBackgroundtype() {
		return backgroundtype;
	}

	public void setBackgroundtype(String backgroundtype) {
		this.backgroundtype = backgroundtype;
	}

	public String getSampledescription() {
		return sampledescription;
	}

	public void setSampledescription(String sampledescription) {
		this.sampledescription = sampledescription;
	}

	public String getSamplelogo() {
		return samplelogo;
	}

	public void setSamplelogo(String samplelogo) {
		this.samplelogo = samplelogo;
	}

	public String getBackgroundtyplogo() {
		return backgroundtyplogo;
	}

	public void setBackgroundtyplogo(String backgroundtyplogo) {
		this.backgroundtyplogo = backgroundtyplogo;
	}

	public String getSamplename() {
		return samplename;
	}

	public void setSamplename(String samplename) {
		this.samplename = samplename;
	}


	
}
