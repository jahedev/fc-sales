import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TestAPI {
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("usd.json");
        JSONObject jsonObj = (JSONObject) jsonParser.parse(fileReader);
        JSONObject rates = (JSONObject) jsonObj.get("rates");

        for (Iterator iterator = rates.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.println(rates.get(key));
        }

//        Double result = (Double) rates.get("BDT");
//        System.out.println("Result: " + result);
    }
}
