package main.java.CommonFunctions;

import com.google.gson.Gson;
import com.sun.istack.logging.Logger;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import main.java.WeatherMaps.Top10Beaches;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestAssuredCommonFunctions {

    Logger logger = Logger.getLogger(this.getClass());

    public static Map<String, String> restParameters = new HashMap<>();
    public ArrayList<String> dates = new ArrayList<>();
    private static final String API_KEY = "0dd1bf7022eb4dcab1f20a7ff83565c4";
    public String response;
    public static Top10Beaches beachEnum;
    public Map<Integer, Map<String, Object>> data = new HashMap<>();

    public static String returnApiKey() {
        return API_KEY;
    }

    public static String[] fieldNames = {"temp", "valid_date", "wind_spd", "uv"};

    public static String getAPIRequest(String url, String childUrl, Map<String, String> parameters) throws UnknownHostException {
        String response;
        if (childUrl.isEmpty()) {
            RestAssured.baseURI = url;
        } else {
            RestAssured.baseURI = url + childUrl;
        }
        if (parameters != null) {
            if (!((parameters.containsKey("key")) && (url.contains("weatherbit")))) {
                parameters.put("key", returnApiKey());
            }
        }
        RequestSpecification request = RestAssured.given();
        Response restAssuredResponse = request.queryParams(restParameters).get();
        if (!((restAssuredResponse.statusCode() != 200) || (restAssuredResponse.statusCode() != 404))) {
            throw new UnknownHostException();
        }
        response = restAssuredResponse.getBody().asString();
        return response;
    }

    public void returnDataFromResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray arrayData = jsonObject.getJSONArray("data");
        int i = 0;
        for (Object dataObject : arrayData) {
            Map<String, Object> individualDataMap = new HashMap<>();
            JSONObject dataItem = (JSONObject) dataObject;
            for (String fieldName : fieldNames) {
                Object temp = dataItem.get(fieldName);
                individualDataMap.put(fieldName, temp);
            }
            data.put(i, individualDataMap);
            i = i + 1;
        }
    }

    public List<String> populateJsonArrayResponsesToList(String response) {
        List<String> jsonResponse = new ArrayList<>();
        if (response.startsWith("[")) {
            JSONArray json = new JSONArray(response);
            if (json.length() > 0) {
                for (int i = 0; i < json.length(); i++) {
                    jsonResponse.add(json.getJSONObject(i).toString());
                    logger.info("The json present at " + (i + 1) + " position is - " + jsonResponse.get(i));
                }
            }
        } else {
            jsonResponse.add(response);
        }
        return jsonResponse;
    }

    public Map<Object, Object> returnValuesFromResponse(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, Map.class);
    }


}
