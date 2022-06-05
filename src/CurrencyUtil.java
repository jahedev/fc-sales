import java.net.URL;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class CurrencyUtil {

    private final String API_URL = "https://open.er-api.com/v6/latest/USDa";

    private HashMap<String, Double> IsoCurrencyToRates;


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

            System.out.println("Today's Currency Rates from 1 USD");
            System.out.println("========================================");

            for (Iterator iterator = ratesJson.keySet().iterator(); iterator.hasNext(); ) {
                String key = (String) iterator.next();
                Double value = ((Number) ratesJson.get(key)).doubleValue();
                IsoCurrencyToRates.put(key, value);
                System.out.println(key + ": " + value);
            }
            System.out.println("========================================");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
     * Check if the ISO Currency Code is supported.
     */
    public boolean checkCurrencyCode(String IsoCurrencyCode) {
        return IsoCurrencyToRates.containsKey(IsoCurrencyCode.toUpperCase());
    }

    public Double currConvert(String IsoCurrencyCode, double amount) {
        if (!checkCurrencyCode(IsoCurrencyCode)) return null;

        Double rate = IsoCurrencyToRates.get(IsoCurrencyCode.toUpperCase());

        return amount / rate;
    }
}