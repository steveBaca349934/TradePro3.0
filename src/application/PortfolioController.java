package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author stevebaca
 *
 */
public class PortfolioController implements Initializable {

	boolean isOpen = false;

	@FXML
	private AnchorPane thisPortfolio;

	@FXML
	private MenuItem thisRiskAssessmentMenuItem;

	@FXML
	private VBox thisVBox;

	@FXML
	public CategoryAxis timeCategoryAxis;

	@FXML
	public NumberAxis stockReturnPercentageNumberAxis;

	@FXML
	private TextField metricOne;

	@FXML
	private TextField metricTwo;

	@FXML
	private TextField metricThree;

	@FXML
	private Button visualize;

	@FXML
	private Button monthlyButton;

	@FXML
	private Button yearlyButton;

	@FXML
	private Text time;

	@FXML
	private Text metricOneText;

	@FXML
	private Text metricTwoText;

	@FXML
	private Text metricThreeText;

	@FXML
	private TextField portfolioBuilderTextField;

	@FXML
	public MenuBar menuBar;

	@FXML
	public Menu fileButton;

	@FXML
	public Menu editButton;

	@FXML
	public Menu helpButton;

	@FXML
	public Menu optionsButton;

	@FXML
	private MenuItem menuItemClose;

	@FXML
	private MenuItem menuItemDelete;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private VBox portfolioTracker;

	@FXML
	private VBox futureWeights;

	@FXML
	private Button anothaOneWeights;

	List<String> symbols = new ArrayList<String>();

	HashMap<String, Double> returnsRealized = new LinkedHashMap<String, Double>();
	HashMap<String, Double> returnsRealized2 = new LinkedHashMap<String, Double>();

	@FXML
	private LineChart<String, Double> Chart;

	/**
	 * Initializes the scene
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		CSSButtonIdentifier();
		// ThisRiskAssesmentMenuItemListener();
		ButtonPopulator();

	}

	/**
	 * Giving every button a CSS ID
	 */
	public void CSSButtonIdentifier() {

		menuBar.setId("menuBar");
		fileButton.setId("fileButton");
		editButton.setId("editButton");
		helpButton.setId("helpButton");
		optionsButton.setId("optionsButton");
		timeCategoryAxis.setId("timeCategoryAxis");
		stockReturnPercentageNumberAxis.setId("stockReturnPercentageNumberAxis");
		thisRiskAssessmentMenuItem.setId("thisRiskAssessmentMenuItem");
		menuItemClose.setId("menuItemClose");
		menuItemDelete.setId("menuItemDelete");
		menuItemAbout.setId("menuItemAbout");

		metricOne.setEditable(false);
		metricTwo.setEditable(false);
		metricThree.setEditable(false);

	}

	/**
	 * Adding a button listener for the risk assessment menu item listener
	 */
	/*
	 * public void ThisRiskAssesmentMenuItemListener() {
	 * 
	 * thisRiskAssessmentMenuItem.setOnAction((ActionEvent e)->{
	 * 
	 * try {
	 * 
	 * Parent part =
	 * FXMLLoader.load(getClass().getResource("/application/RiskAssessmentTest.fxml"
	 * )); Stage stage = new Stage(); Scene scene = new Scene(part);
	 * scene.getStylesheets().add(getClass().getResource(
	 * "/application/application.css").toExternalForm()); stage.setScene(scene);
	 * stage.show(); } catch (Exception e1) { // TODO: handle exception }
	 * 
	 * });
	 * 
	 * }
	 */

	/**
	 * Populating the buttons in the main vbox
	 */
	public void ButtonPopulator() {

		ArrayList<String> thisGuy = new ArrayList<String>();

		Set<String> ok = new HashSet<String>();

		thisGuy.add("SPY");
		thisGuy.add("QQQ");
		thisGuy.add("SCHH");
		thisGuy.add("XLF");
		thisGuy.add("RWM");
		thisGuy.add("UVXY");
		thisGuy.add("GLD");
		thisGuy.add("REK");

		for (String string : thisGuy) {

			Button tempButton = new Button(string);
			tempButton.setPrefWidth(190);
			tempButton.setId("tempButton");
			thisVBox.getChildren().add(tempButton);

			tempButton.setOnAction((ActionEvent e) -> {
				symbols.add(tempButton.getText());
				ok.add(tempButton.getText());

				Button temp = new Button(string);
				temp.setId("portfolioTracker");
				temp.setPrefWidth(136);
				temp.setPrefHeight(2);
				portfolioTracker.getChildren().add(temp);

			});

		}

	}

