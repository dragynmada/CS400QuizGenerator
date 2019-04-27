package application;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONHandler {
  String filePath;

  public JSONHandler(String filePath) {
    this.filePath = filePath;
  }

  public HashMap<String, ArrayList<Question>> addQuestions(HashMap<String, ArrayList<Question>> hashMap) {
    try {
      Object obj = new JSONParser().parse(new FileReader(filePath));
      JSONObject jo = (JSONObject) obj;
      JSONArray questionArray = (JSONArray) jo.get("questionArray");
      Iterator qItr = questionArray.iterator();
      while (qItr.hasNext()) {
        JSONObject currentQuestion = (JSONObject) qItr.next();
        String questionTitle = (String) currentQuestion.get("questionText");
        String topic = (String) currentQuestion.get("topic");
        String imagePath = (String) currentQuestion.get("image");
        ArrayList<String> options = new ArrayList<String>();
        int correctIndex = 0;
        JSONArray choices = (JSONArray) currentQuestion.get("choiceArray");
        for (int i = 0; i < choices.size(); i++) {
          JSONObject currChoice = (JSONObject) choices.get(i);
          options.add((String) currChoice.get("choice"));
          if (((String) currChoice.get("isCorrect")).equals("T")) {
            correctIndex = i;
          }
        }
        Question toAdd = new Question(questionTitle, options, correctIndex, imagePath);
        if (hashMap.containsKey(topic)) {
          ArrayList<Question> toEdit = hashMap.get(topic);
          toEdit.add(toAdd);
          hashMap.replace(topic, toEdit);
        } else {
          ArrayList<Question> newList = new ArrayList<Question>();
          newList.add(toAdd);
          hashMap.put(topic, newList);
        }
      }
      return hashMap;
    } catch (Exception e) {
      // handle exceptions here
    }
    return null;
  }
  
  public void generateJSON(HashMap<String,ArrayList<Question>> hashMap) {
    // Not sure how to do this right now
  }
}
