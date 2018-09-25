package com.wisely.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static void downloadFiles(HttpServletResponse response,String filePath) {
		    response.setContentType("application/octet-stream");
		    response.setCharacterEncoding("UTF-8");
		    FileInputStream fs = null;
		    BufferedInputStream buff = null;
		    OutputStream myout = null;
		    try {
		        File file = new File(filePath.trim());
		        if (file.exists()) {
		            String fileName = file.getName();
		            fs = new FileInputStream(file);
		            response.addHeader(
		                    "Content-Disposition",
		                    "attachment;filename="
		                            + URLEncoder.encode(fileName, "UTF-8"));
		            buff = new BufferedInputStream(fs);
		            byte[] b = new byte[1024];
		            long k = 0;
		            myout = response.getOutputStream();
		            while (k < file.length()) {
		                int j = buff.read(b, 0, 1024);
		                k += j;
		                myout.write(b, 0, j);
		            }
		            buff.close();
		        } else {
		            PrintWriter os = response.getWriter();
		            os.write("文件不存在");
		            os.close();
		        }
		        if (myout != null) {
		            myout.flush();
		            myout.close();
		        }
		        if (fs != null) {
		            fs.close();
		        }
		    } catch (FileNotFoundException e) {
		    	logger.error("FileNotFoundException", e);
		    } catch (UnsupportedEncodingException e) {
		    	logger.error("UnsupportedEncodingException", e);
		    } catch (IOException e) {
		    	logger.error("IOException", e);
		    } finally {
		        if (myout != null) {
		            try {
		                myout.flush();
		                myout.close();
		            } catch (IOException e) {
		            	logger.error("IOException", e);
		            }
		        }
		    }
		
		}
}
