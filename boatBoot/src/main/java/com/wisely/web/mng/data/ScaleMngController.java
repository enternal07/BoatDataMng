package com.wisely.web.mng.data;

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

import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.ScaleMataService;

/**
 * 缩放模型后台管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/scaleMng")
public class ScaleMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ScaleMataService scaleMataService;
	
	@RequestMapping(value = "/saveScale",method = RequestMethod.POST)
	public @ResponseBody ResultVO addScale(@RequestBody ScaleMataPO scaleMataPO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		try {
			tempDemoData = scaleMataService.saveEntity(scaleMataPO) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyScale",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyScale(@RequestBody ScaleMataPO scaleMataPO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		try {
			tempDemoData = scaleMataService.updateEntity(scaleMataPO) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteScale/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteScale(@PathVariable(value="pk")String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			scaleMataService.deleteEntity(pk) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	@RequestMapping(value = "/queryAll",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryAll(HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			re.setData(scaleMataService.findAllItem()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("query all entity error", e);
		} 
		return re;
	}
	
}