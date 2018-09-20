package com.wisely.web.mng.info;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.big.TestSystem;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.TestSystemService;

@Controller
@RequestMapping(value = "/testModelMng")
public class TestSystemMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TestSystemService testModelService;
	
	@RequestMapping(value = "/saveTestSystem",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestSystem(@RequestBody TestSystem testModel,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestSystem tempDemoData = null ; 
		try {
			tempDemoData = testModelService.saveEntity(testModel) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyTestSystem",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestSystem(@RequestBody TestSystem testModel,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestSystem tempDemoData = null ; 
		try {
			tempDemoData = testModelService.updateEntity(testModel) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteTestSystem",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestSystem(@RequestParam("pk") String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			testModelService.deleteEntity(pk) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
