package com.wisely.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
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
import com.wisely.domainVO.ItemBaseVO;
import com.wisely.domainVO.ItemBigVO;
import com.wisely.domainVO.ItemScaleVO;
import com.wisely.domainVO.QueryBigVO;
import com.wisely.domainVO.QueryVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.domainVO.SacleQueryVO;
import com.wisely.service.BaseMetaBackingService;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.service.BigDemoMetadataService;
import com.wisely.service.ExcelService;
import com.wisely.service.ItemBigService;
import com.wisely.service.ItemService;
import com.wisely.service.SmallDemoMetaDataService;
import com.wisely.service.TestModelService;
import com.wisely.service.TestSystemService;
import com.wisely.service.scale.ItemScaleService;
import com.wisely.service.scale.LayingSchemeService;
import com.wisely.service.scale.ScaleMataService;
import com.wisely.service.scale.TestConditionService;
import com.wisely.service.scale.TestModelObjService;
import com.wisely.util.Toolkit;

import until.constant.Constants;
/**
 * 小样模型
 * 大样模型
 * 缩比模型
 * @author dingqi
 */

@RestController
@RequestMapping(value = "/excelUpload")
public class ExcelController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SmallDemoMetaDataService service;
	
	@Autowired
	private ItemService serviceItem;
	
	@Autowired
	private ItemBigService serviceItemBig;
	
	@Autowired
	private ExcelService  excelService;
	
	@Autowired
	private BaseMetaBackingService  baseMetaBackingService;
	
	@Autowired
	private BaseMetaSampleService  baseMetaSampleService;
	
	@Autowired
	private BigDemoMetadataService bigDemoMetadataService;
	
	@Autowired
	private TestModelService   testmodelService;
	@Autowired
	private TestSystemService testSysService;
	
	@Autowired
	private ScaleMataService scaleMataService;
	
	@Autowired
	private TestModelObjService testModelObjService;
	
	@Autowired
	private TestConditionService testConditionService;
	
	@Autowired
	private LayingSchemeService layingSchemeService;
	
	@Autowired
	private ItemScaleService itemScaleService;
	
	  private Sheet sheet;
	  private List<List<String>> listData;
	  private List<Map<String,String>> mapData;
	  private boolean flag;
	  
	
	@RequestMapping(value="uploadExcle",method = RequestMethod.POST)
	public  ResultVO UploadExcle(MultipartFile file, String catalog,  HttpServletRequest request) throws IOException{
		ResultVO res = new ResultVO(false,"",null);
		if(file==null) {
			res.setMessage("没有上传文件，请选着文件上传");
			return res;
		}
		//小样数据加载，一共8个属性，根据样品名称、温度、压力、背衬四个固定，其他4个属性（频率、反射系数、投射系数、吸声系数）变动
		if(Constants.SMALLDEMO.equals(catalog)) {
			excelService.load(file,"声管小样数据");
			res = this.getSmallDemo();
			res.setSuccess(true);
		}else if(Constants.BIGDEMO.equals(catalog)) {
			excelService.load(file,"水罐大样数据");
			res = this.getBigDemo();
			res.setSuccess(true);
		}else if(Constants.CONDEMO.equals(catalog)){
			excelService.load(file,"缩比模型数据");
			res = this.getConDemo();
			res.setSuccess(true);
		}else {
			res.setMessage("不支持的模型数据");
		}
		return res;
		
	}
	
	/*
	 * {
			"samplename":"阿波罗02",
			"backgroundtype":"30mm钢02",
			"temparture":15,
			"press":1,
			"rateMin":10,
			"rateMax":200
		}
	 */
	@RequestMapping(value="/downloadSmall",method = RequestMethod.POST)
    public void downloadSmall(
    		@RequestBody QueryVO queryVO,
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException{
       
		/*QueryVO queryVO = new QueryVO();
		queryVO.setBackgroundtype("30mm钢02");
		queryVO.setPress(1);
		queryVO.setRateMax(200);
		queryVO.setRateMin(10);
		queryVO.setSamplename("阿波罗02");
		queryVO.setTemparture(15.0f);*/
        List<ItemBaseVO> items = serviceItem.findByQueryCondtionOld(queryVO);
        if(Toolkit.notEmpty(items)&&items.size()>0){
        	BaseMetaSample bms = baseMetaSampleService.getBySampleName(queryVO.getSamplename());
        	BaseMetaBacking backing = baseMetaBackingService.getByName(queryVO.getBackingname());
            excelService.downloadSmall(response, queryVO, bms, backing, items);
        }
    }
	
	/**
	 * {
			"samplename": "阿波罗",
			"testModelName": "双层局域实尺度模型",
			"testSystemName": "时间反转",
			"temparture": 15.0,
			"press": 1,
			"rateMin": 0,
			"rateMax": 300
		}
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadBig",method = RequestMethod.POST)
    public void downloadBig(
    		@RequestBody QueryBigVO queryBigVO,
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException{
		
	/*	QueryBigVO queryBigVO = new QueryBigVO();
		queryBigVO.setSamplename("阿波罗");
		queryBigVO.setTestModelName("双层局域实尺度模型");
		queryBigVO.setTestSystemName("时间反转");
		queryBigVO.setPress(1);
		queryBigVO.setTemparture(15.0f);
		queryBigVO.setRateMin(0);
		queryBigVO.setRateMax(300);*/
		
        List<ItemBigVO> items = serviceItemBig.getItemBigList(queryBigVO);
        if(Toolkit.notEmpty(items)&&items.size()>0){
        	BaseMetaSample bms = baseMetaSampleService.getBySampleName(queryBigVO.getSamplename());
        	TestModel tm = testmodelService.getByName(queryBigVO.getTestModelName());
        	TestSystem ts = testSysService.getByName(queryBigVO.getTestSystemName());
            excelService.downloadBig(response, queryBigVO, bms, tm,ts,items);
        }
    }
	/**
	 * {
			"layingSchemeName": "文字文字",
			"testConditionName": "201708杭州",
			"testModelObjName": "中尺度模型",
			"rateMin": 0,
			"rateMax": 1000
		}
	 * @param sacleQueryVO
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/downloadScale",method = RequestMethod.POST)
    public void downloadScale(
    		@RequestBody SacleQueryVO sacleQueryVO,
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException{
/*		SacleQueryVO sacleQueryVO = new SacleQueryVO();
		sacleQueryVO.setTestModelObjName("中尺度模型");
		sacleQueryVO.setTestConditionName("201708杭州");
		sacleQueryVO.setLayingSchemeName("文字文字");
		sacleQueryVO.setRateMin(0);
		sacleQueryVO.setRateMax(1000);*/
		List<ItemScaleVO>  items = (List<ItemScaleVO> )itemScaleService.getItemScaleList(sacleQueryVO);
        if(Toolkit.notEmpty(items)&&items.size()>0){
        	TestModelObjPO tmobj = testModelObjService.getByName(sacleQueryVO.getTestModelObjName());
        	TestConditionPO tc = testConditionService.getByName(sacleQueryVO.getTestConditionName());
        	LayingSchemePO ls = layingSchemeService.getByName(sacleQueryVO.getLayingSchemeName());
            excelService.downloadScale(response, sacleQueryVO, tmobj, tc,ls,items);
        }
    }
	
	/*
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	@Transactional
	 private ResultVO getConDemo() {
		ResultVO re = new ResultVO();
		List<ItemScalePO> items = new ArrayList<ItemScalePO>();
		 //先取原数据
		ScaleMataPO demoMeta = excelService.getScaleMetaFromExcle();
		ResultVO res = scaleMataService.ifExist(demoMeta);
		 if(!res.isSuccess()) {
			 TestConditionPO testConditionPO = testConditionService.saveEntity(demoMeta.getTestConditionPO());
			 TestModelObjPO testModelObjPO = testModelObjService.saveEntity(demoMeta.getTestModelObjPO());
			 LayingSchemePO layingSchemePO = layingSchemeService.saveEntity(demoMeta.getLayingSchemePO());
				demoMeta.setLayingSchemePk(layingSchemePO.getPk());
				demoMeta.setTestModelObjPk(testModelObjPO.getPk());
				demoMeta.setTestConditionPk(testConditionPO.getPk());
			    demoMeta = scaleMataService.saveEntity(demoMeta);
				items= excelService.getItemScaleData(demoMeta);
				itemScaleService.saveAll(items);
			re.setMessage("处理缩比样品"+demoMeta.getTestModelObjName()+"下面的"+items.size()+"条信息完毕");
		 }else {
			 String oldPk = (String) res.getData();
			 demoMeta.setPk(oldPk);
			 //删除旧数据
			 itemScaleService.deleteAll(oldPk);
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
	@Transactional
	 private ResultVO getBigDemo() {
		ResultVO re = new ResultVO();
		List<ItemBig> items = new ArrayList<ItemBig>();
		 //先取原数据
		BigDemoMetadata demoMeta = excelService.getBigMetaFromExcle(true);
		ResultVO res = bigDemoMetadataService.ifExist(demoMeta);
		 if(!res.isSuccess()) {
			   BaseMetaSample smamp = baseMetaSampleService.saveEntity(demoMeta.getSample());
				TestModel testModle = testmodelService.saveEntity(demoMeta.getTestModel());
				TestSystem testStye = testSysService.saveEntity(demoMeta.getTestSystem());
				demoMeta.setSamplepk(smamp.getPk());
				demoMeta.setTestModelPk(testModle.getPk());
				demoMeta.setTestSystemPk(testStye.getPk());
			    demoMeta = bigDemoMetadataService.saveEntity(demoMeta);
				items= excelService.getBigItemData(demoMeta);
			    serviceItemBig.saveAll(items);
			re.setMessage("处理样品"+demoMeta.getSampleName()+"下面的"+items.size()+"条信息完毕");
		 }else {
			 String oldPk = (String) res.getData();
			 demoMeta.setPk(oldPk);
			 //删除旧数据
			 serviceItemBig.deleteAll(oldPk);
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
	@Transactional
	 private ResultVO getSmallDemo() {
		ResultVO re = new ResultVO();
		List<Item> items = new ArrayList<Item>();
		 //先取原数据
		 Demometadata demoMeta = excelService.getSmallMetaFromExcle(true);
		 ResultVO res = service.ifExits(demoMeta);
		 if(!res.isSuccess()) {
			demoMeta = service.saveEntity(demoMeta);
			BaseMetaSample sample = baseMetaSampleService.saveEntity(demoMeta.getSample());
			BaseMetaBacking backing = demoMeta.getBacking();
			backing.setSamplePk(sample.getPk());
			backing = baseMetaBackingService.saveEntity(backing);
			demoMeta.setSamplepk(sample.getPk());
			demoMeta.setBakingpk(backing.getPk());
			items= excelService.getItemData(demoMeta);
			serviceItem.saveAll(items);
			demoMeta = service.saveEntity(demoMeta);
			 re.setMessage("处理样品"+demoMeta.getSamplename()+"下面的"+items.size()+"条信息完毕");
		 }else {
			 //先删除旧数据，再存入新数据
			 String oldPK = (String) res.getData();
			 serviceItem.deleteAll(oldPK);
			 demoMeta.setPk(oldPK);
			 items= excelService.getItemData(demoMeta);
			 serviceItem.saveAll(items);
		 }
		re.setSuccess(true);
		return re;
	}
	
	
    
/**
 * 获取sheet数据
 */
    private void getSheetData() {
//      	ResultVO  res = new ResultVO();
        //listData = new ArrayList<List<String>>();
        //mapData = new ArrayList<Map<String, String>>();    
        //columnHeaderList = new ArrayList<String>();
       
       // getItemData(numOfRows);
//        return res;
    }
   
   
    public List<Item> getItemData(Demometadata demoMeta) {
    	 List<Item> pos = new  ArrayList<Item>();
    	 int numOfRows = sheet.getLastRowNum() + 1;
    	 for (int i = 15; i < numOfRows; i++) {
             Row row = sheet.getRow(i);
             Map<String, String> map = new HashMap<String, String>();
             List<String> list = new ArrayList<String>();
             if (row != null) {
             	Item item = new Item();
                 for (int j = 0; j < row.getLastCellNum(); j++) {
                     Cell cell = row.getCell(j);
                     	String value = getCellValue(cell);
                     	//快速排序ֵ
                     	//偶数2.4.6.8.10.12.1
                     	if(j%2==0){
                     	  if(j==0) {
                     		  item.setRate(Integer.parseInt(value));
                     	  }else {
                     		item.setTransmission(Float.parseFloat(value));
                     	  }
                     	}else {
                     		if(j<3){
                     			  item.setRefect(Float.parseFloat(value));
                     		}else{
                     			item.setBondacust(Float.parseFloat(value));
                     		}
                     	}
                     list.add(this.getCellValue(cell));
                 }
                 item.setSmallPO(demoMeta);
                 pos.add(item);
             }
         }
		return pos;
    }
    
    public String getCellData(int row, int col){
        if(row<=0 || col<=0){
            return null;
        }
        if(!flag){
            this.getSheetData();
        }        
        if(listData.size()>=row && listData.get(row-1).size()>=col){
            return listData.get(row-1).get(col-1);
        }else{
            return null;
        }
    }
    
    public String getCellData11(int row, String headerName){
        if(row<=0){
            return null;
        }
        if(!flag){
            this.getSheetData();
        }        
        if(mapData.size()>=row && mapData.get(row-1).containsKey(headerName)){
            return mapData.get(row-1).get(headerName);
        }else{
            return null;
        }
    }

    public void SavePOs( List<Item>  pos){
    	serviceItem.saveAll(pos);
    }
	/**
	 * 获取小样数据的元数据信息
	 * 1——13行数据
	 * @param isSmall
	 * @return
	 */
	private Demometadata getSmallMetaFromExcle(boolean isSmall) {
		//获取前13行的数据
        	Demometadata smallDemoMetaData= new Demometadata();
        	//前7行是样品
        	BaseMetaSample sample =  new BaseMetaSample();
        	//8.9.10.11行是背衬，	剩余两行是温度和压力
        	BaseMetaBacking backing = new BaseMetaBacking();
        		   Row row1 = sheet.getRow(0);
        		   Cell cell1 = row1.getCell(1);
        		   smallDemoMetaData.setSamplename(getCellValue(cell1));
        		   Row row2 = sheet.getRow(1);
        		   Cell cell2 = row2.getCell(1);
        		   
        		   Row row3 = sheet.getRow(2);
        		   Cell cell3 = row3.getCell(1);
        		   smallDemoMetaData.setTemparture(Float.parseFloat(getCellValue(cell3)));
        		   Row row4 = sheet.getRow(3);
        		   Cell cell4 = row4.getCell(1);
        		   smallDemoMetaData.setPress(Integer.parseInt(getCellValue(cell4)));
        		   System.out.println("元数据："+smallDemoMetaData.getSamplename()+smallDemoMetaData.getTemparture()+smallDemoMetaData.getPress()+smallDemoMetaData.getBackingname());
			   smallDemoMetaData.setSmall(isSmall);
			   
			   smallDemoMetaData.setBacking(backing);
			   smallDemoMetaData.setSample(sample);
        		   return smallDemoMetaData;
	}
	
	private String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    if(cellValue.startsWith("1-B")) {
                    	 cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }
	

}