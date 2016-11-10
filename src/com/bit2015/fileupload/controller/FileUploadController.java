package com.bit2015.fileupload.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.bit2015.fileupload.service.*;

@Controller
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping( "/form" )
	public String form() {
		return "form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam(value="email", required=true, defaultValue="") String email,
						 @RequestParam(value="file1") MultipartFile file1,
						 Model model) {
		System.out.println("email : " + email);
		String url1 = fileUploadService.restore(file1);
		
		model.addAttribute("url1", url1);
		return "result";
	}
}
