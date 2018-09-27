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

import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.TestModelObjService;

@Controller
@RequestMapping(value = "/testModelObjMng")
public class TestModelObjMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TestModelObjService TestModelObjService;
	
	@RequestMapping(value = "/saveTestModelObj",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestModelObj(@RequestBody TestModelObjPO testModelObj,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestModelObjPO tempDemoData = null ; 
		try {
			tempDemoData = TestModelObjService.saveEntity(testModelObj) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyTestModelObj",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestModelObj(@RequestBody TestModelObjPO testModelObj,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestModelObjPO tempDemoData = null ; 
		try {
			tempDemoData = TestModelObjService.updateEntity(testModelObj) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteTestModelObj",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestModelObj(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			TestModelObjService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
