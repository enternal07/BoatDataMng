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
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaSampleService;


@Controller
@RequestMapping(value = "/sampleMng")
public class SampleMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BaseMetaSampleService sampleService;
	
	@RequestMapping(value = "/saveSample",method = RequestMethod.POST)
	public @ResponseBody ResultVO addSample(@RequestBody BaseMetaSample baseMetaSample,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BaseMetaSample tempDemoData = null ; 
		try {
			tempDemoData = sampleService.saveEntity(baseMetaSample) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifySample",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifySample(@RequestBody BaseMetaSample baseMetaSample,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BaseMetaSample tempDemoData = null ; 
		try {
			tempDemoData = sampleService.updateEntity(baseMetaSample) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteSample/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteSample(@PathVariable(value="pk")String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			sampleService.deleteEntity(pk) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}

}
