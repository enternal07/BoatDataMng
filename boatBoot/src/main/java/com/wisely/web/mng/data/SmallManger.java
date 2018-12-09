package com.wisely.web.mng.data;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.small.Demometadata;
import com.wisely.domain.small.Item;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;


/**
 * 声管小样基本信息管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/smallManager")
public class SmallManger {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private SmallDemoMetaDataService service;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public @ResponseBody ResultVO save(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		return re;
	}
	
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public @ResponseBody ResultVO modify(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		
		return re;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public @ResponseBody ResultVO delete(@RequestBody DeleteVO deleteVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		
		return re;
	}
	
	@RequestMapping(value = "/queryByName",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByName(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		
		return re;
	}
	
	@RequestMapping(value = "/queryByCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByCondition(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		return re;
	}
	
	
}
	