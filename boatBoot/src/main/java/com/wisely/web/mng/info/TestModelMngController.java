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
import com.wisely.domain.big.TestModel;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.TestModelService;

@Controller
@RequestMapping(value = "/testModelMng")
public class TestModelMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private TestModelService testModelService;
	
	@RequestMapping(value = "/saveTestModel",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestModel(@RequestBody TestModel testModel,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestModel tempDemoData = null ; 
		try {
			tempDemoData = testModelService.saveEntity(testModel) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyTestModel",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestModel(@RequestBody TestModel testModel,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		TestModel tempDemoData = null ; 
		try {
			tempDemoData = testModelService.updateEntity(testModel) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteTestModel/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestModel(@PathVariable(value="pk")String pk,HttpServletRequest req){
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
