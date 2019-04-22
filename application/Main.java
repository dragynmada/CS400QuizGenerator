package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	HashMap<String, ArrayList<Question>> questionList = new HashMap<String, ArrayList<Question>>();
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox vungryBox = new VBox();
			HBox hungryBox1 = new HBox();
			HBox hungryBox2 = new HBox();
			Button takeQuiz = new Button("Take Quiz");
			Button addNewQuestion = new Button("Add New Question");
			Button importBooks = new Button("Import JSON");
			Button export = new Button("Export");
			
			
			questionList.put("Hungry", null);
			questionList.put("Box", null);
			
			
			ObservableList<String> topicList = FXCollections.observableArrayList(questionList.keySet());
			
			Collections.sort(topicList);
		
			ComboBox topics = new ComboBox();
			
			topics.setItems(topicList);
			
			hungryBox1.setStyle("-fx-border-color:black;");
			hungryBox1.setStyle("-fx-border-radius:5;");
			takeQuiz.setStyle("-fx-font-size:60px;");
			
			
			
			hungryBox1.getChildren().addAll(topics, takeQuiz);
			hungryBox2.getChildren().addAll(addNewQuestion, importBooks, export);
			vungryBox.getChildren().addAll(hungryBox1, hungryBox2);
			Scene scene = new Scene(vungryBox, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
