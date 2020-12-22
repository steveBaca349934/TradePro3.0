package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import application.*;

/**
 * This class is to test the main methods of the Stock and API classes. 
 * Since the Stock class invoke the API class in constructor and other methods, 
 * here test the two classes together.
 * @author Xi Peng
 *
 */

public class StockAndAPIClassesTest {

	@Test
	/**
	 * Test the constructor of the Stock class
	 * The constructor of the Stock class will call its update() method inside
	 * The update() method will call API class's static methods construcUrl(String symbol), 
	 * getResponse(), and parseQuoteResponse(). So test constructor also test all of these methods
	 */
	public void stockConstructorTest() {
		// We can not test all of the stock symbols on the market, so pick out these nine which we will use in our app
		String[] stockSymbols = {"SPY", "IWM", "QQQ", "SCHH", "XLF", "RWM", "UVXY", "GLD", "REK"};
		String[] stockNames = {"SPDR S&P 500", "iShares Russell 2000 ETF", "Invesco QQQ Trus",
				"Schwab U.S. REIT ETF", "SPDR Select Sector Fund - Finan", "ProShares Short Russell2000",
				"ProShares Trust Ultra VIX Short", "SPDR Gold Trust", "ProShares Short Real Estate"};
		
		for (int i = 0; i < 9; i++) {
			try {
				Stock stock = new Stock(stockSymbols[i]);
				assertEquals(stockNames[i], stock.getName(), "The stock name is not right.");
				
				// The price is real time quote, so no fixed value for assertion, just print it out.
				System.out.println(stock.getName() + ": " + stock.getPrice()); 
			} catch (IllegalArgumentException | IOException e) {
				System.out.println(stockSymbols[i] + ": " + e.getClass() + " Exception thrown. Please check the symbol and arguments");
			}
		}
	}
	
	@Test
	/**
	 * Test getHistory() method of the Stock class. 
	 * It will call API class's methods construcUrl(String symbol, String range, String interval), 
	 * getResponse() and parseHistoryResponse() methods. 
	 */
	public void getHistoryTest() {
		String[] stockSymbols = {"SPY", "IWM", "QQQ", "SCHH", "XLF", "RWM", "UVXY", "GLD", "REK"};
		// Yahoo Finance API allows many time ranges and intervals, these are what will be used in our app
		String[] intervals = {"1d", "1wk", "1mo"};
		String[] ranges = {"1y", "2y", "5y"};
		
		for (String symbol: stockSymbols) {
			try {
				Stock stock = new Stock(symbol);
				for (int i = 0; i < 3; i++) {
					/*
					 *  The returned price data could change depending on when you do the request
					 *  so it's hard to use assertions. It would be easier to just print out 
					 *  and manually check some of them
					 */
					System.out.println(symbol + " " + ranges[i] + " " + intervals[i]);
					System.out.println(stock.getHistory(ranges[i], intervals[i]));
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println(symbol + ": " + e.getClass() + " Exception thrown. Please check the symbol and arguments");
			}
			System.out.println();
		}
	}
	
	@Test
	/**
	 * Test the IOException throw
	 * Given malformed URL, the getResponse() method of API class should throw IOException
	 */
	public void ioExceptionThrowTest() {
		Assertions.assertThrows(IOException.class, () -> {
			API.getResponse("http"); 
		  });
	}
	
	@Test
	/**
	 * Test the IllegalArgumentException throw
	 * Given illegal user input of stock symbol, the getResponse() method of API class will return no data.
	 * Then the parseQuoteResponse() and parseHistoryResponse() methods should throw IllegalArgumentException
	 */
	public void illegalArgumentExceptionThrowTest() {
		String[] wrongUserInput = {"", "MSFT123", null};
		for (String s: wrongUserInput) {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				API.parseQuoteResponse(API.getResponse(API.constructUrl(s)));
			});
			
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				API.parseHistoryResponse(API.getResponse(API.constructUrl(s, "1y", "1mo")));
			});
		}
		
	}

}
