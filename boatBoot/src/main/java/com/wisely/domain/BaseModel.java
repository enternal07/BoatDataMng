package com.wisely.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 8948278364847350273L;

	private Date ts = new Date();
	
	@PrePersist
	@PreUpdate
	public void updateTs() {
		ts = new Date();
	}	

    public Date getTs() {
		return ts;
	}

    public void setTs(Date ts) {
		this.ts = ts;
	}
	
}
