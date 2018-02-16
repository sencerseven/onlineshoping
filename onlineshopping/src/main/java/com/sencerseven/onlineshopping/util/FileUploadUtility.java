package com.sencerseven.onlineshopping.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	
	private static final String ABS_PATH ="\\	Users\\sencer\\git\\onlineshoping\\onlineshopping\\src\\main\\webapp\\assets\\images\\products\\";
	private static  String REAL_PATH="";
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// get Real Path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/products/");
		
		logger.info(REAL_PATH);
		
		// to make sure all directory exists ? 
		// please create directory
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			// server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			
			//project directory uplaod
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			
		}catch (Exception e) {
			logger.warn(e.getStackTrace().toString());
		}
	}
	
}
