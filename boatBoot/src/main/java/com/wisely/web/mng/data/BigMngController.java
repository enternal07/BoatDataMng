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
import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BigDemoMetadataService;

/**
 * 声管大样后台管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/bigMng")
public class BigMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BigDemoMetadataService service ; 
	
	@RequestMapping(value = "/saveSmall",method = RequestMethod.POST)
	public @ResponseBody ResultVO addSmall(@RequestBody BigDemoMetadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BigDemoMetadata tempDemoData = null ; 
		try {
			tempDemoData = service.saveEntity(demometadata) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("save entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/modifySmall",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifySmall(@RequestBody BigDemoMetadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BigDemoMetadata tempDemoData = null ; 
		try {
			tempDemoData = service.updateEntity(demometadata) ;
			re.setData(tempDemoData);
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("update entity error", e);
		} 
		return re;
	}
	
	@RequestMapping(value = "/deleteSmall/{pk}",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteSmall(@PathVariable(value="pk")String pk,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		try {
			service.deleteEntity(pk) ;
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