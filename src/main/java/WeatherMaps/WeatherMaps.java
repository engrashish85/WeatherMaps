package main.java.WeatherMaps;

import main.java.CommonFunctions.RestAssuredCommonFunctions;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;


public class WeatherMaps extends RestAssuredCommonFunctions {
    protected void validateTemperatureRange(int lowTemperature, int highTemperature) {
        Iterator mapIterator = data.entrySet().iterator();
        int i = 0;
        int j = 0;
        while (mapIterator.hasNext()) {
            if ((data.get(i).get("valid_date")).equals(dates.get(j))) {
                double temp = Double.parseDouble(data.get(i).get("temp").toString());
                assertTrue("high temperature is greater than - " + lowTemperature, (temp >= (double) lowTemperature));
                assertTrue("high temperature is less than - " + highTemperature, (temp <= (double) highTemperature));
                j = j+1;
                if (dates.size() <= j) {
                    break;
                }
            }
            i = i + 1;
        }
    }

    protected void validateWindSpeedRange(int lowSpeed, int highSpeed) {
        Iterator mapIterator = data.entrySet().iterator();
        int i = 0;
        int j = 0;
        while (mapIterator.hasNext()) {
            if ((data.get(i).get("valid_date")).equals(dates.get(j))) {
                double wind_spd = Double.parseDouble(data.get(i).get("wind_spd").toString());
                assertTrue("Wind Speed is greater than - " + lowSpeed, (wind_spd >= (double) lowSpeed));
                assertTrue("Wind Speed is less than - " + highSpeed, (wind_spd <= (double) highSpeed));
                j = j+1;
            }
            i = i + 1;
        }
    }

    protected void validateUVIndex(int uvIndex) {
        Iterator mapIterator = data.entrySet().iterator();
        int i = 0;
        int j = 0;
        while (mapIterator.hasNext()) {
            if ((data.get(i).get("valid_date")).equals(dates.get(j))) {
                double uv = Double.parseDouble(data.get(i).get("uv").toString());
                assertTrue("uv index should be less than - " + uvIndex, (uv <= (double) uvIndex));
                j = j+1;
            }
            i = i + 1;
        }
    }

}
