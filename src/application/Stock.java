package application;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * This class invokes the functions of the API class to get the stock information, 
 * and convert to needed data type. 
 * To get real time price, it's suggested to call update() method first to make sure 
 * the info is updated, and then call getPrice(). 
 * For memory efficiency, the history price data won't be requested and stored until 
 * getHistory() is called.
 * @author Xi Peng
 *
 */
public class Stock {
	private String symbol; // "symbol": "MSFT"
	private String currency;  //"currency":"USD"
	private String name; //"shortName":"Microsoft Corporation"
	private double change;  //"regularMarketChange":11.029999
	private double changePercent;  //"regularMarketChangePercent":7.170252,
	private Date marketTime; //"regularMarketTime"
	private double price; //"regularMarketPrice":164.86
	private double dayHigh;  //"regularMarketDayHigh":164.99
	private double dayLow;  //"regularMarketDayLow":157.59
	private int volumn;  //"regularMarketVolume":47170448
	private double previousClose;  //"regularMarketPreviousClose":153.83
	private double bid;  //"bid":162.52,
	private double ask;  //"ask":162.64
	private double todaysOpen;  //"regularMarketOpen":160.32
	private double fiftyTwoWeekLow;  //"fiftyTwoWeekLow":118.58
	private double fiftyTwoWeekHigh;  //"fiftyTwoWeekHigh":190.7
	private Map<Date, Double> history; 
	
	/**
	 * Constructor. Requires the symbol of the stock as input, 
	 * then call methods of API class to get the public available info of the stock,
	 * and assign them to corresponding instance variable.
	 * @param symbol
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public Stock(String symbol) throws IllegalArgumentException, IOException {
		this.symbol = symbol;
		update();
	}
	
	/**
	 * Info of a stock changes realtimely, so it's good to create an independent method to update it
	 * Get stock info except for the history data, because the history data could be huge, so has its own requesting method
	 * Update the value of corresponding instance variables
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public void update() throws IllegalArgumentException, IOException {
		Map<String, String> newQuote = API.parseQuoteResponse(API.getResponse(API.constructUrl(this.symbol)));
		
		this.currency = newQuote.get("currency");
		this.name = newQuote.get("shortName");
		this.change = Double.parseDouble(newQuote.get("regularMarketChange"));
		this.changePercent = Double.parseDouble(newQuote.get("regularMarketChangePercent"));
		this.marketTime = new Date(Long.parseLong(newQuote.get("regularMarketTime")) * 1000);
		this.price = Double.parseDouble(newQuote.get("regularMarketPrice"));
		this.dayHigh = Double.parseDouble(newQuote.get("regularMarketDayHigh"));
		this.dayLow = Double.parseDouble(newQuote.get("regularMarketDayLow"));
		this.volumn = Integer.parseInt(newQuote.get("regularMarketVolume"));
		this.previousClose = Double.parseDouble(newQuote.get("regularMarketPreviousClose"));
		this.bid = Double.parseDouble(newQuote.get("bid"));
		this.ask = Double.parseDouble(newQuote.get("ask"));
		this.todaysOpen = Double.parseDouble(newQuote.get("regularMarketOpen"));
		this.fiftyTwoWeekLow = Double.parseDouble(newQuote.get("fiftyTwoWeekLow"));
		this.fiftyTwoWeekHigh = Double.parseDouble(newQuote.get("fiftyTwoWeekHigh"));
	}
	
	/**
	 * Get the history price data of a stock by calling API class's methods, 
	 * with user decided time range and interval as arguments
	 * @param range - String. Time range for history data. Valid range: "1d","5d","1mo","1y","2y","5y"
	 * @param interval - String. Time interval. Valid interval: "1m","1h","1d","1wk","1mo"
	 * @return TreeMap<Date, Double> - The history data with time as keys and price as values ordered by time
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public Map<Date, Double> getHistory(String range, String interval) 
			throws IllegalArgumentException, IOException {
		Map<Date, Double> history = API.parseHistoryResponse(API.getResponse(API.constructUrl(this.symbol, range, interval)));
		this.history = history;
		return this.history;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public String getCurrency() {
		return currency;
	}

	public double getPrice() {
		return price;
	}

	public Date getMarketTime() {
		return marketTime;
	}

	public double getChange() {
		return change;
	}

	public double getChangePercent() {
		return changePercent;
	}

	public double getBid() {
		return bid;
	}

	public double getAsk() {
		return ask;
	}

	public double getDayHigh() {
		return dayHigh;
	}

	public double getDayLow() {
		return dayLow;
	}

	public int getVolumn() {
		return volumn;
	}

	public double getPreviousClose() {
		return previousClose;
	}

	public double getTodaysOpen() {
		return todaysOpen;
	}

	public double getFiftyTwoWeekLow() {
		return fiftyTwoWeekLow;
	}

	public double getFiftyTwoWeekHigh() {
		return fiftyTwoWeekHigh;
	}
	
	/*
	 * The main() methods is to show how to initialize new object and how to use the main functions
	 * 
	 * public static void main(String[] args) {
		try {
			Stock stock = new Stock("MSFT");
			System.out.println(stock.getName());
			stock.update();
			System.out.println(stock.getPrice());
			System.out.println(stock.getHistory("1y", "1mo"));
		} catch (Exception e) {
			System.out.println("Oops! Sth wrong happened. Please check the stock symbol and try again");
		}
	}
	*/
	
}
