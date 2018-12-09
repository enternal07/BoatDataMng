package com.wisely.web.mng.data;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domain.small.Item;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;


/**
 * 大样基本信息管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/uploadManager")
public class UploadManger {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private SmallDemoMetaDataService service;
	
	@RequestMapping(value="uploadItems",method = RequestMethod.POST)
	public  ResultVO UploadExcle(MultipartFile file, String catalog,  HttpServletRequest request) throws IOException{
		ResultVO res = new ResultVO(false,"",null);
		
		return res;
	}
	
}
	