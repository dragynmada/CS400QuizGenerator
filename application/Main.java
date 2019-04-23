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
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	HashMap<String, ArrayList<Question>> questionList = new HashMap<String, ArrayList<Question>>();
	@Override
	public void start(Stage primaryStage) {
		try {
			// Creation of buttons and boxes
			VBox vungryBox = new VBox();
			HBox hungryBox1 = new HBox();
			HBox hungryBox2 = new HBox();
			Button takeQuiz = new Button("Take Quiz");
			Button addNewQuestion = new Button("Add New Question");
			Button importBooks = new Button("Import JSON");
			Button export = new Button("Export");
			
		
			// Hard coded 2 topics and 3 questions for topic Hungry
			questionList.put("Hungry", new ArrayList<Question>());
			questionList.put("Box", new ArrayList<Question>());
			questionList.get("Box").add(new Question("Question1", null, 0, null));
			questionList.get("Box").add(new Question("Question2", null, 0, null));
			questionList.get("Box").add(new Question("Question3", null, 0, null));
			
			// Create comboBox with possible topics
			ObservableList<String> topicList = FXCollections.observableArrayList(questionList.keySet());
			Collections.sort(topicList);
			ComboBox<String> topics = new ComboBox<String>();
			topics.setItems(topicList);
			topics.getSelectionModel().selectFirst();
			
			// Display questions for default topic 
			ArrayList<Question> topicQuestions = questionList.get(topics.getValue());
			ObservableList<String> questionTitles = FXCollections.observableArrayList();
			for(Question q : topicQuestions) {
				questionTitles.add(q.getQuestionTitle());
				System.out.println(q.getQuestionTitle());
			}
			ListView<String> list = new ListView<String>(questionTitles);
			
			// CSS styling for objects
			hungryBox1.setStyle("-fx-border-color:black;");
			hungryBox1.setStyle("-fx-border-radius:5;");
			takeQuiz.setStyle("-fx-font-size:20px;");
			topics.setStyle("-fx-font-size:20px;");
			
			// Add all objects to scene and display
			hungryBox1.getChildren().addAll(topics, takeQuiz);
			hungryBox2.getChildren().addAll(addNewQuestion, importBooks, export);
			vungryBox.getChildren().addAll(hungryBox1, hungryBox2, list);
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
