package application;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Question {
  String questionTitle;
  ArrayList<String> options;
  int correctIndex;
  ImageView image;

  public Question(String questionTitle, ArrayList<String> options, int correctIndex,
      String imagePath) {
    this.questionTitle = questionTitle;
    this.options = options;
    this.correctIndex = correctIndex;
    if (imagePath != null)
      this.image = new ImageView(imagePath);
    else
      this.image = null;
  }

  public int getCorrect() {
    return this.correctIndex;
  }

  public ArrayList<String> getOptions() {
    return options;
  }

  public String getQuestionTitle() {
    return this.questionTitle;
  }
}
