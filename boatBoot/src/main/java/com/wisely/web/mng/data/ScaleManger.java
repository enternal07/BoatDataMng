package com.wisely.web.mng.data;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domain.small.Item;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;

/**
 * 缩放比模型基本信息管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/scaleManager")
public class ScaleManger {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private SmallDemoMetaDataService service;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public @ResponseBody ResultVO save(@RequestBody   ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		return re;
	}
	
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public @ResponseBody ResultVO modify(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		
		return re;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public @ResponseBody ResultVO delete(@RequestBody DeleteVO deleteVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		
		return re;
	}
	
	@RequestMapping(value = "/queryByName",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByName(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		
		return re;
	}
	
	@RequestMapping(value = "/queryByCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByCondition(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		return re;
	}
	
}
	