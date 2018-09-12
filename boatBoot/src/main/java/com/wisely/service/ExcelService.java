package com.wisely.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

@Service
public class ExcelService {

	
	  private Workbook workBook;    
	  private Sheet sheet;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
     * 解析文件
     */
    public void load(MultipartFile file,String sheetName) {
        InputStream inStream = null;
		try {
         	inStream = file.getInputStream();
            workBook = WorkbookFactory.create(inStream);
            sheet = workBook.getSheet(sheetName);            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(inStream!=null){
                    inStream.close();
                }                
            } catch (IOException e) {                
                e.printStackTrace();
            }
        }
    }
/**
 * ecxle 对应15 但是模型里面是从开始，所以需要从14开始
 * @param demoMeta
 * @return
 */
    public List<Item> getItemData(Demometadata demoMeta) {
   	 List<Item> pos = new  ArrayList<Item>();
   	 int numOfRows = sheet.getLastRowNum() ;
   	 for (int i = 14; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
            	Item item = new Item();
                for (int j = 0; j < 4; j++) {
                    Cell cell = row.getCell(j);
                    	String value = getCellValue(cell);
                    	if("".equals(value)) {
                    		value = "0";
                    	}
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
                }
                item.setSmallPO(demoMeta);
                pos.add(item);
            }
        }
		return pos;
   }
    /**
	 * 获取大样数据的元数据信息
	 * 1——18行数据
	 * @param isSmall
	 * @return
	 */
	public BigDemoMetadata getBigMetaFromExcle(boolean isSmall) {
		// 获取前18行的数据
		BigDemoMetadata bigDemoMetaData = new BigDemoMetadata();
		// 前7行是样品
		BaseMetaSample sample = this.getSmapleFormExle();
		sample.setSmall(false);
		// 8.9.10.11，12，13，14行是试验模型，
		TestModel testModel = this.getBigTestModel();
		// 15，16 是测试模型
		TestSystem testSystem = this.getSystem();
		// 剩余两行是温度和压力

		Row row16 = sheet.getRow(16);
		Cell cell16 = row16.getCell(1);
		bigDemoMetaData.setPress(Integer.parseInt(getCellValue(cell16)));

		Row row17 = sheet.getRow(17);
		Cell cell17 = row17.getCell(1);
		bigDemoMetaData.setTemparture(Float.parseFloat(getCellValue(cell17)));
		bigDemoMetaData.setSample(sample);
		bigDemoMetaData.setSampleName(sample.getName());
		bigDemoMetaData.setTestModel(testModel);
		bigDemoMetaData.setTestmodelName(testModel.getName());
		bigDemoMetaData.setTestSystem(testSystem);
		bigDemoMetaData.setTestsystemName(testSystem.getName());
		return bigDemoMetaData;
	}
    /**系统测试
     * 14~15行
     * @return
     */
    private TestSystem getSystem() {
    	TestSystem TestSystem = new TestSystem();
	       Row row8 = sheet.getRow(14);
		   Cell cell8 = row8.getCell(1);
		   TestSystem.setName(getCellValue(cell8));
		   Row row9 = sheet.getRow(15);
		   Cell cell9 = row9.getCell(1);
		   TestSystem.setDescribe(getCellValue(cell9));
		return TestSystem;
	}
	/**
	 * 试验模型
     * 8—～14行
     * @return
     */
    public TestModel getBigTestModel() {
          TestModel testModel = new TestModel();
	       Row row8 = sheet.getRow(7);
		   Cell cell8 = row8.getCell(1);
		   testModel.setName(getCellValue(cell8));
		   Row row9 = sheet.getRow(8);
		   Cell cell9 = row9.getCell(1);
		   testModel.setSize(getCellValue(cell9));
		   Row row10 = sheet.getRow(9);
		   Cell cell10 = row10.getCell(1);
		   testModel.setDoubleShellSpacing(getCellValue(cell10));
		   Row row11 = sheet.getRow(10);
		   Cell cell11 = row11.getCell(1);
		   testModel.setInnerShellThickness(getCellValue(cell11));
		   Row row12 = sheet.getRow(11);
		   Cell cell12 = row12.getCell(1);
		   testModel.setShellThickness(getCellValue(cell12));
		   Row row13= sheet.getRow(12);
		   Cell cell13 = row13.getCell(1);
		   testModel.setInnerShellBackend(getCellValue(cell13));
		   Row row14 = sheet.getRow(13);
		   Cell cell14 = row14.getCell(1);
		   testModel.setOther(getCellValue(cell14));
		   return testModel;
	}
	
	/**
	 * 获取小样数据的元数据信息
	 * 1——13行数据
	 * @param isSmall
	 * @return
	 */
    public Demometadata getSmallMetaFromExcle(boolean isSmall) {
		//获取前13行的数据
        	Demometadata smallDemoMetaData= new Demometadata();
        	//前7行是样品
        	BaseMetaSample sample =  this.getSmapleFormExle();
        	
        	//8.9.10.11行是背衬，	剩余两行是温度和压力
        	BaseMetaBacking backing = this.getSmallBacking();		   
        		   Row row12 = sheet.getRow(11);
        		   Cell cell12 = row12.getCell(1);
        		   smallDemoMetaData.setTemparture(Float.parseFloat(getCellValue(cell12)));
        		   Row row13 = sheet.getRow(12);
        		   Cell cell13 = row13.getCell(1);
        		   smallDemoMetaData.setPress(Integer.parseInt(getCellValue(cell13)));
			   smallDemoMetaData.setSmall(isSmall);
			   smallDemoMetaData.setSamplename(sample.getName());
			   smallDemoMetaData.setBackgroundtype(backing.getName());
			   smallDemoMetaData.setBacking(backing);
			   smallDemoMetaData.setSample(sample);
        		   return smallDemoMetaData;
	}
	public BaseMetaBacking getSmallBacking() {
	 	BaseMetaBacking backing = new BaseMetaBacking();
	       Row row8 = sheet.getRow(7);
		   Cell cell8 = row8.getCell(1);
		   backing.setName(getCellValue(cell8));
		   Row row9 = sheet.getRow(8);
		   Cell cell9= row9.getCell(1);
		   backing.setFrontMedium(getCellValue(cell9));
		   Row row10 = sheet.getRow(9);
		   Cell cell10 = row10.getCell(1);
		   backing.setEndMedium(getCellValue(cell10));
		   Row row11 = sheet.getRow(10);
		   Cell cell11 = row11.getCell(1);
		   backing.setOther(getCellValue(cell11));
		return backing;
	}
	/**
	 * 前7行
	 * @return
	 */
	private BaseMetaSample getSmapleFormExle() {
		
	 	BaseMetaSample sample =  new BaseMetaSample();
	 	   Row row1 = sheet.getRow(0);
		   Cell cell1 = row1.getCell(1);
		   sample.setName(getCellValue(cell1));
		   Row row2 = sheet.getRow(1);
		   Cell cell2 = row2.getCell(1);
		   sample.setDensity(getCellValue(cell2));
		   Row row3 = sheet.getRow(2);
		   Cell cell3 = row3.getCell(1);
		   sample.setFlexibleModel(getCellValue(cell3));
		   Row row4 = sheet.getRow(3);
		   Cell cell4 = row4.getCell(1);
		   sample.setPoissonRatio(getCellValue(cell4));
		   Row row5 = sheet.getRow(4);
		   Cell cell5 = row5.getCell(1);
		   sample.setSoundSpeed(getCellValue(cell5));
		   Row row6 = sheet.getRow(5);
		   Cell cell6 = row6.getCell(1);
		   sample.setThickness(getCellValue(cell6));
		   Row row7 = sheet.getRow(6);
		   Cell cell7 = row7.getCell(1);
		   sample.setOther(getCellValue(cell7));
		return sample;
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
                   // cellValue = String.valueOf(cell.getCellFormula());
                    //if(cellValue.startsWith("1-B") || cellValue.startsWith("B21")) {
                    	 cellValue = String.valueOf(cell.getNumericCellValue());
                    //}
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
	/**
	 *前19行都是元数据，第20行开始基础数据，从0开始计算，所以下面是19
	 * @param demoMeta
	 * @return
	 */
	public List<ItemBig> getBigItemData(BigDemoMetadata demoMeta) {
		 List<ItemBig> pos = new  ArrayList<ItemBig>();
	   	 int numOfRows = sheet.getLastRowNum() ;
	   	 System.out.println("带持久化的大样数据有"+numOfRows);
	   	 for (int i = 19; i < numOfRows; i++) {
	            Row row = sheet.getRow(i);
	            if (row != null) {
	            	ItemBig item = new ItemBig();
	                for (int j = 0; j < 7; j++) {
	                    Cell cell = row.getCell(j);
	                    	String value = getCellValue(cell);
	                    	if("".equals(value)) {
	                    		value = "0";
	                    	}
	                    	//快速排序ֵ
	                    	//偶数0、2.4.6.
	                    	if(j%2==0){
	                    	  if(j==0) {
	                    		  item.setRate(Integer.parseInt(value));
	                    	  }else if(j==2){
	                    		item.setTransmission(Float.parseFloat(value));
	                    	  }else if(j==6){
	                    		  item.setRadiationlose(Float.parseFloat(value));;
	                    	  }else {
	                    		  item.setEchoes(Float.parseFloat(value));
	                    	  }
	                    	}else {//1、3、5
	                    		if(j<3){
	                    			  item.setRefect(Float.parseFloat(value));
	                    		}else if(j==3){
	                    			item.setBondacust(Float.parseFloat(value));
	                    		}else {
	                    			item.setRadiation(Integer.parseInt(value));
	                    		}
	                    	}
	                }
	                item.setBigDemoMetadata(demoMeta);
	                pos.add(item);
	            }
	        }
			return pos;
	}
	
	/**
	 * 获取大样数据的元数据信息
	 * 1——19行数据
	 * 1-6行  试验模型对象
	 * 7-13行 试验情况模型对象
	 * 14-19行 敷设方案模型对象
	 * @param isSmall
	 * @return
	 */
	public ScaleMataPO getScaleMetaFromExcle() {
		// 存储前19行的数据
		ScaleMataPO scaleMetaData = new ScaleMataPO();
		// 前6行是试验对象模型
		TestModelObjPO testModelObj = this.getTestModelObjPOFromExle();
		// 7-13行行是试验情况模型，
		TestConditionPO testConditionPO = this.getTestCondition();
		// 14-19 是敷设方案
		LayingSchemePO layingSchemePO = this.getLayingSchemePO();
		
		scaleMetaData.setLayingSchemeName(layingSchemePO.getName());
		scaleMetaData.setTestConditionName(testConditionPO.getName());
		scaleMetaData.setTestModelObjName(testModelObj.getName());
		scaleMetaData.setLayingSchemePO(layingSchemePO);
		scaleMetaData.setTestModelObjPO(testModelObj);
		scaleMetaData.setTestConditionPO(testConditionPO);
		return scaleMetaData;
	}
	private LayingSchemePO getLayingSchemePO() {
		LayingSchemePO layingSchemePO = new LayingSchemePO();
		
		 Row row14 = sheet.getRow(13);
		  Cell cell14 = row14.getCell(1);
		  layingSchemePO.setName(getCellValue(cell14));
		  
		  Row row15 = sheet.getRow(14);
		  Cell cell15 = row15.getCell(1);
		  layingSchemePO.setShellSurfaceOuter(getCellValue(cell15));
		  
		  Row row16 = sheet.getRow(15);
		  Cell cell16 = row16.getCell(1);
		  layingSchemePO.setShellSurfaceIner(getCellValue(cell16));
		  
		  Row row17 = sheet.getRow(16);
		  Cell cell17 = row17.getCell(1);
		  layingSchemePO.setInnerShell(getCellValue(cell17));
		  
		  Row row18 = sheet.getRow(17);
		  Cell cell18 = row18.getCell(1);
		  layingSchemePO.setRibs(getCellValue(cell18));
		  
		  Row row19 = sheet.getRow(18);
		  Cell cell19 = row19.getCell(1);
		  layingSchemePO.setOther(getCellValue(cell19));
		return layingSchemePO;
	}
	//7~13行
	private TestConditionPO getTestCondition() {
		TestConditionPO testConditionPO = new TestConditionPO();
		 Row row7 = sheet.getRow(6);
		   Cell cell7 = row7.getCell(1);
		   testConditionPO.setName(getCellValue(cell7));
		   
		   Row row8 = sheet.getRow(7);
		   Cell cell8 = row8.getCell(1);
		   testConditionPO.setTestTime(getCellValue(cell8));
		   
		   Row row9= sheet.getRow(8);
		   Cell cell9 = row9.getCell(1);
		   testConditionPO.setTestPlace(getCellValue(cell9));
		   
		   Row row10 = sheet.getRow(9);
		   Cell cell10 = row10.getCell(1);
		   testConditionPO.setDuration(getCellValue(cell10));
		   
		   Row row11 = sheet.getRow(10);
		   Cell cell11 = row11.getCell(1);
		   testConditionPO.setWaterDepth(getCellValue(cell11));
		   
		   Row row12 = sheet.getRow(11);
		   Cell cell12 = row12.getCell(1);
		   testConditionPO.setTestDepth(getCellValue(cell12));
		   
		   Row row13 = sheet.getRow(12);
		   Cell cell13 = row13.getCell(1);
		   testConditionPO.setOther(getCellValue(cell13));
		return testConditionPO;
	}
	private TestModelObjPO getTestModelObjPOFromExle() {
		TestModelObjPO testModelObjPO =  new TestModelObjPO();
	 	   Row row1 = sheet.getRow(0);
		   Cell cell1 = row1.getCell(1);
		   testModelObjPO.setName(getCellValue(cell1));
		   Row row2 = sheet.getRow(1);
		   Cell cell2 = row2.getCell(1);
		   testModelObjPO.setShellType(getCellValue(cell2));;
		   Row row3 = sheet.getRow(2);
		   Cell cell3 = row3.getCell(1);
		   testModelObjPO.setShapeSize(getCellValue(cell3));
		   Row row4 = sheet.getRow(3);
		   Cell cell4 = row4.getCell(1);
		   testModelObjPO.setWeight(getCellValue(cell4));
		   Row row5 = sheet.getRow(4);
		   Cell cell5 = row5.getCell(1);
		   testModelObjPO.setDisplacement(getCellValue(cell5));
		   Row row6 = sheet.getRow(5);
		   Cell cell6 = row6.getCell(1);
		   testModelObjPO.setOther(getCellValue(cell6));
		return testModelObjPO;
	}
	
	/**
	 *前20行都是元数据，第21行开始基础数据，从0开始计算，所以下面是20
	 *基础数据7列
	 * @param demoMeta
	 * @return
	 */
	public List<ItemScalePO> getItemScaleData(ScaleMataPO demoMeta) {
		 List<ItemScalePO> pos = new  ArrayList<ItemScalePO>();
	   	 int numOfRows = sheet.getLastRowNum() ;
	  // 	 System.out.println("待持久化的缩比数据有"+numOfRows);
	   	 for (int i = 20; i < numOfRows; i++) {
	            Row row = sheet.getRow(i);
	            if (row != null) {
	            	ItemScalePO item = new ItemScalePO();
	                for (int j = 0; j < 7; j++) {
	                    Cell cell = row.getCell(j);
	                    	String value = getCellValue(cell);
	                    	if("".equals(value)) {
	                    		value = "0";
	                    	}
	                    	//快速排序ֵ
	                    	//偶数0、2.4.6.---频率/敷瓦声目标强度/光壳辐射声功率/辐射声功率插入损失
	                    	if(j%2==0){
	                    	  if(j==0) {
	                    		  item.setRate(Integer.parseInt(value));
	                    	  }else if(j==2){
	                    		item.setLayingShellTS(Float.parseFloat(value));
	                    	  }else if(j==6){
	                    		  item.setLightShellSP(Float.parseFloat(value));;
	                    	  }else {
	                    		  item.setReductionSP(Float.parseFloat(value));
	                    	  }
	                    	}else {//1、3、5---光壳声目标强度/声目标强度降低量/敷瓦辐射声功率
	                    		if(j<3){
	                    			  item.setLightShellTS(Float.parseFloat(value));
	                    		}else if(j==3){
	                    			item.setReductionTS(Float.parseFloat(value));
	                    		}else {
	                    			item.setLayingShellSP(Float.parseFloat(value));
	                    		}
	                    	}
	                }
	                item.setScaleMataPO(demoMeta);
	                pos.add(item);
	            }
	        }
			return pos;
	}
	
}
