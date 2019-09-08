package com.challange.csvparser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.challange.csvparser.model.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


@Service
public class ParseCSVService {

	Logger logger = LoggerFactory.getLogger(ParseCSVService.class);
	
	
	
	public String parseFile(String uploadedFile, char colSeparator) {
		
		try (Reader reader = Files.newBufferedReader(Paths.get(uploadedFile));) {
			CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader).withType(User.class)
					.withIgnoreLeadingWhiteSpace(true)
					.withIgnoreQuotations(true)
					.withSeparator(colSeparator)
					.build();

			Iterator<User> csvUserIterator = csvToBean.iterator();
			
			
			
			return "Success";
		} catch (IOException e) {
			logger.error(e.getMessage());
			return String.format("Error occurred while parsing file %s", e.getMessage());
		}
	}
}
