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
import com.wisely.domain.big.TestModel;
import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.TestModelService;
import com.wisely.util.Toolkit;

@Controller
@RequestMapping(value = "/testModelMng")
public class TestModelMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TestModelService testModelService;
	
	@RequestMapping(value = "/saveTestModel",method = RequestMethod.POST)
	public @ResponseBody ResultVO addTestModel(@RequestBody TestModel testModel,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			TestModel result = null;
			if(Toolkit.notEmpty(testModel)&&Toolkit.notEmpty(testModel.getName())){
				if(Toolkit.isEmpty(testModelService.getByName(testModel.getName()))){
					result =  testModelService.saveEntity(testModel);
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
	
	@RequestMapping(value = "/modifyTestModel",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyTestModel(@RequestBody TestModel testModel,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		TestModel tempDemoData = null ; 
		try {
			if(testModelService.queryOtherNameCount(testModel)==0){
				tempDemoData = testModelService.updateEntity(testModel) ;
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
	
	@RequestMapping(value = "/deleteTestModel",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteTestModel(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = testModelService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	

}
