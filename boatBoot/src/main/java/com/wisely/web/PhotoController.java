package com.wisely.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import com.wisely.Constants;
import com.wisely.domain.common.Photo;
import com.wisely.domainVO.DeleteVO;
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
			@RequestParam("file") MultipartFile[] files, //MultipartFile  CommonsMultipartFile
			@RequestParam("type") int type,
			HttpServletRequest request) throws IOException{
		
		ResultVO re = new ResultVO(true);
		if(Toolkit.isEmpty(files)  || files.length==0 || type<1 || type >7){
			re.setSuccess(false);
			re.setMessage("参数错误");
			return re;
		}
		int realTypeIndex = type-1 ; 
		//String dir = FileUtil.getSecondDir(realTypeIndex);
		Photo result = null ; 
		List<Photo> pts = new ArrayList<>();
		try {
			//定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字 
			for (MultipartFile multipartFile : files) {
				/*String nameUUID = UUID.randomUUID().toString() ; 
				String fileName = dir+File.separator+nameUUID+Constants.POSTFIX;
				FileOutputStream os = new FileOutputStream(fileName);
				InputStream in = multipartFile.getInputStream();
				int b = 0;
				while((b=in.read())!=-1){ //读取文件 
					os.write(b);
				}
				os.flush(); //关闭流 
				in.close();
				os.close();*/
		        Base64Encoder encoder = new Base64Encoder();
		        String base64 =  new String(encoder.encode(multipartFile.getBytes()));
				Photo photo = new Photo();
				photo.setPrevName(multipartFile.getOriginalFilename());
				/*photo.setName(nameUUID);
				photo.setAbsurl(fileName);*/
				photo.setInfotype(Constants.MODEL_TYPES[realTypeIndex]);
				photo.setUrl("data:image/png;base64,"+base64);
				result = photoService.saveEntity(photo);
				/*if(Toolkit.isEmpty(result)){
					File tempFile = new File(fileName);
					if(tempFile.exists()){
						tempFile.delete();
					}
				}*/
				pts.add(result);
			}
		} catch (FileNotFoundException e) {
			logger.error("file not found", e);
		} catch (IOException e) {
			logger.error("read and write error", e);
		}catch (Exception e) {
			logger.error("other error", e);
		}
		if(Toolkit.isEmpty(pts)){
			re.setSuccess(false);
		}else{
			re.setData(pts);
		}
		return re;
	}
	
	@RequestMapping(value="/deletePhoto",method = RequestMethod.POST)
	public @ResponseBody  ResultVO deletePhoto(
			@RequestBody DeleteVO delVO,HttpServletRequest req) throws IOException{
		ResultVO re = new ResultVO(false);
		if(Toolkit.notEmpty(delVO.getPk())){
			try {
				photoService.deleteEntity(delVO.getPk());
				re.setSuccess(true);
			} catch (Exception e) {
				logger.error("delete entity error", e);
			}
		}
		return re;
		
	}
	
}