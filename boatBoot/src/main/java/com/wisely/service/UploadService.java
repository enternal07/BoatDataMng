package com.wisely.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wisely.domain.big.BigDemoMetadata;
import com.wisely.domain.big.ItemBig;
import com.wisely.domain.big.TestModel;
import com.wisely.domain.big.TestSystem;
import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.scale.ItemScalePO;
import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.domain.scale.ScaleMataPO;
import com.wisely.domain.scale.TestConditionPO;
import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.domain.small.Demometadata;
import com.wisely.domain.small.Item;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.ItemScaleService;
import com.wisely.service.scale.ScaleMataService;

@Service
public class UploadService {
	
	@Autowired
	private ExcelService  excelService;
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
	 public ResultVO getConDemo() {
		ResultVO re = new ResultVO();
		List<ItemScalePO> items = new ArrayList<ItemScalePO>();
		//先取原数据
		ScaleMataPO demoMeta = excelService.getScaleMetaFromExcle();
		ResultVO res = scaleMataService.ifExist(demoMeta);
		 if(!res.isSuccess()) {
			 //删除旧数据
			 //itemScaleService.deleteAll(oldPk);
			 items= excelService.getItemScaleData(demoMeta);
			 itemScaleService.saveAll(items);
			 re.setMessage("处理缩比样品"+demoMeta.getTestModelObjName()+"下面的"+items.size()+"条信息完毕");
		 }
		 	re.setSuccess(true);
		return re;
	}
	/*
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	public ResultVO getBigDemo() {
		ResultVO re = new ResultVO();
		List<ItemBig> items = new ArrayList<ItemBig>();
		 //先取原数据
		BigDemoMetadata demoMeta = excelService.getBigMetaFromExcle(true);
		ResultVO res = bigDemoMetadataService.ifExist(demoMeta);
		 if(!res.isSuccess()) {
			 //删除旧数据
			 //serviceItemBig.deleteAll(oldPk);
			 items= excelService.getBigItemData(demoMeta);
			 serviceItemBig.saveAll(items);
		 }
		 	re.setSuccess(true);
		return re;
	}
	/*
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	public ResultVO getSmallDemo() {
		ResultVO re = new ResultVO();
		List<Item> items = new ArrayList<Item>();
		 //先取原数据
		 Demometadata demoMeta = excelService.getSmallMetaFromExcle(true);
		 ResultVO res = service.ifExits(demoMeta);
		 if(!res.isSuccess()) {
			 //serviceItem.deleteAll(oldPK);
			 //demoMeta.setPk(oldPK);
			 items= excelService.getItemData(demoMeta);
			 serviceItem.saveAll(items);
		 }
		re.setSuccess(true);
		return re;
	}

}