	/**
	 * This class provides functionality to the GUI takes value from Risk Assessment
	 * Test Makes it so that you can build your portfolio, visualize data, and get
	 * the weights for you portfolio
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void SPY() throws IOException {

		
		BufferedReader BR = new BufferedReader(new FileReader("RAT Score"));
		
		String thisScore = BR.readLine();
		System.out.println(thisScore);
		
		//closing resource leak
		BR.close();
		// Portfoilo gets built

		// System.out.println(RATC.AddEmUp());

		AnalysisRunner runtest = new AnalysisRunner(thisScore);

		try {
			runtest.AnalysisCompute(symbols);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		anothaOneWeights.setOnAction((ActionEvent e) -> {
			double[][] Weights = runtest.getWeights();

			ArrayList<Double> disJoint = new ArrayList<Double>();

			for (int i = 0; i < Weights.length; i++) {
				for (int j = 0; j < Weights[i].length; j++) {
					disJoint.add(Weights[i][j]);
				}
			}

			for (Double double1 : disJoint) {
				Button tempGuy = new Button(double1.toString());
				tempGuy.setPrefWidth(85);
				tempGuy.setPrefHeight(2);
				tempGuy.setId("futureWeights");
				futureWeights.getChildren().add(tempGuy);
			}

		});

		visualize.setOnAction((ActionEvent e) -> {
			/*
			 * Chart.getData().clear(); XYChart.Series<String, Double>test = new
			 * XYChart.Series<String, Double>(); test.getData().add(new XYChart.Data<String,
			 * Double>("Feb 14", 10.5)); test.getData().add(new XYChart.Data<String,
			 * Double>("Feb 19", 18.3)); test.getData().add(new XYChart.Data<String,
			 * Double>("March 10th", 15.1)); test.setName("ok"); Chart.getData().add(test);
			 */
			System.out.println("test");
			double[][] histReturn = runtest.getHistReturn();
			String[] investmentDate = null;
			//Have to try catch this unfortunately because it will throw errors every now and then
			//believe this is due to some issue in the yahoo finance API but will figure this out later
			try {
				investmentDate = runtest.getInvestmentDate();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String[] risks = runtest.getRisks();

			// futureWeights

			metricOne.setText("" + runtest.getDrawDown());
			metricOne.setAlignment(Pos.CENTER);
			metricTwo.setText("" + runtest.getSharpeRatio());
			metricTwo.setAlignment(Pos.CENTER);

			metricThree.setText("" + runtest.getRiskRating());
			metricThree.setAlignment(Pos.CENTER);

			ArrayList<Double> holder = new ArrayList<Double>();

			for (int i = 0; i < histReturn.length; i++) {
				for (int j = 0; j < histReturn[i].length; j++) {
					holder.add(histReturn[i][j]);
				}
			}

			ArrayList<String> timeHolder = new ArrayList<String>();
			////////////////////////////////////
			////////////////////////////////////
			////////////////////////////////////
			for (String string : investmentDate) {
				timeHolder.add(string);
			}
			////////////////////////////////////
			////////////////////////////////////
			////////////////////////////////////
			////////////////////////////////////

			ArrayList<Double> firstHalfHolder = new ArrayList<Double>();

			for (int i = 0; i < holder.size() / 2; i++) {
				firstHalfHolder.add(holder.get(i));
			}

			ArrayList<Double> secondHalfHolder = new ArrayList<Double>();

			for (int i = holder.size() / 2; i < holder.size(); i++) {
				secondHalfHolder.add(holder.get(i));
			}

			for (int i = 0; i < timeHolder.size(); i++) {
				returnsRealized.put(timeHolder.get(i), firstHalfHolder.get(i));

			}

			for (int i = 0; i < timeHolder.size(); i++) {
				returnsRealized2.put(timeHolder.get(i), secondHalfHolder.get(i));
			}

			XYChart.Series<String, Double> anotherOne = new XYChart.Series<String, Double>();
			XYChart.Series<String, Double> anotherOne2 = new XYChart.Series<String, Double>();

			ObservableList<XYChart.Data<String, Double>> data = FXCollections.observableArrayList();
			ObservableList<XYChart.Data<String, Double>> data2 = FXCollections.observableArrayList();

			for (Entry<String, Double> e1 : returnsRealized.entrySet()) {
				String k1 = String.valueOf(e1.getKey());
				Double v = e1.getValue();

				data.add(new Data<String, Double>(k1, v));
			}

			for (Entry<String, Double> e1 : returnsRealized2.entrySet()) {
				String k1 = String.valueOf(e1.getKey());
				Double v = e1.getValue();

				data2.add(new Data<String, Double>(k1, v));
			}

			Chart.getData().clear();
			anotherOne.setName("Portfolio Returns");
			anotherOne2.setName("S&P 500 Returns");
			anotherOne2.getData().addAll(data2);
			anotherOne.getData().addAll(data);
			Chart.getData().addAll(anotherOne, anotherOne2);

		});
	}

	/**
	 * Get symbols
	 * 
	 * @return List
	 */
	public List<String> getSymbols() {
		return symbols;
	}

	/**
	 * Get Returns realized
	 * 
	 * @return hashmap
	 */
	public HashMap<String, Double> getReturnsRealized() {
		return returnsRealized;
	}

	/**
	 * get returns realized 2
	 * 
	 * @return hashmap
	 */
	public HashMap<String, Double> getReturnsRealized2() {
		return returnsRealized2;
	}

}
