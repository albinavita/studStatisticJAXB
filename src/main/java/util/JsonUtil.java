package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Student;
import model.University;

import java.util.List;

public class JsonUtil {

    private JsonUtil() {
    }

     public static String writeToJson(List<?> lists) {
          return  new GsonBuilder().setPrettyPrinting().create().toJson(lists);
     }
}
