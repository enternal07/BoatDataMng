package com.wisely.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.PhotoDao;
import com.wisely.domain.common.Photo;
import com.wisely.service.IPhotoService;
import com.wisely.util.Toolkit;

@Service
public class PhotoServiceImpl implements IPhotoService{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PhotoDao photoDao; 
	
	@Override
	public Photo saveEntity(Photo photo) {
		Photo pht = null ; 
		try {
			pht  = photoDao.save(photo);
		} catch (Exception e) {
			logger.error("save entity error", e);
		}
		return pht;
	}

	@Override
	public void deleteEntity(String pk) {

		if(Toolkit.notEmpty(pk)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			photoDao.deleteByPK(pk,sdf.format(new Date()));
		}
	}


}
