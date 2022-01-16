package com.file.server;

import com.file.server.controller.FileController;
import org.apiguardian.api.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertEquals;



@SpringBootTest
class FileControllerTest {

	@InjectMocks
	private FileController fc;
	@Autowired
	public WebApplicationContext webApplicationContext;
	public MockMvc mockMvc;

	@Test
	public void checkTextNormalice() {
		assertEquals(true, fc.checkIsMatch("hola hola", "hola"));
		assertEquals(true, fc.checkIsMatch("chhuschhus hola", "hola"));
		assertEquals(true, fc.checkIsMatch("hola chhuschhus", "hola"));
		assertEquals(false, fc.checkIsMatch("chhusholachhus", "hola"));
		assertEquals(true, fc.checkIsMatch("HolA", "hola"));
		assertEquals(true, fc.checkIsMatch("HÓlä", "hola"));
		assertEquals(false, fc.checkIsMatch("HÓlä", "hola"));
		assertEquals(false, fc.checkIsMatch("HÓ-lä", "hola"));
		assertEquals(false, fc.checkIsMatch("ho la", "hola"));
		assertEquals(false, fc.checkIsMatch("h033la", "hola"));
		assertEquals(true, fc.checkIsMatch("españa", "espana"));
	}
	@Test
	public void checkAddStr() throws Exception {
		mockMvc.perform(post("/file/write?str=hola+mundo")).andExpect(status().isOk());
	}

	@BeforeEach
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
}
