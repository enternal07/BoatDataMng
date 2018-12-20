package com.wisely.web.mng.data;

import java.util.List;

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
import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.domain.small.Demometadata;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaBackingService;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.service.SmallDemoMetaDataService;
import com.wisely.util.Toolkit;


/**
 * 声管小样基本信息管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/smallManager")
public class SmallManger {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SmallDemoMetaDataService service;
	@Autowired
	private BaseMetaSampleService sampleService ;
	@Autowired
	private BaseMetaBackingService backService ;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public @ResponseBody ResultVO save(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		//判断是否为空
		if(Toolkit.notEmpty(demometadata)){
			demometadata.setSmall(true);
			Demometadata dt = service.findByUniqueCondition(demometadata) ; 
			Demometadata dtname = service.findByName(demometadata.getName()); 
			if (Toolkit.isEmpty(dt)&&Toolkit.isEmpty(dtname)){//名称不存在也不重复
				String samplePk = demometadata.getSamplepk() ; 
				String backingPk = demometadata.getBakingpk(); 
				if(Toolkit.isEmpty(samplePk)){
					BaseMetaSample bms =  sampleService.getBySampleName(demometadata.getSamplename());
					samplePk = bms!=null?bms.getPk():null;
				}
				if(Toolkit.isEmpty(backingPk)){
					BaseMetaBacking bmb =  backService.getByName(demometadata.getBackingname());
					backingPk = bmb!=null?bmb.getPk():null;
				}
				demometadata.setSamplepk(samplePk);
				demometadata.setBakingpk(backingPk);
				tempDemoData = service.saveEntity(demometadata);
			}else{
				re.setMessage("名称重复或者数据已经存在！");
				re.setSuccess(false);
			}
		}else{
			re.setMessage("数据不能为空！");
			re.setSuccess(false);
		}
		re.setData(tempDemoData);
		return re;
	}
	
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public @ResponseBody ResultVO modify(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		//判断是否为空
		if(Toolkit.notEmpty(demometadata)){
			demometadata.setSmall(true);
			Demometadata temp = service.queryDetail(demometadata.getPk());
			if(Toolkit.notEmpty(temp)){
				if(temp.getName().equals(demometadata.getName())){//名称相同
					Demometadata dtname = service.existUniqueCondition(demometadata); 
					if(Toolkit.isEmpty(dtname)){
						String samplePk = demometadata.getSamplepk() ; 
						String backingPk = demometadata.getBakingpk(); 
						if(Toolkit.isEmpty(samplePk)){
							BaseMetaSample bms =  sampleService.getBySampleName(demometadata.getSamplename());
							samplePk = bms!=null?bms.getPk():null;
						}
						if(Toolkit.isEmpty(backingPk)){
								BaseMetaBacking bmb =  backService.getByName(demometadata.getBackingname());
								backingPk = bmb!=null?bmb.getPk():null;
						}
						demometadata.setSamplepk(samplePk);
						demometadata.setBakingpk(backingPk);
						tempDemoData = service.updateEntity(demometadata);
					}else{
						re.setMessage("名称或者数据已经存在");
						re.setSuccess(false);
					}
				}else{ //名称不同
						Demometadata dt = service.existName(demometadata);
						Demometadata dtname = service.existUniqueCondition(demometadata); 
						if (Toolkit.isEmpty(dt)&&Toolkit.isEmpty(dtname)){//名称不存在也不重复
							String samplePk = demometadata.getSamplepk() ; 
							String backingPk = demometadata.getBakingpk(); 
							if(Toolkit.isEmpty(samplePk)){
								BaseMetaSample bms =  sampleService.getBySampleName(demometadata.getSamplename());
								samplePk = bms!=null?bms.getPk():null;
							}
							if(Toolkit.isEmpty(backingPk)){
								BaseMetaBacking bmb =  backService.getByName(demometadata.getBackingname());
								backingPk = bmb!=null?bmb.getPk():null;
							}
							demometadata.setSamplepk(samplePk);
							demometadata.setBakingpk(backingPk);
							tempDemoData = service.updateEntity(demometadata);
						}else{
							re.setMessage("名称或者数据已经存在");
							re.setSuccess(false);
						}
					}
				}else{
					re.setMessage("数据不存在");
					re.setSuccess(false);
				}
		}else{
			re.setMessage("数据不能为空！");
			re.setSuccess(false);
		}
		re.setData(tempDemoData);
		return re;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public @ResponseBody ResultVO delete(@RequestBody DeleteVO deleteVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		if(Toolkit.notEmpty(deleteVO)){
			Demometadata dt = service.queryDetail(deleteVO.getPk());
			if(Toolkit.notEmpty(dt)){
				service.deleteAllItems(deleteVO.getPk());
				service.deleteEntity(deleteVO.getPk());
			}else{
				re.setSuccess(false);
				re.setMessage("实体不存在");
			}
		}
		return re;
	}
	
	@RequestMapping(value = "/queryByName",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByName(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		Demometadata tempDemoData = null ; 
		if(Toolkit.notEmpty(demometadata)&&Toolkit.notEmpty(demometadata.getName())){
			tempDemoData = service.findByName(demometadata.getName());
		}
		re.setData(tempDemoData);
		return re;
	}
	
	@RequestMapping(value = "/queryByCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByCondition(@RequestBody Demometadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		List<Demometadata> tempDemoData = null ; 
		if(Toolkit.notEmpty(demometadata)){
			demometadata.setSmall(true);
			tempDemoData = service.findByUniqueConditionList(demometadata);
		}
		re.setData(tempDemoData);
		return re;
	}
	
	
}
	