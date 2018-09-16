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

import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaBackingService;

@Controller
@RequestMapping(value = "/backingMng")
public class BackingMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BaseMetaBackingService baseMetaBackingService ; 
	
	@RequestMapping(value = "/saveBacking",method = RequestMethod.POST)
	public @ResponseBody ResultVO addBacking(@RequestBody BaseMetaBacking baseMetaBacking,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BaseMetaBacking tempDemoData = null ; 
		try {
			tempDemoData = baseMetaBackingService.saveEntity(baseMetaBacking) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyBacking",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyBacking(@RequestBody BaseMetaBacking baseMetaBacking,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BaseMetaBacking tempDemoData = null ; 
		try {
			tempDemoData = baseMetaBackingService.updateEntity(baseMetaBacking) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteBacking/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteBacking(@PathVariable(value="pk")String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			baseMetaBackingService.deleteEntity(pk) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}

}
