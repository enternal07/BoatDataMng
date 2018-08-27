package com.wisely.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisely.domain.Item;
import com.wisely.domain.ItemBig;
import com.wisely.domain.ItemContraction;
import com.wisely.domain.TestModel;
import com.wisely.domain.TestSystem;
import com.wisely.domain.BaseMetaBacking;
import com.wisely.domain.BaseMetaSample;
import com.wisely.domain.BigDemoMetadata;
import com.wisely.domain.ContractionMetadata;
import com.wisely.domain.Demometadata;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaBackingService;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.service.BigDemoMetadataService;
import com.wisely.service.ContractionMetadataService;
import com.wisely.service.ExcelService;
import com.wisely.service.ItemBigService;
import com.wisely.service.ItemContractionService;
import com.wisely.service.ItemService;
import com.wisely.service.SmallDemoMetaDataService;
import com.wisely.service.TestModelService;
import com.wisely.service.TestSystemService;

import until.constant.Constants;
/**
 * 小样模型
 * 大样模型
 * 
 * @author dingqi
 *
 */

@Controller
@RequestMapping(value = "/excelUpload")
public class ExcelController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SmallDemoMetaDataService service;
	@Autowired
	private ContractionMetadataService serviceConMeta;
	
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
	private ItemContractionService serviceItemCon;
	
	@Autowired
	private BigDemoMetadataService bigDemoMetadataService;
	
	@Autowired
	private TestModelService   testmodelService;
	@Autowired
	private TestSystemService testSysService;
	
	  private Sheet sheet;
	  private List<List<String>> listData;
	  private List<Map<String,String>> mapData;
	  private boolean flag;
	  
	
	@RequestMapping(value="uploadExcle",method = RequestMethod.POST)
	public @ResponseBody ResultVO UploadExcle(MultipartFile file, String catalog,  HttpServletRequest request) throws IOException{
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
	 * 判断元数据；
	 * 存储没有存储过的数据
	 */
	@Transactional
	 private ResultVO getConDemo() {
		ResultVO re = new ResultVO();
		List<ItemContraction> items = new ArrayList<ItemContraction>();
		 //先取原数据
		 ContractionMetadata demoMeta = getConDemoFromExcle();
		 if(!serviceConMeta.ifExits(demoMeta)) {
			
			 items= getItemConData(demoMeta);
			demoMeta = serviceConMeta.saveEntity(demoMeta);
			serviceItemCon.saveAll(items);
			 re.setMessage("处理样品"+demoMeta.getSamplename()+"下面的"+items.size()+"条信息完毕");
		 }else {
			 re.setMessage(demoMeta.toString()+"的数据已经导入，请勿重新导入");
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
		 if(!bigDemoMetadataService.ifExist(demoMeta)) {
			    baseMetaSampleService.saveEntity(demoMeta.getSample());
				testmodelService.saveEntity(demoMeta.getTestModel());
				testSysService.saveEntity(demoMeta.getTestSystem());
			    demoMeta = bigDemoMetadataService.saveEntity(demoMeta);
				items= excelService.getBigItemData(demoMeta);
			    serviceItemBig.saveAll(items);
			re.setMessage("处理样品"+demoMeta.getSamplename()+"下面的"+items.size()+"条信息完毕");
		 }else {
			 re.setMessage(demoMeta.toString()+"的数据已经导入，请勿重复导入");
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
		 if(!service.ifExits(demoMeta)) {
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
			 re.setMessage(demoMeta.toString()+"的数据已经导入，请勿重复导入");
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
    public List<ItemContraction> getItemConData(ContractionMetadata demoMeta) {
   	 List<ItemContraction> pos = new  ArrayList<ItemContraction>();
   	 int numOfRows = sheet.getLastRowNum() + 1;
   	 for (int i = 4; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            List<String> list = new ArrayList<String>();
            if (row != null) {
            	ItemContraction item = new ItemContraction();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    	String value = getCellValue(cell);
                    	//快速排序ֵ
                    	//偶数2.4.6.8.10.12.1
                    	if(j%2==0){
                    	  if(j==0) {
                    		  item.setRate(Integer.parseInt(value));
                    	  }else {
                    		  item.setRadiation(Integer.parseInt(value));
                    	  }
                    	}else {
                    		if(j<3){
                    			item.setTarget(Float.parseFloat(value));
                    		}else{
                    			item.setRadiationlose(Float.parseFloat(value));;
                    		}
                    	}
                    list.add(this.getCellValue(cell));
                }
                item.setSamplPO(demoMeta);
                pos.add(item);
            }
        }
		return pos;
   }
    public List<ItemBig> getItemDataBig(Demometadata demoMeta) {
   	 List<ItemBig> pos = new  ArrayList<ItemBig>();
   	 int numOfRows = sheet.getLastRowNum() + 1;
   	 for (int i = 5; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            List<String> list = new ArrayList<String>();
            if (row != null) {
            	ItemBig item = new ItemBig();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    	String value = getCellValue(cell);
                    	//快速排序ֵ
                    	//偶数0.2.4.6
                    	if(j%2==0){
                    	  if(j<2) {
                    		  item.setRate(Integer.parseInt(value));
                    	  }else if(j>2){
                    		 if(j>4) {
                    			 item.setEchoes(Float.parseFloat(value)); 
                    		 }else {
                    			 item.setRadiationlose(Float.parseFloat(value));
                    		 }
                    	  }else {
                    		  item.setTransmission(Float.parseFloat(value));
                    	  }
                    	}else {//奇数：1.3.5
                    		if(j<3){
                    			  item.setRefect(Float.parseFloat(value));
                    		}else if(j>3) {
                    			item.setRadiation(Integer.parseInt(value));
                    		}else {
                    			item.setBondacust(Float.parseFloat(value));
                    		}
                    	}
                    list.add(this.getCellValue(cell));
                }
                item.setSamplPO(demoMeta);
                pos.add(item);
            }
        }
		return pos;
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
        		   System.out.println("元数据："+smallDemoMetaData.getSamplename()+smallDemoMetaData.getTemparture()+smallDemoMetaData.getPress()+smallDemoMetaData.getBackgroundtype());
			   smallDemoMetaData.setSmall(isSmall);
			   
			   smallDemoMetaData.setBacking(backing);
			   smallDemoMetaData.setSample(sample);
        		   return smallDemoMetaData;
	}
	private ContractionMetadata getConDemoFromExcle() {
		//获取前四行的数据
        	ContractionMetadata smallDemoMetaData= new ContractionMetadata();
        		   Row row1 = sheet.getRow(0);
        		   Cell cell1 = row1.getCell(1);
        		   smallDemoMetaData.setSamplename(getCellValue(cell1));
        		   Row row2 = sheet.getRow(1);
        		   Cell cell2 = row2.getCell(1);
        		   smallDemoMetaData.setBackgroundtype(getCellValue(cell2));
        		   Row row3 = sheet.getRow(2);
        		   Cell cell3 = row3.getCell(1);
        		   smallDemoMetaData.setTesttime(getCellValue(cell3));
        		   
        		   System.out.println("元数据："+smallDemoMetaData.getSamplename()+smallDemoMetaData.getBackgroundtype()+smallDemoMetaData.getTesttime());
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