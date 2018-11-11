package com.wisely.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wisely.domainVO.ItemBaseVO;
import com.wisely.domainVO.ItemBigVO;
import com.wisely.domainVO.ItemScaleVO;
import com.wisely.domainVO.QueryBigVO;
import com.wisely.domainVO.QueryVO;
import com.wisely.domainVO.SacleQueryVO;
import com.wisely.util.Toolkit;

@Service
public class ExcelService {

	
	  private Workbook workBook;    
	  private Sheet sheet;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
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
    
    public void downloadSmall(HttpServletResponse response,QueryVO queryVO,
    		BaseMetaSample bms,BaseMetaBacking backing,List<ItemBaseVO> items){
    	
    	response.reset();
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    response.addHeader(
                "Content-Disposition","attachment;filename=download.xlsx");
	    OutputStream out = null;
    	InputStream inStream = null;
    	Sheet sheet = null;
    	String name = "声管小样数据";
    	Workbook workBook = null; 
  		try {
  			out = response.getOutputStream();
  			workBook = new XSSFWorkbook();
    		sheet = workBook.createSheet(name);
          } catch (Exception e) {
        	  logger.error("excel读写错误",e);
          }finally{
              try {
                  if(inStream!=null){
                      inStream.close();
                  }                
              } catch (IOException e) {                
            	  logger.error("excel读写错误",e);
              }
         }
  		if(Toolkit.notEmpty(sheet)){
  			Row row1 = sheet.createRow(0);
  			row1.createCell(0).setCellValue("样品名称");
  			row1.createCell(1).setCellValue(bms.getName());
  			Row row2 = sheet.createRow(1);
  			row2.createCell(0).setCellValue("密度");
  			row2.createCell(1).setCellValue(bms.getDensity());
  			Row row3 = sheet.createRow(2);
  			row3.createCell(0).setCellValue("弹性模量");
  			row3.createCell(1).setCellValue(bms.getFlexibleModel());
  			Row row4 = sheet.createRow(3);
  			row4.createCell(0).setCellValue("泊松比");
  			row4.createCell(1).setCellValue(bms.getPoissonRatio());
  			Row row5 = sheet.createRow(4);
  			row5.createCell(0).setCellValue("声速");
  			row5.createCell(1).setCellValue(bms.getSoundSpeed());
  			Row row6 = sheet.createRow(5);
  			row6.createCell(0).setCellValue("厚度");
  			row6.createCell(1).setCellValue(bms.getThickness());
  			Row row7 = sheet.createRow(6);
  			row7.createCell(0).setCellValue("其他");
  			row7.createCell(1).setCellValue(bms.getOther());
  			Row row8 = sheet.createRow(7);
  			row8.createCell(0).setCellValue("背衬名称");
  			row8.createCell(1).setCellValue(backing.getName());
  			Row row9 = sheet.createRow(8);
  			row9.createCell(0).setCellValue("样品前端介质");
  			row9.createCell(1).setCellValue(backing.getFrontMedium());
  			Row row10 = sheet.createRow(9);
  			row10.createCell(0).setCellValue("背衬后端介质");
  			row10.createCell(1).setCellValue(backing.getEndMedium());
  			Row row11 = sheet.createRow(10);
  			row11.createCell(0).setCellValue("其他");
  			row11.createCell(1).setCellValue(backing.getOther());
  			Row row12 = sheet.createRow(11);
  			row12.createCell(0).setCellValue("温度/︒");
  			row12.createCell(1).setCellValue(queryVO.getTemparture());
  			Row row13 = sheet.createRow(12);
  			row13.createCell(0).setCellValue("压力/Mpa");
  			row13.createCell(1).setCellValue(queryVO.getPress());
  			Row row14 = sheet.createRow(13);
  			row14.createCell(0).setCellValue("频率/Hz");
  			row14.createCell(1).setCellValue("反射系数");
  			row14.createCell(2).setCellValue("透射系数");
  			row14.createCell(3).setCellValue("吸声系数");
  			int i = 14 ; 
  			for (ItemBaseVO item:items) {
  				Row myRow = sheet.createRow(i);
  				myRow.createCell(0).setCellValue(item.getRate());
  				myRow.createCell(1).setCellValue(item.getRefect());
  				myRow.createCell(2).setCellValue(item.getTransmission());
  				myRow.createCell(3).setCellValue(item.getBondacust());
  				i++;
			}
  			try {
				workBook.write(out);
			} catch (IOException e) {
				logger.error("excel写错误",e);
			}
  		}
  		if(Toolkit.notEmpty(out)){
  			try {
  				out.flush();
				out.close();
			} catch (IOException e) {
			}
  			out = null;
  		}
    }
    
    
    
