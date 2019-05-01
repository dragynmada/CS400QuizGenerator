package application;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JSONHandler {

  final FileChooser fc;

  public JSONHandler() {
    fc = new FileChooser();
    fc.setTitle("Save to JSON");
  }

  public HashMap<String, ArrayList<Question>> addQuestions(
      HashMap<String, ArrayList<Question>> hashMap, String filePath) {
    try {
      Object obj = new JSONParser().parse(new FileReader(filePath));
      JSONObject jo = (JSONObject) obj;
      JSONArray questionArray = (JSONArray) jo.get("questionArray");
      Iterator qItr = questionArray.iterator();
      while (qItr.hasNext()) {
        JSONObject currentQuestion = (JSONObject) qItr.next();
        String questionTitle = (String) currentQuestion.get("questionText");
        String topic = (String) currentQuestion.get("topic");
        topic = topic.toLowerCase();
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

	public void generateJSON(HashMap<String, ArrayList<Question>> hashMap, String filePath) {
		try {
			JSONObject toSave = new JSONObject();
			JSONArray questions = new JSONArray();
			List<String> topics = new ArrayList<String>(hashMap.keySet());
			for (String topic : topics) {
				for (Question q : hashMap.get(topic)) {
					JSONObject toAdd = new JSONObject();
					toAdd.put("meta-data", "unused");
					toAdd.put("questionText", q.getQuestionTitle());
					toAdd.put("topic", topic);
					if (q.imagePath == null) {
						toAdd.put("image", q.imagePath);
					} else {
						toAdd.put("image", "none");
					}
					JSONArray choiceArray = new JSONArray();
					if (q.options != null) {
						for (int i = 0; i < q.options.size(); i++) {
							JSONObject option = new JSONObject();
							if (i == q.correctIndex)
								option.put("isCorrect", "T");
							else
								option.put("isCorrect", "F");
							option.put("choice", q.options.get(i));
						}
						toAdd.put("choiceArray", choiceArray);
					}
					questions.add(toAdd);
				}
			}
			toSave.put("questionArray", questions);
			File fileguy = new File(filePath);
			Files.write(Paths.get(filePath), toSave.toJSONString().getBytes());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
