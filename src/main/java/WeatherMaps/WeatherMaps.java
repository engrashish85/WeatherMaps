package main.java.WeatherMaps;

import com.sun.istack.logging.Logger;
import com.sun.media.sound.InvalidDataException;
import main.java.CommonFunctions.RestAssuredCommonFunctions;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class WeatherMaps extends RestAssuredCommonFunctions {
    Logger logger = Logger.getLogger(this.getClass());
    public static Boolean isBeachFavorable = true;
    ArrayList<Top10Beaches> top10Beaches = new ArrayList<>();
    String fileLocation;
    private HSSFWorkbook hb;
    private FileInputStream inputStream;
    private HSSFSheet hssfSheet;
    private static final String WEATHER_MAPS_SHEET_NAME = "WeatherMap";
    private Row row;
    protected void validateTemperatureRange(int lowTemperature, int highTemperature) {
        isBeachFavorable = true;
        Iterator mapIterator = data.entrySet().iterator();
        int i = 0;
        int j = 0;
        while (mapIterator.hasNext()) {
            if ((data.get(i).get("valid_date")).equals(dates.get(j))) {
                double temp = Double.parseDouble(data.get(i).get("temp").toString());
                if ((temp >= (double) lowTemperature) && (temp <= (double) highTemperature) &&
                        (!(top10Beaches.contains(beachEnum)))) {
                    top10Beaches.add(beachEnum);
                } else if (!((temp >= (double) lowTemperature) && (temp <= (double) highTemperature))){
                    isBeachFavorable = false;
                    top10Beaches.remove(beachEnum);
                    break;
                }
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
                if ((wind_spd >= (double) lowSpeed) && (wind_spd <= (double) highSpeed) &&
                        (!(top10Beaches.contains(beachEnum)))) {
                    top10Beaches.add(beachEnum);
                } else if (!((wind_spd >= (double) lowSpeed) && (wind_spd <= (double) highSpeed))) {
                    isBeachFavorable = false;
                    top10Beaches.remove(beachEnum);
                    break;
                }
                j = j+1;
                if (dates.size() <= j) {
                    break;
                }
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
                if ((uv <= (double) uvIndex) && (!(top10Beaches.contains(beachEnum)))) {
                    top10Beaches.add(beachEnum);
                } else if (!(uv <= (double) uvIndex)) {
                    isBeachFavorable = false;
                    top10Beaches.remove(beachEnum);
                    break;
                }
                j = j+1;
                if (dates.size() <= j) {
                    break;
                }
            }
            i = i + 1;
        }
    }

    protected void initializeExcel() throws IOException {
        File directory = new File ("output/logs");
        logger.info("Path is - "+directory.getAbsolutePath());
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fileLocation = directory.getAbsolutePath() + "\\" +
                simpleDateFormat.format(date) + ".xls";
        File file = new File(fileLocation);
        if (file.exists()) {
            inputStream = new FileInputStream(file);
            hb = new HSSFWorkbook(inputStream);
            hssfSheet = hb.getSheet(WEATHER_MAPS_SHEET_NAME);
        } else {
            hb = new HSSFWorkbook();
            hssfSheet = hb.createSheet(WEATHER_MAPS_SHEET_NAME);
            row = hssfSheet.createRow(0);
            for (int i = 0; i < 5; i++) {
                Cell cell = row.createCell(i);
                switch (i) {
                    case 0:
                        cell.setCellValue("BeachNme");
                        break;
                    case 1:
                        cell.setCellValue("PostCode");
                        break;
                    default:
                        logger.severe("Invalid row number given");
                }
            }
        }
    }

    protected void appendDataToSheet() throws InvalidDataException {
        if (top10Beaches.size() > 0) {
            String temp;
            Boolean rowFound = false;
            int rowCount = hssfSheet.getPhysicalNumberOfRows();
            for (int i = 1; i <= rowCount-1; i++) {
                temp = hssfSheet.getRow(i).getCell(0).getStringCellValue();
                if (top10Beaches.get(0).toString().equals(temp)) {
                    row = hssfSheet.getRow(i);
                    rowFound = true;
                    for (int j = 0; j < 2; j++) {
                        Cell cell = row.getCell(j);
                        switch (j) {
                            case 0:
                                cell.setCellValue(top10Beaches.get(0).toString());
                                break;
                            case 1:
                                cell.setCellValue(top10Beaches.get(0).returnPostalCodeOfBeach());
                                break;
                            default:
                                throw new InvalidDataException("Data provided "+ j + " is not correct");
                        }
                    }
                    break;
                }
            }
            if (rowFound.equals(false)) {
                row = hssfSheet.createRow(rowCount);
                for (int i = 0; i < 2; i++) {
                    Cell cell = row.createCell(i);
                    switch (i) {
                        case 0:
                            cell.setCellValue(top10Beaches.get(0).toString());
                            break;
                        case 1:
                            cell.setCellValue(top10Beaches.get(0).returnPostalCodeOfBeach());
                            break;
                        default:
                            logger.severe("Invalid row number given");
                    }
                }
            }
        }
    }

    protected void closeWorkBook() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
        hb.write(fileOutputStream);
        hb.close();
        fileOutputStream.close();
    }
}
