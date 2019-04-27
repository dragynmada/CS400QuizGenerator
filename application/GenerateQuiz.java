package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class GenerateQuiz {

  int numQuestions;
  ArrayList<String> topics;

  public GenerateQuiz(int numQuestions, ArrayList<String> topics) {
    this.numQuestions = numQuestions;
    this.topics = topics;
  }

  public Quiz generate(HashMap<String, ArrayList<Question>> questionList) {
    ArrayList<Question> allQuestions = new ArrayList<Question>();
    for (String topic : topics)
      allQuestions.addAll(questionList.get(topic));
    Collections.shuffle(allQuestions);
    ArrayList<Question> quizQuestions = (ArrayList<Question>) allQuestions.stream()
        .limit((long) numQuestions).collect(Collectors.toList());
    return new Quiz(numQuestions, quizQuestions);
  }
}
