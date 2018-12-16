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

import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.TestModelObjService;
import com.wisely.util.Toolkit;

@Controller
@RequestMapping(value = "/testModelObjMng")
public class TestModelObjMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TestModelObjService TestModelObjService;
	
	@RequestMapping(value = "/saveTestModelObj",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestModelObj(@RequestBody TestModelObjPO testModelObj,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			TestModelObjPO result = null;
			if(Toolkit.notEmpty(testModelObj)&&Toolkit.notEmpty(testModelObj.getName())){
				if(Toolkit.isEmpty(TestModelObjService.getByName(testModelObj.getName()))){
					result =  TestModelObjService.saveEntity(testModelObj);
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
	
	@RequestMapping(value = "/modifyTestModelObj",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestModelObj(@RequestBody TestModelObjPO testModelObj,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		TestModelObjPO tempDemoData = null ; 
		try {
			if(TestModelObjService.queryOtherNameCount(testModelObj)==0){
				tempDemoData = TestModelObjService.updateEntity(testModelObj) ;
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
	
	@RequestMapping(value = "/deleteTestModelObj",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestModelObj(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = TestModelObjService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
