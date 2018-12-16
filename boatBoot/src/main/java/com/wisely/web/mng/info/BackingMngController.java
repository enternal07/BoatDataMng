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

import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaBackingService;
import com.wisely.util.Toolkit;

@Controller
@RequestMapping(value = "/backingMng")
public class BackingMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BaseMetaBackingService baseMetaBackingService ; 
	
	@RequestMapping(value = "/saveBacking",method = RequestMethod.POST)
	public @ResponseBody ResultVO addBacking(@RequestBody BaseMetaBacking baseMetaBacking,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			BaseMetaBacking result = null;
			if(Toolkit.notEmpty(baseMetaBacking)&&Toolkit.notEmpty(baseMetaBacking.getName())){
				if(Toolkit.isEmpty(baseMetaBackingService.getByName(baseMetaBacking.getName()))){
					result =  baseMetaBackingService.saveEntity(baseMetaBacking);
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
	
	@RequestMapping(value = "/modifyBacking",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifyBacking(@RequestBody BaseMetaBacking baseMetaBacking,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		BaseMetaBacking tempDemoData = null ; 
		try {
			if(baseMetaBackingService.queryOtherNameCount(baseMetaBacking)==0){
				tempDemoData = baseMetaBackingService.updateEntity(baseMetaBacking) ;
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
	
	@RequestMapping(value = "/deleteBacking",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteBacking(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = baseMetaBackingService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}

}
