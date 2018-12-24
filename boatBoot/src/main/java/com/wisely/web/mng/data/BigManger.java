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

import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domain.big.TestModel;
import com.wisely.domain.big.TestSystem;
import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.small.Demometadata;
import com.wisely.domainVO.DeleteVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.service.BigDemoMetadataService;
import com.wisely.service.TestModelService;
import com.wisely.service.TestSystemService;
import com.wisely.util.Toolkit;

/**
 * 大样基本信息管理
 * @author liqz
 */

@Controller
@RequestMapping(value = "/bigManager")
public class BigManger {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BigDemoMetadataService service; 
	@Autowired
	private BaseMetaSampleService sampleService ;
	@Autowired
	private TestModelService testModelService;
	@Autowired
	private TestSystemService testSystemService;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public @ResponseBody ResultVO save(@RequestBody BigDemoMetadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BigDemoMetadata tempDemoData = null ; 
		//判断是否为空
		if(Toolkit.notEmpty(demometadata)){
			BigDemoMetadata dt = service.findByUniqueCondition(demometadata) ; 
			BigDemoMetadata dtname = service.findByName(demometadata.getName()); 
			if (Toolkit.isEmpty(dt)&&Toolkit.isEmpty(dtname)){//名称不存在也不重复
				String samplePk = demometadata.getSamplepk() ; 
				String testModelPk = demometadata.getTestModelName(); 
				String testSystemPk = demometadata.getTestSystemName();
				if(Toolkit.isEmpty(samplePk)){
					BaseMetaSample bms =  sampleService.getBySampleName(demometadata.getSampleName());
					samplePk = bms!=null?bms.getPk():null;
				}
				if(Toolkit.isEmpty(testModelPk)){
					TestModel bmb =  testModelService.getByName(demometadata.getTestModelName());
					testModelPk = bmb!=null?bmb.getPk():null;
				}
				if(Toolkit.isEmpty(testSystemPk)){
					TestSystem bmb =  testSystemService.getByName(demometadata.getTestModelName());
					testSystemPk = bmb!=null?bmb.getPk():null;
				}
				demometadata.setSamplepk(samplePk);
				demometadata.setTestModelPk(testModelPk);
				demometadata.setTestSystemPk(testSystemPk);
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
	public @ResponseBody ResultVO modify(@RequestBody BigDemoMetadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BigDemoMetadata tempDemoData = null ; 
		//判断是否为空
		if(Toolkit.notEmpty(demometadata)){
			BigDemoMetadata temp = service.queryDetail(demometadata.getPk());
			if(Toolkit.notEmpty(temp)){
				if(temp.getName().equals(demometadata.getName())){//名称相同
					BigDemoMetadata dtname = service.existUniqueCondition(demometadata); 
					if(Toolkit.isEmpty(dtname)){
						String samplePk = demometadata.getSamplepk() ; 
						String testModelPk = demometadata.getTestModelName(); 
						String testSystemPk = demometadata.getTestSystemName();
						if(Toolkit.isEmpty(samplePk)){
							BaseMetaSample bms =  sampleService.getBySampleName(demometadata.getSampleName());
							samplePk = bms!=null?bms.getPk():null;
						}
						if(Toolkit.isEmpty(testModelPk)){
							TestModel bmb =  testModelService.getByName(demometadata.getTestModelName());
							testModelPk = bmb!=null?bmb.getPk():null;
						}
						if(Toolkit.isEmpty(testSystemPk)){
							TestSystem bmb =  testSystemService.getByName(demometadata.getTestModelName());
							testSystemPk = bmb!=null?bmb.getPk():null;
						}
						demometadata.setSamplepk(samplePk);
						demometadata.setTestModelPk(testModelPk);
						demometadata.setTestSystemPk(testSystemPk);
						tempDemoData = service.updateEntity(demometadata);
					}else{
						re.setMessage("名称或者数据已经存在");
						re.setSuccess(false);
					}
				}else{ //名称不同
						BigDemoMetadata dt = service.existName(demometadata);
						BigDemoMetadata dtname = service.existUniqueCondition(demometadata); 
						if (Toolkit.isEmpty(dt)&&Toolkit.isEmpty(dtname)){//名称不存在也不重复
							String samplePk = demometadata.getSamplepk() ; 
							String testModelPk = demometadata.getTestModelName(); 
							String testSystemPk = demometadata.getTestSystemName();
							if(Toolkit.isEmpty(samplePk)){
								BaseMetaSample bms =  sampleService.getBySampleName(demometadata.getSampleName());
								samplePk = bms!=null?bms.getPk():null;
							}
							if(Toolkit.isEmpty(testModelPk)){
								TestModel bmb =  testModelService.getByName(demometadata.getTestModelName());
								testModelPk = bmb!=null?bmb.getPk():null;
							}
							if(Toolkit.isEmpty(testSystemPk)){
								TestSystem bmb =  testSystemService.getByName(demometadata.getTestModelName());
								testSystemPk = bmb!=null?bmb.getPk():null;
							}
							demometadata.setSamplepk(samplePk);
							demometadata.setTestModelPk(testModelPk);
							demometadata.setTestSystemPk(testSystemPk);
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
			BigDemoMetadata dt = service.queryDetail(deleteVO.getPk());
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
	
	@RequestMapping(value = "/deleteAll",method = RequestMethod.POST)
	public @ResponseBody ResultVO deleteAll(@RequestBody DeleteVO delVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		StringBuffer sb = new StringBuffer();
		if(Toolkit.notEmpty(delVO) && Toolkit.notEmpty(delVO.getPks())){
			for (String pk : delVO.getPks()) {
				BigDemoMetadata dt = service.queryDetail(pk);
				if(Toolkit.notEmpty(dt)){
					service.deleteAllItems(pk);
					service.deleteEntity(pk);
				}else{
					sb.append("["+pk+"]实体不存在!");
				}
			}
		}else{
			re.setSuccess(false);
			re.setMessage("数据不能为空！");
		}
		re.setMessage(sb.toString());
		return re;
	}
	
	
	@RequestMapping(value = "/queryByName",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByName(@RequestBody BigDemoMetadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		BigDemoMetadata tempDemoData = null ; 
		if(Toolkit.notEmpty(demometadata)&&Toolkit.notEmpty(demometadata.getName())){
			tempDemoData = service.findByName(demometadata.getName());
		}
		re.setData(tempDemoData);
		return re;
	}
	
	@RequestMapping(value = "/queryByCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO queryByCondition(@RequestBody BigDemoMetadata demometadata,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		List<BigDemoMetadata> tempDemoData = null ;
		if(Toolkit.notEmpty(demometadata)){
			tempDemoData = service.findByUniqueConditionList(demometadata);
		}
		re.setData(tempDemoData);
		return re;
	}
	
	
}