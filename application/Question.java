package application;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Question {
  String questionTitle;
  ArrayList<String> options;
  int correctIndex;
  ImageView image;

  public Question(String questionTitle, ArrayList<String> options, int correctIndex,
      ImageView image) {
    this.questionTitle = questionTitle;
    this.options = options;
    this.correctIndex = correctIndex;
    this.image = image;
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 0a59f4875ee397b8dfd624b8be1ea3a1b7285f80
