package com.challange.csvparser;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.challange.csvparser.controller.FileUploadController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadTests {

	@Autowired
	FileUploadController controller;
	
	 private MockMvc mockMvc;
	
	@Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
       
    }
	
	@Test
	public void parseFile() throws Exception {
		
		final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.csv");
		
		final MockMultipartFile file = new MockMultipartFile("file", "sample.csv", "application/csv", inputStream);
		
		
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadfile").file(file).param("separator", ",").contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

		
		Assert.assertEquals("Success", result.getResponse().getContentAsString());
		
	}
}
