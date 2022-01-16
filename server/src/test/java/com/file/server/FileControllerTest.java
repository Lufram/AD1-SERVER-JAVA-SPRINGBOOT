package com.file.server;

import com.file.server.controller.FileController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.testng.AssertJUnit.assertEquals;



@SpringBootTest
class FileControllerTest {

	@Test
	public void checkTextNormalice() {
		FileController fc = new FileController();
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

}
