package test;

import static org.junit.Assert.*;


import org.junit.Test;

import application.PortfolioController;


public class PortfolioControllerTest {

	PortfolioController PC = new PortfolioController();
	
	/**
	 * Testing symbols is not null
	 */
	@Test
	public void TestOne() {
		
		assertNotNull(PC.getSymbols());
		
	}
	
	/**
	 * testing returns realized is not null
	 */
	@Test
	public void TestTwo() {
		
		assertNotNull(PC.getReturnsRealized());
		
	}
	
	/**
	 * testing returns realized two is not null
	 */
	@Test
	public void TestThree() {
		
		assertNotNull(PC.getReturnsRealized2());
		
	}

}
