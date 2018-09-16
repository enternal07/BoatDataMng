package com.wisely.service;

import com.wisely.domain.common.Photo;

public interface IPhotoService {
	
	String saveEntity(Photo photo);
	
	void deleteEntity(String pk);
	
}
