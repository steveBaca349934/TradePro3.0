package test;

import org.junit.Test;

import application.Analysis;

import org.junit.Before;
import static org.junit.Assert.*;

//import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

//import org.ejml.simple.SimpleMatrix;

public class AnalysisTest {
	
	
	private int[][] window ;
	
	private String[] indiviualStock_risks;
	
	
	@Before
	public void setup() throws Exception {
		
		//ArrayList<double[][]> returnResult;
		
		List<String> symbols = new ArrayList<String>();
 		
 		symbols.add("SPY");
 		
 		symbols.add("QQQ");
 		
 		symbols.add("GLD");
 		
 		String Score = "High";
 		
		int windowSize = 20;
		int balancePeriod = 5;
		
		int contain_spy = 1;
    	
		if(symbols.contains("SPY") != true){
         
			symbols.add("SPY");
			
			contain_spy = 0;
			
        }
		
		String[] symbols1 = symbols.toArray(new String[symbols.size()]);
		
	
		
 	
		//this is where portfolio gets built
		Analysis testAnalysis =  new Analysis();
 
 	    testAnalysis.getStockprice(symbols1,Score);
 	
 	    testAnalysis.getStockreturn(contain_spy);
 	
 	    //returnResult = testAnalysis.backTesting(windowSize, balancePeriod, Strategy);
 	    
 	    window = testAnalysis.getWindow(windowSize, balancePeriod);	 
 	    
 	    indiviualStock_risks = testAnalysis.getRisks(contain_spy);
 	    
 	    //double sharpeRatio = riskRun.getSharpeRatio();
 	    
	
	}
		
		
	
	
	@Test
    public void historicalQuoteTest() throws IOException {
        
		
		assertEquals(47,  window[0].length);
		assertEquals("mid risk",  indiviualStock_risks[0]);
		
		
    	

	}







}
