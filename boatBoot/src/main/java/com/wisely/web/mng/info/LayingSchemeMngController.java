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

import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.LayingSchemeService;
import com.wisely.util.Toolkit;


@Controller
@RequestMapping(value = "/layingSchemeMng")
public class LayingSchemeMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LayingSchemeService layingSchemeService;
	
	@RequestMapping(value = "/saveLayingSchemePO",method = RequestMethod.POST)
	public @ResponseBody ResultVO addLayingSchemePO(@RequestBody LayingSchemePO layingSchemePO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			LayingSchemePO result = null;
			if(Toolkit.notEmpty(layingSchemePO)&&Toolkit.notEmpty(layingSchemePO.getName())){
				if(Toolkit.isEmpty(layingSchemeService.getByName(layingSchemePO.getName()))){
					result =  layingSchemeService.saveEntity(layingSchemePO);
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
	
	@RequestMapping(value = "/modifyLayingSchemePO",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyLayingSchemePO(@RequestBody LayingSchemePO layingSchemePO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		LayingSchemePO tempDemoData = null ; 
		try {
			if(layingSchemeService.queryOtherNameCount(layingSchemePO)==0){
				tempDemoData = layingSchemeService.updateEntity(layingSchemePO) ;
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
	
	@RequestMapping(value = "/deleteLayingSchemePO",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteLayingSchemePO(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = layingSchemeService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}

}
