package com.wisely.web.mng.data;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisely.domainVO.ResultVO;
import com.wisely.service.ExcelNewService;
import com.wisely.service.UploadService;

import until.constant.Constants;

@RestController
@RequestMapping(value = "/uploadManager")
public class UploadManager {
	
	@Autowired
	private ExcelNewService  excelNewService;
	@Autowired
	private UploadService  uploadService;
	
	@RequestMapping(value="uploadItems",method = RequestMethod.POST)
	public  ResultVO UploadExcle(MultipartFile file, String catalog,  HttpServletRequest request) throws IOException{
		ResultVO res = new ResultVO(false,"",null);
		if(file==null) {
			res.setMessage("没有上传文件，请选着文件上传");
			return res;
		}
		//小样数据加载，一共8个属性，根据样品名称、温度、压力、背衬四个固定，其他4个属性（频率、反射系数、投射系数、吸声系数）变动
		if(Constants.SMALLDEMO.equals(catalog)) {
			res = uploadService.getSmallDemo(file);
			//res.setSuccess(true);
		}else if(Constants.BIGDEMO.equals(catalog)) {
			res = uploadService.getBigDemo(file);
			//res.setSuccess(true);
		}else if(Constants.CONDEMO.equals(catalog)){
			excelNewService.load(file,"缩比模型数据");
			res = uploadService.getConDemo(file);
			//res.setSuccess(true);
		}else {
			res.setMessage("不支持的模型数据");
		}
		return res;
	}
}

