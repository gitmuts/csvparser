package com.challange.csvparser.service;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.challange.csvparser.model.User;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class ExportFileService {

	
	Logger logger = LoggerFactory.getLogger(ExportFileService.class);
	
	public String getUsersExport() {
		
		try {
			
			List<User> users = new ArrayList<>();
			Date date = new Date();
			User user1 = new User("Test", "User", date, "201 NRB", "2362873", "FEMALE", 13);
			
			User user2 = new User("Test2", "User2", date, "201 NRB", "2362873", "MALE", 18);
			
			users.add(user1); users.add(user2);
			
			return writeToFile(users);
 		}catch(Exception e) {
			logger.error(e.getMessage());
			return "";
		}
	}
	
	private String writeToFile(List<User> users) {
		try {
		String fileName = "users_export.csv";
		Writer writer  = new FileWriter(fileName);
		 
	    StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
	       .build();
	 
	    sbc.write(users);
	    writer.close();
	    return fileName;
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}
	
}
