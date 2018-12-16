package com.wisely.web.mng.info;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.scale.TestConditionPO;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.TestConditionService;
import com.wisely.util.Toolkit;

@Controller
@RequestMapping(value = "/testConditionMng")
public class TestConditionMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TestConditionService testConditionService;
	
	@RequestMapping(value = "/saveTestCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestCondition(@RequestBody TestConditionPO testCondition,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			TestConditionPO result = null;
			if(Toolkit.notEmpty(testCondition)&&Toolkit.notEmpty(testCondition.getName())){
				if(Toolkit.isEmpty(testConditionService.getByName(testCondition.getName()))){
					result =  testConditionService.saveEntity(testCondition);
					re.setData(result);
					re.setSuccess(true); 
				}else{
					re.setMessage("名称已经存在");
				}
			}else{
				re.setMessage("数据不能为空");
			}
			
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyTestCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestCondition(@RequestBody TestConditionPO testCondition,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		TestConditionPO tempDemoData = null ; 
		try {
			if(testConditionService.queryOtherNameCount(testCondition)==0){
				tempDemoData = testConditionService.updateEntity(testCondition) ;
				re.setData(tempDemoData);
				re.setSuccess(true);
			}else{
				re.setMessage("名称已经存在");
			}
			
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteTestCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestCondition(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = testConditionService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
