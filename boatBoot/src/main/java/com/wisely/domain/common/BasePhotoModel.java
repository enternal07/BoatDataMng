package com.wisely.domain.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BasePhotoModel implements Serializable{

	private static final long serialVersionUID = 8948278364847350273L;

	private Date ts = new Date();
	
	@OneToMany(targetEntity=Photo.class,cascade={CascadeType.MERGE})
	@JoinColumn(name="model_pk")
	protected List<Photo> photos;
	
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

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


