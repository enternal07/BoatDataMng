package com.wisely.web.mng.data;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.scale.ItemScalePO;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.PKVO;
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
	private ScaleMataService service;
	
	@RequestMapping(value = "/saveScale",method = RequestMethod.POST)
	public @ResponseBody ResultVO addScale(@RequestBody ScaleMataPO scaleMataPO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		try {
			tempDemoData = service.saveEntity(scaleMataPO) ;
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
			tempDemoData = service.updateEntity(scaleMataPO) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteScale",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteScale(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			service.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/queryFull",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryFull(HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			re.setData(service.queryFull()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("query all entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/queryDetail",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryDetail(@RequestBody PKVO pKVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			re.setData(service.queryDetail(pKVO.getPk())) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("query detail entity error", e);
		} 
		return re;
	}
	
	/*Items Action*/
	
	@RequestMapping(value = "/saveItem",method = RequestMethod.POST)
	public @ResponseBody ResultVO saveItem(@RequestBody ItemScalePO item,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ItemScalePO tempItem = null ; 
		try {
			tempItem = service.saveItem(item) ;
			re.setData(tempItem);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifyItem",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyItem(@RequestBody ItemScalePO item,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ItemScalePO tempItem = null ; 
		try {
			tempItem = service.updateItem(item) ;
			re.setData(tempItem);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteItem",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteItem(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			service.deleteItem(delVO.getPk());
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteItems",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteItems(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			service.deleteItems(delVO.getPks());
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
			re.setData(service.findAllItem()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("query all entity error", e);
		} 
		return re;
	}
	
	
	
}