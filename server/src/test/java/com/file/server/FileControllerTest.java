package com.file.server;

import com.file.server.controller.FileController;
import org.apiguardian.api.API;
import org.junit.jupiter.api.AfterEach;
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
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertThrows;
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
		assertEquals(true, fc.checkIsMatch("chhusholachhus", "hola"));
		assertEquals(true, fc.checkIsMatch("HolA", "hola"));
		assertEquals(true, fc.checkIsMatch("HÓlä", "hola"));
		assertEquals(true, fc.checkIsMatch("HÓlä", "hola"));
		assertEquals(false, fc.checkIsMatch("HÓ-lä", "hola"));
		assertEquals(false, fc.checkIsMatch("ho la", "hola"));
		assertEquals(false, fc.checkIsMatch("h033la", "hola"));
		assertEquals(true, fc.checkIsMatch("españa", "espana"));
	}




	@Test
	public void checkAddStr() throws Exception {
		mockMvc.perform(post("/file/write?str=hola+mundo")).andExpect(status().isOk());
	}

	@Test
	public void getMatchToDataEmpty() throws Exception {
		fc.resetFile();
		ResponseEntity<String> httpResponse = fc.getMatchToData("hola");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(httpResponse.getBody());
		Assert.assertEquals(httpResponse.getBody(), "200 OK Numero de coincidencias: 0");
	}

	@Test
	public void getMatchToDataNotEmpty() throws Exception {
		fc.resetFile();
		fc.addStr("hola que tal");
		ResponseEntity<String> httpResponse = fc.getMatchToData("hola");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
		Assert.assertNotNull(httpResponse.getBody());
		Assert.assertEquals(httpResponse.getBody(), "200 OK Numero de coincidencias: 1");
	}

	@Test
	public void AddStrIO() {
		File fl = new File("data.txt");
		fl.setWritable(false,false);

		ResponseEntity<String> httpResponse = fc.addStr("hola mundo");
		Assert.assertEquals(httpResponse.getBody(), "404 NOT_FOUND: Fichero no encontrado");
	}

	@Test
	public void resetFileIO() {
		File fl = new File("data.txt");
		fl.setWritable(false,false);


		Assert.assertEquals(fc.resetFile(), false);
	}

	@Test
	public void getMatchToDataNotFile() {
		try {
			Files.delete(Paths.get("data.txt"));
		} catch (IOException e){
			e.printStackTrace();
		}
		ResponseEntity<String> httpResponse = fc.getMatchToData("hola");
		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.NOT_FOUND);
		Assert.assertNotNull(httpResponse.getBody());
		Assert.assertEquals(httpResponse.getBody(), "404 NOT_FOUND: Fichero no encontrado");
	}


	@BeforeEach
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@AfterEach
	public void reset(){
		File fl = new File("data.txt");
		fl.setWritable(true,true);
		fl.setReadable(true,true);
	}
}
