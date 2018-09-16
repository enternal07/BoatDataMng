package com.wisely.web.mng.info;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.scale.TestConditionPO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.TestConditionService;

@Controller
@RequestMapping(value = "/testConditionMng")
public class TestConditionMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private TestConditionService testConditionService;
	
	@RequestMapping(value = "/saveTestCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestCondition(@RequestBody TestConditionPO testCondition,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestConditionPO tempDemoData = null ; 
		try {
			tempDemoData = testConditionService.saveEntity(testCondition) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyTestCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestCondition(@RequestBody TestConditionPO testCondition,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestConditionPO tempDemoData = null ; 
		try {
			tempDemoData = testConditionService.updateEntity(testCondition) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteTestCondition/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestCondition(@PathVariable(value="pk") String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			testConditionService.deleteEntity(pk) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
