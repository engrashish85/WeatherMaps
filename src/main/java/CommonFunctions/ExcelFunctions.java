package main.java.CommonFunctions;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelFunctions {
    public static Map<String, List<String>> myMap;

    public Map<String, List<String>> retrieveDataIntoMap(String excelPath) {
        try {
            File file = new File(excelPath);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file

            //creating Workbook instance that refers to .xls file
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file

            // CAREFUL HERE! use LinkedHashMap to guarantee the insertion order!
            myMap = new LinkedHashMap<>();

            // populate map with headers and empty list
            if (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    myMap.put(cell.getStringCellValue(), new ArrayList<>());
                }
            }

            Iterator<List<String>> columnsIterator;
            // populate lists
            while (itr.hasNext()) {

                // get the list iterator every row to start from first list
                columnsIterator = myMap.values().iterator();
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // here don't check hasNext() because if the file not contains problems
                    // the # of columns is same as # of headers
                    columnsIterator.next().add(cell.getStringCellValue());
                }
            }

            // here your map should be filled with data as expected

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMap;
    }

}
