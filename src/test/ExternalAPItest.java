package test;

import org.junit.Test;

import application.ExternalAPI;

import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.io.IOException;

public class ExternalAPItest {
	
	private Calendar today;
	private Calendar from;
	static ExternalAPI testApi;
	
	
	@Before
	public void setup() {
		today = Calendar.getInstance();
        today.set(Calendar.YEAR, 2019);
        today.set(Calendar.MONTH, 8);
        today.set(Calendar.DATE, 11);

        from = (Calendar) today.clone();
        from.add(Calendar.YEAR, -1);
        
        testApi = new ExternalAPI();
		
		testApi.setup();
		
		String[] symbols = {"SPY"};
		
		try{
			testApi.multiYearData(symbols,"Low");
		}catch(IOException ex) {
			
		}
	}
		
		
	
	
	@Test
    public void stockAdjcloseTest() throws IOException {
		
        
		assertEquals(262.514984, testApi.getStocks().get("SPY").getHistory(from,today).get(0).getAdjClose());

	}
	

	

}



