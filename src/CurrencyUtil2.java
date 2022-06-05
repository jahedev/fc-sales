import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

class CurrencyUtil2 {

    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    private static HashMap<String, Double> IsoCurrencyToRates;


    public CurrencyUtil2() {
        initialize();
    }

    /* intialize by putting current currency rates to hash map */
    public boolean initialize() {
        IsoCurrencyToRates = new HashMap<>();

        try {
            URL url = new URL(API_URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Wrong ISO code provided.");
                return false;
            }

            StringBuilder infoStr = new StringBuilder();
            Scanner urlScanner = new Scanner(url.openStream());

            while (urlScanner.hasNext()) {
                infoStr.append(urlScanner.nextLine());
            }

            urlScanner.close();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(infoStr.toString());
            JSONObject ratesJson = (JSONObject) jsonObj.get("rates");

            for (Iterator iterator = ratesJson.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                Double value = (Double) ratesJson.get(key);

                IsoCurrencyToRates.put(key, value);
                System.out.println(key + ": " + value);
            }

            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    /*
     * Check if the ISO Currency Code is supported.
     */
    public static boolean checkCurrencyCode(String IsoCurrencyCode) {
        return IsoCurrencyToRates.containsKey(IsoCurrencyCode);
    }

    public static Double currConvert(String IsoCurrencyCode, double amount) {
        if (!checkCurrencyCode(IsoCurrencyCode)) return null;

        Double rate = IsoCurrencyToRates.get(IsoCurrencyCode);

        return amount / rate;
    }
}