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

import com.wisely.domain.BaseMetaBacking;
import com.wisely.domain.BaseMetaSample;
import com.wisely.domain.Demometadata;
import com.wisely.domain.Item;

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
   	 System.out.println("带持久化的数据有"+numOfRows);
   	 for (int i = 14; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            List<String> list = new ArrayList<String>();
            if (row != null) {
            	Item item = new Item();
            //	System.out.println("带处理的行"+row.getLastCellNum());
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
                    list.add(this.getCellValue(cell));
                }
               
                item.setSmallPO(demoMeta);
                pos.add(item);
            }
        }
		return pos;
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
