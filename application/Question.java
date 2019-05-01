package application;

import java.util.ArrayList;

public class Question {
  protected String questionTitle;
  protected ArrayList<String> options;
  protected int correctIndex;
  protected String imagePath;

  public Question(String questionTitle, ArrayList<String> options, int correctIndex,
      String imagePath) {
    this.questionTitle = questionTitle;
    this.options = options;
    this.correctIndex = correctIndex;
    this.imagePath = imagePath;
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
