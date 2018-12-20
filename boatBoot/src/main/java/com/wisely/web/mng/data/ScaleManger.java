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

import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domain.scale.TestConditionPO;
import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.LayingSchemeService;
import com.wisely.service.scale.ScaleMataService;
import com.wisely.service.scale.TestConditionService;
import com.wisely.service.scale.TestModelObjService;
import com.wisely.util.Toolkit;

/**
 * 缩放比模型基本信息管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/scaleManager")
public class ScaleManger {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ScaleMataService service;
	@Autowired
	private TestConditionService  conditionService ;
	@Autowired
	private TestModelObjService testModelObjService ;
	@Autowired
	private LayingSchemeService layingSchemeService ;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public @ResponseBody ResultVO save(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		//判断是否为空
		if(Toolkit.notEmpty(demometadata)){
			ScaleMataPO dt = service.findByUniqueCondition(demometadata) ; 
			ScaleMataPO dtname = service.findByName(demometadata.getName()); 
			if (Toolkit.isEmpty(dt)&&Toolkit.isEmpty(dtname)){//名称不存在也不重复
				String conditionPk = demometadata.getTestConditionPk();
				String modelObjPk = demometadata.getTestModelObjPk(); 
				String layschemaPk = demometadata.getLayingSchemePk();
				if(Toolkit.isEmpty(conditionPk)){
					TestConditionPO bms =  conditionService.getByName(demometadata.getTestConditionName());
					conditionPk = bms!=null?bms.getPk():null;
				}
				if(Toolkit.isEmpty(modelObjPk)){
					TestModelObjPO bmb =  testModelObjService.getByName(demometadata.getTestModelObjName());
					modelObjPk = bmb!=null?bmb.getPk():null;
				}
				if(Toolkit.isEmpty(layschemaPk)){
					LayingSchemePO bmb =  layingSchemeService.getByName(demometadata.getLayingSchemeName());
					layschemaPk = bmb!=null?bmb.getPk():null;
				}
				demometadata.setTestConditionPk(conditionPk);
				demometadata.setTestModelObjPk(modelObjPk);
				demometadata.setLayingSchemePk(layschemaPk);
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
	public @ResponseBody ResultVO modify(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		//判断是否为空
		if(Toolkit.notEmpty(demometadata)){
			ScaleMataPO temp = service.queryDetail(demometadata.getPk());
			if(Toolkit.notEmpty(temp)){
				if(temp.getName().equals(demometadata.getName())){//名称相同
					ScaleMataPO dtname = service.existUniqueCondition(demometadata); 
					if(Toolkit.isEmpty(dtname)){
						String conditionPk = demometadata.getTestConditionPk();
						String modelObjPk = demometadata.getTestModelObjPk(); 
						String layschemaPk = demometadata.getLayingSchemePk();
						if(Toolkit.isEmpty(conditionPk)){
							TestConditionPO bms =  conditionService.getByName(demometadata.getTestConditionName());
							conditionPk = bms!=null?bms.getPk():null;
						}
						if(Toolkit.isEmpty(modelObjPk)){
							TestModelObjPO bmb =  testModelObjService.getByName(demometadata.getTestModelObjName());
							modelObjPk = bmb!=null?bmb.getPk():null;
						}
						if(Toolkit.isEmpty(layschemaPk)){
							LayingSchemePO bmb =  layingSchemeService.getByName(demometadata.getLayingSchemeName());
							layschemaPk = bmb!=null?bmb.getPk():null;
						}
						demometadata.setTestConditionPk(conditionPk);
						demometadata.setTestModelObjPk(modelObjPk);
						demometadata.setLayingSchemePk(layschemaPk);
						tempDemoData = service.updateEntity(demometadata);
					}else{
						re.setMessage("名称或者数据已经存在");
						re.setSuccess(false);
					}
				}else{ //名称不同
						ScaleMataPO dt = service.existName(demometadata);
						ScaleMataPO dtname = service.existUniqueCondition(demometadata); 
						if (Toolkit.isEmpty(dt)&&Toolkit.isEmpty(dtname)){//名称不存在也不重复
							String conditionPk = demometadata.getTestConditionPk();
							String modelObjPk = demometadata.getTestModelObjPk(); 
							String layschemaPk = demometadata.getLayingSchemePk();
							if(Toolkit.isEmpty(conditionPk)){
								TestConditionPO bms =  conditionService.getByName(demometadata.getTestConditionName());
								conditionPk = bms!=null?bms.getPk():null;
							}
							if(Toolkit.isEmpty(modelObjPk)){
								TestModelObjPO bmb =  testModelObjService.getByName(demometadata.getTestModelObjName());
								modelObjPk = bmb!=null?bmb.getPk():null;
							}
							if(Toolkit.isEmpty(layschemaPk)){
								LayingSchemePO bmb =  layingSchemeService.getByName(demometadata.getLayingSchemeName());
								layschemaPk = bmb!=null?bmb.getPk():null;
							}
							demometadata.setTestConditionPk(conditionPk);
							demometadata.setTestModelObjPk(modelObjPk);
							demometadata.setLayingSchemePk(layschemaPk);
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
			ScaleMataPO dt = service.queryDetail(deleteVO.getPk());
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
	public @ResponseBody ResultVO queryByName(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		ScaleMataPO tempDemoData = null ; 
		if(Toolkit.notEmpty(demometadata)&&Toolkit.notEmpty(demometadata.getName())){
			tempDemoData = service.findByName(demometadata.getName());
		}
		re.setData(tempDemoData);
		return re;
	}
	
	@RequestMapping(value = "/queryByCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByCondition(@RequestBody ScaleMataPO demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		List<ScaleMataPO> tempDemoData = null ; 
		if(Toolkit.notEmpty(demometadata)){
			tempDemoData = service.findByUniqueConditionList(demometadata);
		}
		re.setData(tempDemoData);
		return re;
	}
	
	
}
	