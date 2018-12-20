package com.wisely.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domain.big.ItemBig;
import com.wisely.domain.scale.ItemScalePO;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domain.small.Demometadata;
import com.wisely.domain.small.Item;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.ItemScaleService;
import com.wisely.service.scale.ScaleMataService;
import com.wisely.util.Toolkit;

@Service
public class UploadService {
	
	@Autowired
	private ExcelNewService  excelNewService;
	@Autowired
	private SmallDemoMetaDataService service;
	@Autowired
	private BigDemoMetadataService bigDemoMetadataService;
	@Autowired
	private ScaleMataService scaleMataService;
	@Autowired
	private ItemService serviceItem;
	@Autowired
	private ItemBigService serviceItemBig;
	@Autowired
	private ItemScaleService itemScaleService;
	
	/*
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	public ResultVO getSmallDemo(MultipartFile file,String pk) {
		excelNewService.load(file,"声管小样数据");
		ResultVO re = new ResultVO();
		List<Item> items = new ArrayList<Item>();
		 //先取原数据
		 //Demometadata demoMeta = excelNewService.getSmallMetaFromExcle(true);
		 Demometadata temp = service.queryDetail(pk==null?"":pk);
		 if(Toolkit.notEmpty(temp)){
			 items= excelNewService.getItemData(temp);
			 serviceItem.saveAll(items);
			 re.setMessage("处理声管小样"+temp.getName()+"下面的"+items.size()+"条信息完毕");
			 re.setSuccess(true);
		 }else{
			 re.setSuccess(false);
			 re.setMessage("声管小样不存在！");
		 }
		return re;
	}
	
	/*
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	public ResultVO getBigDemo(MultipartFile file,String pk) {
		excelNewService.load(file,"水罐大样数据");
		ResultVO re = new ResultVO();
		List<ItemBig> items = new ArrayList<ItemBig>();
		 //先取原数据
		//BigDemoMetadata demoMeta = excelNewService.getBigMetaFromExcle(true);
		BigDemoMetadata temp = bigDemoMetadataService.queryDetail(pk==null?"":pk);
		if(Toolkit.notEmpty(temp)){
			 items= excelNewService.getBigItemData(temp);
			 serviceItemBig.saveAll(items);
			 re.setMessage("处理水罐大样"+temp.getName()+"下面的"+items.size()+"条信息完毕");
			 re.setSuccess(true);
		 }else{
			 re.setSuccess(false);
			 re.setMessage("水罐大样不存在！");
		 }
		return re;
	}
	
	/*
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	 public ResultVO getConDemo(MultipartFile file,String pk) {
		ResultVO re = new ResultVO();
		List<ItemScalePO> items = new ArrayList<ItemScalePO>();
		//先取原数据
		//ScaleMataPO demoMeta = excelNewService.getScaleMetaFromExcle();
		ScaleMataPO temp = scaleMataService.queryDetail(pk==null?"":pk);
		if(Toolkit.notEmpty(temp)){
			 items= excelNewService.getItemScaleData(temp);
			 itemScaleService.saveAll(items);
			 re.setMessage("处理缩比样品"+temp.getTestModelObjName()+"下面的"+items.size()+"条信息完毕");
			 re.setSuccess(true);
		 }else{
			 re.setSuccess(false);
			 re.setMessage("缩放比模型不存在！");
		 }
		return re;
	}

}
