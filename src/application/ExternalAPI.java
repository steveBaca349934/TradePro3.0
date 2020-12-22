package application;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
//import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;


import java.io.IOException;
/*import java.math.BigDecimal;*/
import java.util.Calendar;
import java.util.Map;
/*import java.util.List;*/
/*import java.util.Map;*/


public class ExternalAPI {
	
	
	private String[] symbol;
	
	private Calendar today;
    
    private Map<String, Stock> stocks;
    
    
    
    public void setup() {
        
    	today = Calendar.getInstance();
    	
    	
    	//we can take the nearest Wednesday as the investment day
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
	
		int lag = 4 - dayOfWeek;
		
		today.add(Calendar.DATE, -7);
		
		today.add(Calendar.DATE, lag);
        
        
        
   
    }

	
    /*interval here is set by user depending on his investment horizon*/
	public void multiYearData(String[] symbols, String Score) throws IOException {
        
		
		symbol = symbols;
		
		Calendar from = (Calendar) today.clone();
        Calendar to = (Calendar) today.clone();
        
        
        
        
        if(Score.equals("High")) {
        	
        	from.add(Calendar.YEAR, -1); // from 10 years ago
        	
        	stocks = YahooFinance.get(symbols, from, to, Interval.DAILY); // single request
        	
        }
        
        
        if(Score.equals("Mid")) {
        	
        	from.add(Calendar.YEAR, -3); // from 5 years ago
        	
        	stocks = YahooFinance.get(symbols, from, to, Interval.DAILY); // single request
        	
        }
        
        
        if(Score.equals("Low")) {
        	
        	from.add(Calendar.YEAR, -5); // from 2 years ago
        	
        	stocks = YahooFinance.get(symbols, from, to, Interval.DAILY); // single request
        	
        }
          
       

	}
	
	
	public Map<String, Stock> getStocks() {
		return stocks;
	}


	public String[] getSymbol() {
		return symbol;
	}
	
	
	/*public static void main(String[] args) {
		
		API testApi = new API();
		
		testApi.setup();
		
		try{
			testApi.multiYearTest();
		}catch(IOException ex) {
			
		}
		
		
		System.out.print(testApi.getHistoricalQuote().getAdjClose());
		
	}*/


	
	
	
}
