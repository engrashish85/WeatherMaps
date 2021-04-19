package main.java.CommonFunctions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import main.java.WeatherMaps.Top10Beaches;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RestAssuredCommonFunctions {
    public static Map<String, String> restParameters = new HashMap<>();
    public ArrayList<String> dates = new ArrayList<>();
    public static String BASE_URL = "http://api.weatherbit.io/v2.0/forecast/daily";
    private final String API_KEY = "0dd1bf7022eb4dcab1f20a7ff83565c4";
    public String response;
    public static Top10Beaches beachEnum;
    public Map<Integer, Map<String, Object>> data = new HashMap<>();
//    public ArrayList<Map<String, Object>> data = new ArrayList<>();
    public String returnApiKey() {
        return API_KEY;
    }
    public static String[] fieldNames = {"temp", "valid_date", "wind_spd", "uv"};

    public String getAPIRequest (Map<String, String> parameters) throws UnknownHostException {
        RestAssured.baseURI = BASE_URL;
        if (!(parameters.containsKey("key"))) {
            parameters.put("key", returnApiKey());
        }
        RequestSpecification request = RestAssured.given();
        Response restAssuredResponse = request.queryParams(restParameters).get();
        if (restAssuredResponse.statusCode() != 200) {
            throw new UnknownHostException();
        }
        return restAssuredResponse.getBody().asString();
    }

    public void returnDataFromResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray arrayData = jsonObject.getJSONArray("data");
        int i = 0;
        for (Object dataObject : arrayData) {
            Map <String, Object> individualDataMap = new HashMap<>();
            JSONObject dataItem = (JSONObject) dataObject;
            for (String fieldName : fieldNames) {
                Object temp = dataItem.get(fieldName);
                individualDataMap.put(fieldName, temp);
            }
            data.put(i, individualDataMap);
            i = i + 1;
        }
    }


}
