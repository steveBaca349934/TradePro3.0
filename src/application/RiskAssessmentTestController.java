package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RiskAssessmentTestController implements Initializable {

	boolean isOpen = false;

	@FXML
	public AnchorPane ratAnchorPane;

	@FXML
	public MenuBar ratMenuBar;

	@FXML
	private MenuItem portfolioBuilderMenuItem;

	@FXML
	private TextField riskRating;

	@FXML
	private TextField recommendedInvestmentTimeline;

	@FXML
	private TextField q1TextBox;

	@FXML
	private TextField q2TextBox;

	@FXML
	private TextField q3TextBox;

	@FXML
	private TextField q4TextBox;

	@FXML
	private TextField q5TextBox;

	@FXML
	private TextField q6TextBox;

	@FXML
	private TextField q7TextBox;

	@FXML
	private TextField q8TextBox;

	@FXML
	private TextField q9TextBox;

	@FXML
	private TextField q10TextBox;

	@FXML
	private TextField q11TextBox;

	@FXML
	private TextField q12TextBox;

	@FXML
	public Menu ratFileButton;

	@FXML
	public Menu ratEditButton;

	@FXML
	public Menu ratHelpButton;

	@FXML
	public Menu ratOptionsButton;

	@FXML
	private MenuItem ratMenuItemClose;

	@FXML
	private MenuItem ratMenuItemDelete;

	@FXML
	private MenuItem ratMenuItemAbout;

	@FXML
	private MenuItem ratq1MenuItem1;

	@FXML
	private MenuItem ratq1MenuItem2;

	@FXML
	private MenuItem ratq1MenuItem3;

	@FXML
	private MenuItem ratq1MenuItem4;

	@FXML
	private MenuItem ratq1MenuItem5;

	@FXML
	private MenuItem ratq2MenuItem1;

	@FXML
	private MenuItem ratq2MenuItem2;

	@FXML
	private MenuItem ratq2MenuItem3;

	@FXML
	private MenuItem ratq2MenuItem4;

	@FXML
	private MenuItem ratq2MenuItem5;

	@FXML
	private MenuItem ratq3MenuItem1;

	@FXML
	private MenuItem ratq3MenuItem2;

	@FXML
	private MenuItem ratq3MenuItem3;

	@FXML
	private MenuItem ratq3MenuItem4;

	@FXML
	private MenuItem ratq3MenuItem5;

	@FXML
	private MenuItem ratq4MenuItem1;

	@FXML
	private MenuItem ratq4MenuItem2;

	@FXML
	private MenuItem ratq4MenuItem3;

	@FXML
	private MenuItem ratq4MenuItem4;

	@FXML
	private MenuItem ratq4MenuItem5;

	@FXML
	private MenuItem ratq5MenuItem1;

	@FXML
	private MenuItem ratq5MenuItem2;

	@FXML
	private MenuItem ratq5MenuItem3;

	@FXML
	private MenuItem ratq5MenuItem4;

	@FXML
	private MenuItem ratq5MenuItem5;

	@FXML
	private MenuItem ratq6MenuItem1;

	@FXML
	private MenuItem ratq6MenuItem2;

	@FXML
	private MenuItem ratq6MenuItem3;

	@FXML
	private MenuItem ratq6MenuItem4;

	@FXML
	private MenuItem ratq6MenuItem5;

	@FXML
	private MenuItem ratq7MenuItem1;

	@FXML
	private MenuItem ratq7MenuItem2;

	@FXML
	private MenuItem ratq7MenuItem3;

	@FXML
	private MenuItem ratq7MenuItem4;

	@FXML
	private MenuItem ratq7MenuItem5;

	@FXML
	private MenuItem ratq8MenuItem1;

	@FXML
	private MenuItem ratq8MenuItem2;

	@FXML
	private MenuItem ratq8MenuItem3;

	@FXML
	private MenuItem ratq8MenuItem4;

	@FXML
	private MenuItem ratq8MenuItem5;

	@FXML
	private MenuItem ratq9MenuItem1;

	@FXML
	private MenuItem ratq9MenuItem2;

	@FXML
	private MenuItem ratq9MenuItem3;

	@FXML
	private MenuItem ratq9MenuItem4;

	@FXML
	private MenuItem ratq9MenuItem5;

	@FXML
	private MenuItem ratq10MenuItem1;

	@FXML
	private MenuItem ratq10MenuItem2;

	@FXML
	private MenuItem ratq10MenuItem3;

	@FXML
	private MenuItem ratq10MenuItem4;

	@FXML
	private MenuItem ratq10MenuItem5;

	@FXML
	private MenuItem ratq11MenuItem1;

	@FXML
	private MenuItem ratq11MenuItem2;

	@FXML
	private MenuItem ratq11MenuItem3;

	@FXML
	private MenuItem ratq11MenuItem4;

	@FXML
	private MenuItem ratq11MenuItem5;

	@FXML
	private MenuItem ratq12MenuItem1;

	@FXML
	private MenuItem ratq12MenuItem2;

	@FXML
	private MenuItem ratq12MenuItem3;

	@FXML
	private MenuItem ratq12MenuItem4;

	@FXML
	private MenuItem ratq12MenuItem5;

	@FXML
	private MenuButton q1Menubutton;

	@FXML
	private MenuButton q2Menubutton;

	@FXML
	private MenuButton q3Menubutton;

	@FXML
	private MenuButton q4Menubutton;

	@FXML
	private MenuButton q5Menubutton;

	@FXML
	private MenuButton q6Menubutton;

	@FXML
	private MenuButton q7Menubutton;

	@FXML
	private MenuButton q8Menubutton;

	@FXML
	private MenuButton q9Menubutton;

	@FXML
	private MenuButton q10Menubutton;

	@FXML
	private MenuButton q11Menubutton;

	@FXML
	private MenuButton q12Menubutton;

	@FXML
	private RadioButton disJauntTest;

	public int question1Value = 0;

	public int question2Value;

	public int question3Value;

	public int question4Value;

	public int question5Value;

	public int question6Value;

	public int question7Value;

	public int question8Value;

	public int question9Value;

	public int question10Value;

	public int question11Value;

	public int question12Value;

	String jake = " ";

	public HashMap<String, Integer> questionCount = new HashMap<String, Integer>();

	public boolean canOpenPortolioBuilder = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SwitchScenes();
		MakeEverythingUnModifiable();
		Question1Return();
		Question2Return();
		Question3Return();
		Question4Return();
		Question5Return();
		Question6Return();
		Question7Return();
		Question8Return();
		Question9Return();
		Question10Return();
		Question11Return();
		Question12Return();
		AddEmUp();
	}

	public void SwitchScenes() {
		ratAnchorPane.setId("ratAnchorPane");
		ratMenuBar.setId("ratMenuBar");
		ratFileButton.setId("ratFileButton");
		ratEditButton.setId("ratEditButton");
		ratHelpButton.setId("ratHelpButton");
		ratOptionsButton.setId("ratOptionsButton");
		portfolioBuilderMenuItem.setId("portfolioBuilderMenuItem");
		ratMenuItemClose.setId("ratMenuItemClose");
		ratMenuItemDelete.setId("ratMenuItemDelete");
		ratMenuItemAbout.setId("ratMenuItemAbout");

		portfolioBuilderMenuItem.setOnAction((ActionEvent e) -> {
			if (isOpen == false && checkIfReadyForPortfolioBuilder(this.questionCount)) {

				try {
					isOpen = true;

					Parent part = FXMLLoader.load(getClass().getResource("/application/Portfolio.fxml"));
					Stage stage = new Stage();
					stage.setResizable(false);
					Scene scene = new Scene(part);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					stage.setScene(scene);

					stage.show();
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
	}

	public void CSSIdenitfier() {
		ratAnchorPane.setId("ratAnchorPane");
		ratMenuBar.setId("ratMenuBar");
	}

	public void MakeEverythingUnModifiable() {
		riskRating.setEditable(false);
		recommendedInvestmentTimeline.setEditable(false);
		q1TextBox.setEditable(false);
		q2TextBox.setEditable(false);
		q3TextBox.setEditable(false);
		q4TextBox.setEditable(false);
		q5TextBox.setEditable(false);
		q6TextBox.setEditable(false);
		q7TextBox.setEditable(false);
		q8TextBox.setEditable(false);
		q9TextBox.setEditable(false);
		q10TextBox.setEditable(false);
		q11TextBox.setEditable(false);
		q12TextBox.setEditable(false);

	}

	/**
	 * 
	 */
	public void Question1Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq1MenuItem1, 1);
		thisQues1.put(ratq1MenuItem2, 2);
		thisQues1.put(ratq1MenuItem3, 3);
		thisQues1.put(ratq1MenuItem4, 4);
		thisQues1.put(ratq1MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq1MenuItem1);
		thisGuy.add(ratq1MenuItem2);
		thisGuy.add(ratq1MenuItem3);
		thisGuy.add(ratq1MenuItem4);
		thisGuy.add(ratq1MenuItem5);

		AtomicInteger thatGuy = new AtomicInteger();

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				q1TextBox.setText(thisQues1.get(menuItem).toString());

				thatGuy.getAndAdd(thisQues1.get(menuItem));

				this.question1Value = thisQues1.get(menuItem);

				// we are keeping track of how many questions they answer because I don't want
				// them to access
				// portfolio builder till they have answered all of the questions
				this.questionCount.put("question1", 1);

			});

		}

	}

	public void Question2Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq2MenuItem1, 1);
		thisQues1.put(ratq2MenuItem2, 2);
		thisQues1.put(ratq2MenuItem3, 3);
		thisQues1.put(ratq2MenuItem4, 4);
		thisQues1.put(ratq2MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq2MenuItem1);
		thisGuy.add(ratq2MenuItem2);
		thisGuy.add(ratq2MenuItem3);
		thisGuy.add(ratq2MenuItem4);
		thisGuy.add(ratq2MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question2Value = thisQues1.get(menuItem);

				q2TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question2", 1);

			});

		}

		System.out.println(jake);

	}

	public void Question3Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq3MenuItem1, 1);
		thisQues1.put(ratq3MenuItem2, 2);
		thisQues1.put(ratq3MenuItem3, 3);
		thisQues1.put(ratq3MenuItem4, 4);
		thisQues1.put(ratq3MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq3MenuItem1);
		thisGuy.add(ratq3MenuItem2);
		thisGuy.add(ratq3MenuItem3);
		thisGuy.add(ratq3MenuItem4);
		thisGuy.add(ratq3MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question3Value = thisQues1.get(menuItem);

				q3TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question3", 1);

			});

		}

	}

	public void Question4Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq4MenuItem1, 1);
		thisQues1.put(ratq4MenuItem2, 2);
		thisQues1.put(ratq4MenuItem3, 3);
		thisQues1.put(ratq4MenuItem4, 4);
		thisQues1.put(ratq4MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq4MenuItem1);
		thisGuy.add(ratq4MenuItem2);
		thisGuy.add(ratq4MenuItem3);
		thisGuy.add(ratq4MenuItem4);
		thisGuy.add(ratq4MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question4Value = thisQues1.get(menuItem);

				q4TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question4", 1);

			});

		}

	}

	public void Question5Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq5MenuItem1, 1);
		thisQues1.put(ratq5MenuItem2, 2);
		thisQues1.put(ratq5MenuItem3, 3);
		thisQues1.put(ratq5MenuItem4, 4);
		thisQues1.put(ratq5MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq5MenuItem1);
		thisGuy.add(ratq5MenuItem2);
		thisGuy.add(ratq5MenuItem3);
		thisGuy.add(ratq5MenuItem4);
		thisGuy.add(ratq5MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question5Value = thisQues1.get(menuItem);

				q5TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question5", 1);

			});

		}

	}

	public void Question6Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq6MenuItem1, 1);
		thisQues1.put(ratq6MenuItem2, 2);
		thisQues1.put(ratq6MenuItem3, 3);
		thisQues1.put(ratq6MenuItem4, 4);
		thisQues1.put(ratq6MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq6MenuItem1);
		thisGuy.add(ratq6MenuItem2);
		thisGuy.add(ratq6MenuItem3);
		thisGuy.add(ratq6MenuItem4);
		thisGuy.add(ratq6MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question6Value = thisQues1.get(menuItem);

				q6TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question6", 1);

			});

		}

	}

	public void Question7Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq7MenuItem1, 1);
		thisQues1.put(ratq7MenuItem2, 2);
		thisQues1.put(ratq7MenuItem3, 3);
		thisQues1.put(ratq7MenuItem4, 4);
		thisQues1.put(ratq7MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq7MenuItem1);
		thisGuy.add(ratq7MenuItem2);
		thisGuy.add(ratq7MenuItem3);
		thisGuy.add(ratq7MenuItem4);
		thisGuy.add(ratq7MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question7Value = thisQues1.get(menuItem);

				q7TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question7", 1);

			});

		}

	}

	public void Question8Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq8MenuItem1, 1);
		thisQues1.put(ratq8MenuItem2, 2);
		thisQues1.put(ratq8MenuItem3, 3);
		thisQues1.put(ratq8MenuItem4, 4);
		thisQues1.put(ratq8MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq8MenuItem1);
		thisGuy.add(ratq8MenuItem2);
		thisGuy.add(ratq8MenuItem3);
		thisGuy.add(ratq8MenuItem4);
		thisGuy.add(ratq8MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question8Value = thisQues1.get(menuItem);

				q8TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question8", 1);

			});

		}

	}

	public void Question9Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq9MenuItem1, 1);
		thisQues1.put(ratq9MenuItem2, 2);
		thisQues1.put(ratq9MenuItem3, 3);
		thisQues1.put(ratq9MenuItem4, 4);
		thisQues1.put(ratq9MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq9MenuItem1);
		thisGuy.add(ratq9MenuItem2);
		thisGuy.add(ratq9MenuItem3);
		thisGuy.add(ratq9MenuItem4);
		thisGuy.add(ratq9MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question9Value = thisQues1.get(menuItem);

				q9TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question9", 1);

			});

		}

	}

	public void Question10Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq10MenuItem1, 1);
		thisQues1.put(ratq10MenuItem2, 2);
		thisQues1.put(ratq10MenuItem3, 3);
		thisQues1.put(ratq10MenuItem4, 4);
		thisQues1.put(ratq10MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq10MenuItem1);
		thisGuy.add(ratq10MenuItem2);
		thisGuy.add(ratq10MenuItem3);
		thisGuy.add(ratq10MenuItem4);
		thisGuy.add(ratq10MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question10Value = thisQues1.get(menuItem);

				q10TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question10", 1);

			});

		}

	}

	public void Question11Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq11MenuItem1, 1);
		thisQues1.put(ratq11MenuItem2, 2);
		thisQues1.put(ratq11MenuItem3, 3);
		thisQues1.put(ratq11MenuItem4, 4);
		thisQues1.put(ratq11MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq11MenuItem1);
		thisGuy.add(ratq11MenuItem2);
		thisGuy.add(ratq11MenuItem3);
		thisGuy.add(ratq11MenuItem4);
		thisGuy.add(ratq11MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question11Value = thisQues1.get(menuItem);

				q11TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question11", 1);

			});

		}

	}

	public void Question12Return() {

		HashMap<MenuItem, Integer> thisQues1 = new HashMap<MenuItem, Integer>();
		thisQues1.put(ratq12MenuItem1, 1);
		thisQues1.put(ratq12MenuItem2, 2);
		thisQues1.put(ratq12MenuItem3, 3);
		thisQues1.put(ratq12MenuItem4, 4);
		thisQues1.put(ratq12MenuItem5, 5);

		ArrayList<MenuItem> thisGuy = new ArrayList<MenuItem>();
		thisGuy.add(ratq12MenuItem1);
		thisGuy.add(ratq12MenuItem2);
		thisGuy.add(ratq12MenuItem3);
		thisGuy.add(ratq12MenuItem4);
		thisGuy.add(ratq12MenuItem5);

		for (MenuItem menuItem : thisGuy) {

			menuItem.setOnAction((ActionEvent e) -> {

				this.question12Value = thisQues1.get(menuItem);

				q12TextBox.setText(thisQues1.get(menuItem).toString());
				this.questionCount.put("question12", 1);

			});

		}

	}

	/*
	 * 
	 */
	@FXML
	public String AddEmUp() {

		int value = (question1Value + question2Value + question3Value + question4Value + question5Value + question6Value
				+ question7Value + question8Value + question9Value + question10Value + question11Value
				+ question12Value) / 12;

		String str = "";

		if (value == 1 || value == 2) {
			recommendedInvestmentTimeline.setText("Monthly");
			str = "Low";
		} else if (value == 3 || value == 4) {
			recommendedInvestmentTimeline.setText("Bi-Weekly");
			str = "Mid";
		} else if (value == 5) {
			recommendedInvestmentTimeline.setText("Weekly");
			str = "High";
		}

		riskRating.setText(str);
		System.out.println(str);
		return str;

	}

	/**
	 * @author stevebaca
	 * @since 12/22
	 * @param questionCount
	 * @return true if every question has been answer else false
	 */
	private boolean checkIfReadyForPortfolioBuilder(HashMap<String, Integer> questionCount) {
		@SuppressWarnings("unchecked")
		HashSet<String> checker = new HashSet(
				Arrays.asList("question1", "question2", "question3", "question4", "question5", "question6", "question7",
						"question8", "question9", "question10", "question11", "question12"));// using a hashset because
																								// it is O(1) for
																								// contains questions
		int count = 0;

		for (String string : checker) {

			if (questionCount.containsKey(string)) {
				count++;
			}

		}

		if (count != 12) {
			return false;
		}

		return true;
	}

}
