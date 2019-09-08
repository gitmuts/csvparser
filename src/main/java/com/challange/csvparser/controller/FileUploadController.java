package com.challange.csvparser.controller;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.challange.csvparser.service.ExportFileService;
import com.challange.csvparser.service.ParseCSVService;


@RestController
public class FileUploadController {

	
	Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	ParseCSVService service;
	
	@Autowired
	ExportFileService exportService;

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String loadCSV(@RequestParam("file") MultipartFile file, @RequestParam("separator") char colSeparator){
		
		try {
		
			    byte[] bytes = file.getBytes();
	            Path path = Paths.get(file.getOriginalFilename());
	            Files.write(path, bytes);
	            
	            String response=service.parseFile(file.getOriginalFilename(), colSeparator);
	            return response;
		}catch(Exception e) {
			logger.error(e.getMessage());
			return "Error occurred while uploading file";
		}
		
		
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ResponseEntity<?> getCSVExport(){
		
		try {
		
			String fileName = exportService.getUsersExport();
			
			if(!fileName.equals("")){
				
				InputStreamResource resource = new InputStreamResource(new FileInputStream(fileName));
				
				HttpHeaders headers = new HttpHeaders();
		        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		        headers.add("Pragma", "no-cache");
		        headers.add("Expires", "0");
		        
				 return ResponseEntity.ok()
				            .headers(headers)
				            .contentLength(fileName.length())
				            .contentType(MediaType.parseMediaType("application/octet-stream"))
				            .body(resource);
			}else {
				return new ResponseEntity<>("Error cocured while getting CSV Export", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			    
		}catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("Error cocured while getting CSV Export", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}