    public void downloadBig(HttpServletResponse response,QueryBigVO queryVO,
    		BaseMetaSample bms,TestModel tm,TestSystem ts,List<ItemBigVO>  items){
    	
    	response.reset();
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    response.addHeader(
                "Content-Disposition","attachment;filename=download.xlsx");
	    OutputStream out = null;
    	InputStream inStream = null;
    	Sheet sheet = null;
    	String name = "水罐大样数据";
    	Workbook workBook = null; 
  		try {
  			out = response.getOutputStream();
  			workBook = new XSSFWorkbook();
    		sheet = workBook.createSheet(name);
          } catch (Exception e) {
        	  logger.error("excel读写错误",e);
          }finally{
              try {
                  if(inStream!=null){
                      inStream.close();
                  }                
              } catch (IOException e) {                
            	  logger.error("excel读写错误",e);
              }
         }
  		if(Toolkit.notEmpty(sheet)){
  			Row row1 = sheet.createRow(0);
  			row1.createCell(0).setCellValue("样品名称");
  			row1.createCell(1).setCellValue(bms.getName());
  			Row row2 = sheet.createRow(1);
  			row2.createCell(0).setCellValue("密度");
  			row2.createCell(1).setCellValue(bms.getDensity());
  			Row row3 = sheet.createRow(2);
  			row3.createCell(0).setCellValue("弹性模量");
  			row3.createCell(1).setCellValue(bms.getFlexibleModel());
  			Row row4 = sheet.createRow(3);
  			row4.createCell(0).setCellValue("泊松比");
  			row4.createCell(1).setCellValue(bms.getPoissonRatio());
  			Row row5 = sheet.createRow(4);
  			row5.createCell(0).setCellValue("声速");
  			row5.createCell(1).setCellValue(bms.getSoundSpeed());
  			Row row6 = sheet.createRow(5);
  			row6.createCell(0).setCellValue("厚度");
  			row6.createCell(1).setCellValue(bms.getThickness());
  			Row row7 = sheet.createRow(6);
  			row7.createCell(0).setCellValue("其他");
  			row7.createCell(1).setCellValue(bms.getOther());
  			Row row8 = sheet.createRow(7);
  			row8.createCell(0).setCellValue("试验模型名称");
  			row8.createCell(1).setCellValue(tm.getName());
  			Row row9 = sheet.createRow(8);
  			row9.createCell(0).setCellValue("尺寸");
  			row9.createCell(1).setCellValue(tm.getSize());
  			Row row10 = sheet.createRow(9);
  			row10.createCell(0).setCellValue("双层壳间距");
  			row10.createCell(1).setCellValue(tm.getDoubleShellSpacing());
  			Row row11 = sheet.createRow(10);
  			row11.createCell(0).setCellValue("内壳厚度");
  			row11.createCell(1).setCellValue(tm.getInnerShellThickness());
  			
  			Row row12 = sheet.createRow(11);
  			row12.createCell(0).setCellValue("外壳厚度");
  			row12.createCell(1).setCellValue(tm.getShellThickness());
  			
  			Row row13 = sheet.createRow(12);
  			row13.createCell(0).setCellValue("内壳后端");
  			row13.createCell(1).setCellValue(tm.getInnerShellBackend());
  			
  			Row row14 = sheet.createRow(13);
  			row14.createCell(0).setCellValue("其他");
  			row14.createCell(1).setCellValue(tm.getOther());
  			
  			Row row15 = sheet.createRow(14);
  			row15.createCell(0).setCellValue("测试系统名称");
  			row15.createCell(1).setCellValue(ts.getName());
  			
  			Row row16 = sheet.createRow(15);
  			row16.createCell(0).setCellValue("介绍");
  			row16.createCell(1).setCellValue(ts.getDescribe());
  			
  			Row row17 = sheet.createRow(16);
  			row17.createCell(0).setCellValue("压力/Mpa");
  			row17.createCell(1).setCellValue(queryVO.getPress());
  			
  			Row row18 = sheet.createRow(17);
  			row18.createCell(0).setCellValue("温度/︒");
  			row18.createCell(1).setCellValue(queryVO.getTemparture());
  			
  			Row row19 = sheet.createRow(18);
  			row19.createCell(0).setCellValue("频率/Hz");
  			row19.createCell(1).setCellValue("反射系数");
  			row19.createCell(2).setCellValue("透射系数");
  			row19.createCell(3).setCellValue("吸声系数");
  			row19.createCell(4).setCellValue("回声降低/dB");
  			row19.createCell(5).setCellValue("辐射声功率/dB");
  			row19.createCell(6).setCellValue("辐射声功率插入损失/dB");
  			
  			int i = 19 ; 
  			for (ItemBigVO item:items) {
  				Row myRow = sheet.createRow(i);
  				myRow.createCell(0).setCellValue(item.getRate());
  				myRow.createCell(1).setCellValue(item.getRefect());
  				myRow.createCell(2).setCellValue(item.getTransmission());
  				myRow.createCell(3).setCellValue(item.getBondacust());
  				myRow.createCell(4).setCellValue(item.getEchoes());
  				myRow.createCell(5).setCellValue(item.getRadiation());
  				myRow.createCell(6).setCellValue(item.getRadiationlose());
  				i++;
			}
  			try {
				workBook.write(out);
			} catch (IOException e) {
				logger.error("excel写错误",e);
			}
  		}
  		if(Toolkit.notEmpty(out)){
  			try {
  				out.flush();
				out.close();
			} catch (IOException e) {
			}
  			out = null;
  		}
    }
    
    
    public void downloadScale(HttpServletResponse response,SacleQueryVO queryVO,
    		TestModelObjPO tm,TestConditionPO tc,LayingSchemePO ls,List<ItemScaleVO>  items){
    	
    	response.reset();
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    response.addHeader(
                "Content-Disposition","attachment;filename=download.xlsx");
	    
	    OutputStream out = null;
    	InputStream inStream = null;
    	Sheet sheet = null;
    	String name = "缩比模型数据";
    	Workbook workBook = null; 
  		try {
  			out = response.getOutputStream();
  			workBook = new XSSFWorkbook();
    		sheet = workBook.createSheet(name);
          } catch (Exception e) {
        	  logger.error("excel读写错误",e);
          }finally{
              try {
                  if(inStream!=null){
                      inStream.close();
                  }                
              } catch (IOException e) {                
            	  logger.error("excel读写错误",e);
              }
         }
  		if(Toolkit.notEmpty(sheet)){
  			Row row1 = sheet.createRow(0);
  			row1.createCell(0).setCellValue("外场试验模型名称");
  			row1.createCell(1).setCellValue(tm.getName());
  			Row row2 = sheet.createRow(1);
  			row2.createCell(0).setCellValue("壳体类型");
  			row2.createCell(1).setCellValue(tm.getShellType());
  			Row row3 = sheet.createRow(2);
  			row3.createCell(0).setCellValue("尺寸");
  			row3.createCell(1).setCellValue(tm.getShapeSize());
  			Row row4 = sheet.createRow(3);
  			row4.createCell(0).setCellValue("重量");
  			row4.createCell(1).setCellValue(tm.getWeight());
  			Row row5 = sheet.createRow(4);
  			row5.createCell(0).setCellValue("排水量");
  			row5.createCell(1).setCellValue(tm.getDisplacement());
  			Row row6 = sheet.createRow(5);
  			row6.createCell(0).setCellValue("其他");
  			row6.createCell(1).setCellValue(tm.getOther());
  			Row row7 = sheet.createRow(6);
  			row7.createCell(0).setCellValue("试验名称");
  			row7.createCell(1).setCellValue(tc.getName());
  			Row row8 = sheet.createRow(7);
  			row8.createCell(0).setCellValue("试验时间");
  			row8.createCell(1).setCellValue(tc.getTestTime());
  			Row row9 = sheet.createRow(8);
  			row9.createCell(0).setCellValue("试验地点");
  			row9.createCell(1).setCellValue(tc.getTestPlace());
  			Row row10 = sheet.createRow(9);
  			row10.createCell(0).setCellValue("试验时长");
  			row10.createCell(1).setCellValue(tc.getDuration());
  			Row row11 = sheet.createRow(10);
  			row11.createCell(0).setCellValue("水域深度");
  			row11.createCell(1).setCellValue(tc.getWaterDepth());
  			
  			Row row12 = sheet.createRow(11);
  			row12.createCell(0).setCellValue("试验深度");
  			row12.createCell(1).setCellValue(tc.getTestDepth());
  			
  			Row row13 = sheet.createRow(12);
  			row13.createCell(0).setCellValue("其他");
  			row13.createCell(1).setCellValue(tc.getOther());
  			
  			Row row14 = sheet.createRow(13);
  			row14.createCell(0).setCellValue("敷设方案名称");
  			row14.createCell(1).setCellValue(ls.getName());
  			
  			Row row15 = sheet.createRow(14);
  			row15.createCell(0).setCellValue("外壳外表面");
  			row15.createCell(1).setCellValue(ls.getShellSurfaceOuter());
  			
  			Row row16 = sheet.createRow(15);
  			row16.createCell(0).setCellValue("外壳内表面");
  			row16.createCell(1).setCellValue(ls.getShellSurfaceIner());
  			
  			Row row17 = sheet.createRow(16);
  			row17.createCell(0).setCellValue("内壳");
  			row17.createCell(1).setCellValue(ls.getInnerShell());
  			
  			Row row18 = sheet.createRow(17);
  			row18.createCell(0).setCellValue("肋骨");
  			row18.createCell(1).setCellValue(ls.getRibs());
  			
  			Row row19 = sheet.createRow(18);
  			row19.createCell(0).setCellValue("其他");
  			row19.createCell(1).setCellValue(ls.getOther());
  			
  			Row row20 = sheet.createRow(19);
  			row20.createCell(0).setCellValue("频率/Hz");
  			row20.createCell(1).setCellValue("光壳声目标强度");
  			row20.createCell(2).setCellValue("敷瓦声目标强度");
  			row20.createCell(3).setCellValue("声目标强度降低量");
  			row20.createCell(4).setCellValue("光壳辐射声功率");
  			row20.createCell(5).setCellValue("敷瓦辐射声功率");
  			row20.createCell(6).setCellValue("辐射声功率插入损失");
  			
  			int i = 20 ; 
  			for (ItemScaleVO item:items) {
  				Row myRow = sheet.createRow(i);
  				myRow.createCell(0).setCellValue(item.getRate());
  				myRow.createCell(1).setCellValue(item.getLightShellTS());
  				myRow.createCell(2).setCellValue(item.getLayingShellTS());
  				myRow.createCell(3).setCellValue(item.getReductionTS());
  				myRow.createCell(4).setCellValue(item.getLightShellSP());
  				myRow.createCell(5).setCellValue(item.getLayingShellSP());
  				myRow.createCell(6).setCellValue(item.getReductionSP());
  				i++;
			}
  			try {
				workBook.write(out);
			} catch (IOException e) {
				logger.error("excel写错误",e);
			}
  		}
  		if(Toolkit.notEmpty(out)){
  			try {
  				out.flush();
				out.close();
			} catch (IOException e) {
			}
  			out = null;
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
		//bigDemoMetaData.setPress(Integer.parseInt(getCellValue(cell16)));
		bigDemoMetaData.setPress(Float.parseFloat(getCellValue(cell16)));

		Row row17 = sheet.getRow(17);
		Cell cell17 = row17.getCell(1);
		bigDemoMetaData.setTemparture(Float.parseFloat(getCellValue(cell17)));
		bigDemoMetaData.setSample(sample);
		bigDemoMetaData.setSampleName(sample.getName());
		bigDemoMetaData.setTestModel(testModel);
		bigDemoMetaData.setTestModelName(testModel.getName());
		bigDemoMetaData.setTestSystem(testSystem);
		bigDemoMetaData.setTestSystemName(testSystem.getName());
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
        		   //smallDemoMetaData.setPress(Integer.parseInt(getCellValue(cell13)));
        		   smallDemoMetaData.setPress(Float.parseFloat(getCellValue(cell13)));
			   smallDemoMetaData.setSmall(isSmall);
			   smallDemoMetaData.setSamplename(sample.getName());
			   smallDemoMetaData.setBackingname(backing.getName());
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
