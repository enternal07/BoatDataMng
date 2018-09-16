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
import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.LayingSchemeService;


@Controller
@RequestMapping(value = "/layingSchemeMng")
public class LayingSchemeMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private LayingSchemeService layingSchemeService;
	
	@RequestMapping(value = "/saveLayingSchemePO",method = RequestMethod.POST)
	public @ResponseBody ResultVO addLayingSchemePO(@RequestBody LayingSchemePO layingSchemePO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		LayingSchemePO tempDemoData = null ; 
		try {
			tempDemoData = layingSchemeService.saveEntity(layingSchemePO) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyLayingSchemePO",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyLayingSchemePO(@RequestBody LayingSchemePO layingSchemePO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		LayingSchemePO tempDemoData = null ; 
		try {
			tempDemoData = layingSchemeService.updateEntity(layingSchemePO) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteLayingSchemePO/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteLayingSchemePO(@PathVariable(value="pk")String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			layingSchemeService.deleteEntity(pk) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}

}
