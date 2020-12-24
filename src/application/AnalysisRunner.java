package application;

//import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.Map;

//import yahoofinance.Stock;

public class AnalysisRunner {

	// return variables about how to invest
	private Date[] investmentDate; // these are dates in history that we made balance
	private double[][] weights; // these are the weights for each stocks. Dimension is 1 x number of stocks.
	private double[][] histReturn; // these are the returns following our strategy and also
	// contains the SPY return for bechmark. Dimension is 2 x number of dates in
	// history that we made balance

	// return variables about risks
	private String[] indiviualStock_risks; // these are the risks rating for each stocks. Length is the number of
											// stocks.
	private String riskRating;
	private double sharpeRatio;
	private double drawDown;

	// variables needs to be setup to run
	private int Strategy; // this is the strategy to be followed
	private int windowSize; // this is the rolling window size for historical data to be analyzed
	private int balancePeriod;// this is how frequent we adjust our portfolio
	private String Score;// this is the score coming from assessment test so that we can determine above
							// three parameters
	// the default total historical data size being adopted is set in API.
	// the default frequency of adjustment close price for analyzing is daily.

	public AnalysisRunner(String Score) {

		this.Score = Score;

		if (Score.equals("High")) {

			this.Strategy = 1;
			this.windowSize = 20;
			this.balancePeriod = 5;

		} else if (Score.equals("Mid")) {

			this.Strategy = 1;
			this.windowSize = 60;
			this.balancePeriod = 10;

		} else {

			this.Strategy = 1;
			this.windowSize = 120;
			this.balancePeriod = 30;

		}

	}

	public void AnalysisCompute(List<String> symbols) throws Exception {

		// this section is to include SPY as an benchmark regardless of user's choice if
		// they want to invest in SPY
		int contain_spy = 1;
		if (symbols.contains("SPY") != true) {

			symbols.add("SPY");

			contain_spy = 0;

		}
		String[] symbols1 = symbols.toArray(new String[symbols.size()]);

		// this is where portfolio gets built and you can get weights and historical
		// return of investment portfolios
		ArrayList<double[][]> returnResult = new ArrayList<double[][]>();
		Analysis testAnalysis = new Analysis();
		testAnalysis.getStockprice(symbols1, Score);
		testAnalysis.getStockreturn(contain_spy);
		returnResult = testAnalysis.backTesting(windowSize, balancePeriod, Strategy);
		histReturn = returnResult.get(1);
		// for(int i = 0; i < histReturn.length; i++) {

		// System.out.println("histReturn" + i + ": "+ Arrays.toString(histReturn[i]));
		// }
		weights = testAnalysis.getAdvice(windowSize, balancePeriod, Strategy);
		// System.out.println("weights: "+ Arrays.toString(weights[0]));

		// this is where to retrieve investment date
		int[][] window = testAnalysis.getWindow(windowSize, balancePeriod);
		investmentDate = testAnalysis.getDate(window);
		// System.out.println("investment times: "+ investmentDate.length);

		// this is where to retrieve risk information
		indiviualStock_risks = testAnalysis.getRisks(contain_spy);
		// System.out.println("indiviual stock risks: " +
		// Arrays.toString(indiviualStock_risks));
		RiskValuator riskRun = new RiskValuator(returnResult.get(0));
		riskRun.RiskValuatorCompute();
		drawDown = riskRun.getDrawDown();
		// System.out.println("drawDown: "+ drawDown);
		sharpeRatio = riskRun.getSharpeRatio();
		// System.out.println("sharpeRatio: "+ sharpeRatio);
		riskRating = riskRun.getRiskRating();
		// System.out.println("riskRating: "+ riskRating);

	}

	public String[] getInvestmentDate() throws Exception {
		String[] final_investmentDate = null;
		DateFormat df = DateFormat.getDateInstance(); // initialize a DateFormat() object
		try {
			final_investmentDate = new String[investmentDate.length];
		} catch (Exception e) {

		}
		for (int i = 0; i < investmentDate.length; i++) {
			final_investmentDate[i] = df.format(investmentDate[i]);
		}

		return final_investmentDate;
	}

	public double[][] getWeights() {
		return weights;
	}

	public double[][] getHistReturn() {
		return histReturn;
	}

	public String[] getRisks() {
		return indiviualStock_risks;
	}

	public String getRiskRating() {

		return riskRating;
	}

	public double getSharpeRatio() {
		// rounding this value...wayyyy too big right now
		String s = new StringBuilder(String.valueOf(sharpeRatio)).substring(0, 4).toString();

		Double sharpeRatioRounded = Double.valueOf(s);

		return sharpeRatioRounded;
	}

	public double getDrawDown() {
		// rounding this value...wayyyy too big right now

		String s = new StringBuilder(String.valueOf(drawDown)).substring(0, 4).toString();

		Double drawDownRounded = Double.valueOf(s);

		return drawDownRounded;
	}

	// String[] symbols_user = new String[] {"INTC", "BABA", "TSLA"};

	

}