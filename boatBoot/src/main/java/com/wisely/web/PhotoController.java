package com.wisely.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisely.Constants;
import com.wisely.domain.common.Photo;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.IPhotoService;
import com.wisely.util.FileUtil;
import com.wisely.util.Toolkit;


@Controller
@RequestMapping(value = "/photoMng")
public class PhotoController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPhotoService photoService;
	
	@RequestMapping(value="/uploadPhoto",method = RequestMethod.POST)
	public @ResponseBody  ResultVO uploadPhoto(
			@RequestParam("file") MultipartFile file, //MultipartFile  CommonsMultipartFile
			@RequestParam("type") int type,
			HttpServletRequest request) throws IOException{
		
		ResultVO re = new ResultVO(true);
		if(Toolkit.isEmpty(file) || type<1 || type >7){
			re.setSuccess(false);
			re.setMessage("参数错误");
			return re;
		}
		int realTypeIndex = type-1 ; 
		String dir = FileUtil.getSecondDir(realTypeIndex);
		String result = null ; 
		try {
			//定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
			String nameUUID = UUID.randomUUID().toString() ; 
			String fileName = dir+File.separator+nameUUID+Constants.POSTFIX;
			FileOutputStream os = new FileOutputStream(fileName);
			InputStream in = file.getInputStream();
			int b = 0;
			while((b=in.read())!=-1){ //读取文件 
				os.write(b);
			}
			os.flush(); //关闭流 
			in.close();
			os.close();
			Photo photo = new Photo();
			photo.setPrevName(file.getOriginalFilename());
			photo.setName(nameUUID);
			photo.setLocation(fileName);
			photo.setInfotype(Constants.MODEL_TYPES[realTypeIndex]);
			result = photoService.saveEntity(photo);
			if(Toolkit.isEmpty(result)){
				File tempFile = new File(fileName);
				if(tempFile.exists()){
					tempFile.delete();
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("file not found", e);
		} catch (IOException e) {
			logger.error("read and write error", e);
		}catch (Exception e) {
			logger.error("other error", e);
		}
		if(Toolkit.isEmpty(result)){
			re.setSuccess(false);
		}else{
			re.setData(result);
		}
		return re;
	}
	
	@RequestMapping(value="/deletePhoto/{pk}",method = RequestMethod.POST)
	public @ResponseBody  ResultVO deletePhoto(@PathVariable(value="pk") String pk,HttpServletRequest req) throws IOException{
		ResultVO re = new ResultVO(false);
		if(Toolkit.notEmpty(pk)){
			try {
				photoService.deleteEntity(pk);
				re.setSuccess(true);
			} catch (Exception e) {
				logger.error("delete entity error", e);
			}
		}
		return re;
		
	}
	
}