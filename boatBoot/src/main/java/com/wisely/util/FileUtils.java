package com.wisely.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static void downloadFiles(HttpServletResponse response,String filePath) {
		    //response.setContentType("application/octet-stream");
			response.reset();
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("application/vnd.ms-excel;charset=utf-8");
		    //response.setContentType("application/msexcel;charset=utf-8");
		    FileInputStream fs = null;
		    BufferedInputStream buff = null;
		    OutputStream myout = null;
		    try {
		        File file = new File(filePath.trim());
		        if (file.exists()) {
		            //String fileName = file.getName();
		            fs = new FileInputStream(file);
		            //String fileName = CharEncodingEdit.processFileName(request, "download.xls");
		            response.addHeader(
		                    "Content-Disposition","attachment;filename=download.xlsx");
		            //URLEncoder.encode(fileName, "UTF-8")); //
		           /* buff = new BufferedInputStream(fs);
		            byte[] b = new byte[1024];
		            long k = 0;
		            myout = response.getOutputStream();
		            while (k < file.length()) {
		                int j = buff.read(b, 0, 1024);
		                k += j;
		                myout.write(b, 0, j);
		            }
		            buff.close();*/
		            ServletOutputStream out = response.getOutputStream();
		            BufferedInputStream bis = null;
		            BufferedOutputStream bos = null;
		            try {
		                bis = new BufferedInputStream(new FileInputStream(file));
		                bos = new BufferedOutputStream(out);
		                byte[] buffs = new byte[2048];
		                int bytesRead;
		                while (-1 != (bytesRead = bis.read(buffs, 0, buffs.length))) {
		                    bos.write(buffs, 0, bytesRead);
		                }
		            } catch (final IOException e) {
		                throw e;
		            } finally {
		                if (bis != null)
		                    bis.close();
		                if (bos != null)
		                    bos.close();
		            }
		        } else {
		            PrintWriter os = response.getWriter();
		            os.write("文件不存在");
		            os.close();
		        }
		        
		       /* if (myout != null) {
		            myout.flush();
		        }
		        if (fs != null) {
		            fs.close();
		        }*/
		    } catch (FileNotFoundException e) {
		    	logger.error("FileNotFoundException", e);
		    } catch (UnsupportedEncodingException e) {
		    	logger.error("UnsupportedEncodingException", e);
		    } catch (Exception e) {
		    	logger.error("Exception", e);
		    } finally {
		        if (myout != null) {
		            try {
		                myout.close();
		            } catch (IOException e) {
		            	logger.error("IOException", e);
		            }
		        }
		    }
		
		}
}
