package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import application.ScrapeForSandPandDJIA;

public class ScrapeForSandPandDJIATest {

	ScrapeForSandPandDJIA SFSPDJ = new ScrapeForSandPandDJIA();

	/**
	 * Test that the SandP500 value is not null
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {
		
		String testCaseOne = SFSPDJ.SandPReader();
		
		assertNotNull(testCaseOne);
	}
	
	/**
	 * Test that the DJIA value is not null
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		
		String testCaseTwo = SFSPDJ.DJIAReader();
		
		assertNotNull(testCaseTwo);
	}

}
