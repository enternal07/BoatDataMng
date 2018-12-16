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
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.util.Toolkit;

@Controller
@RequestMapping(value = "/sampleMng")
public class SampleMngController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BaseMetaSampleService sampleService;
	/**
	 * 新增 名称不可以有重复的
	 * 修改 香瓜的名称改过来
	 * 删除 有引用不你能删除
	 */
	@RequestMapping(value = "/saveSample",method = RequestMethod.POST)
	public @ResponseBody ResultVO addSample(@RequestBody BaseMetaSample baseMetaSample,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			BaseMetaSample result = null;
			if(Toolkit.notEmpty(baseMetaSample)&&Toolkit.notEmpty(baseMetaSample.getName())){
				if(Toolkit.isEmpty(sampleService.getBySampleName(baseMetaSample.getName()))){
					result =  sampleService.saveEntity(baseMetaSample);
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
	
	@RequestMapping(value = "/modifySample",method = RequestMethod.POST)
	public @ResponseBody ResultVO modifySample(@RequestBody BaseMetaSample baseMetaSample,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		BaseMetaSample tempDemoData = null ; 
		try {
			if(sampleService.queryOtherNameCount(baseMetaSample)==0){
				tempDemoData = sampleService.updateEntity(baseMetaSample) ;
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
	
	@RequestMapping(value = "/deleteSample",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteSample(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(false);
		try {
			re = sampleService.deleteEntity(delVO.getPk()) ;
		} catch (Exception e) {
			re.setSuccess(false);
			logger.error("delete entity error", e);
		} 
		return re;
	}

}
