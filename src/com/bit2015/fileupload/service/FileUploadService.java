package com.bit2015.fileupload.service;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "/cho/upload";
	private static final String URL = "/gallery/assets/";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";

		try {
			if(multipartFile.isEmpty() == true) {
				return url;
			}
			
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.')+1, originalFileName.length());
			String saveFileName = generateSaveFileName(extName);
			Long fileSize = multipartFile.getSize();
			
			System.out.println(originalFileName);
			System.out.println(extName);
			System.out.println(saveFileName);
			System.out.println(fileSize);
			
			writeFile(multipartFile, saveFileName);
			
			url = URL + saveFileName;
			
		} catch (IOException e) {
			// throw new UploadFileException("write file");
			// log남기기
			throw new RuntimeException("upload file");
		}
		
		return url;
	}
	
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(fileData);
		fos.close();
	}
	
	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		
		return fileName;
	}
}
