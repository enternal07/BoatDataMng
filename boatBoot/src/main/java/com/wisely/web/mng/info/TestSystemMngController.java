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

import com.wisely.domain.big.TestSystem;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.TestSystemService;
import com.wisely.util.Toolkit;

@Controller
@RequestMapping(value = "/testSystemMng")
public class TestSystemMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TestSystemService testSystemService;
	
	@RequestMapping(value = "/saveTestSystem",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestSystem(@RequestBody TestSystem testSystem,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			TestSystem result = null;
			if(Toolkit.notEmpty(testSystem)&&Toolkit.notEmpty(testSystem.getName())){
				if(Toolkit.isEmpty(testSystemService.getByName(testSystem.getName()))){
					result =  testSystemService.saveEntity(testSystem);
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
	
	@RequestMapping(value = "/modifyTestSystem",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestSystem(@RequestBody TestSystem testSystem,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		TestSystem tempDemoData = null ; 
		try {
			if(testSystemService.queryOtherNameCount(testSystem)==0){
				tempDemoData = testSystemService.updateEntity(testSystem) ;
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
	
	@RequestMapping(value = "/deleteTestSystem",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestSystem(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = testSystemService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
