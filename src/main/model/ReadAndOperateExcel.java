package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;


/*
Represents the Class which reads an Excel File as the primary file and performs conversions to different File formats
and object structures.
*/
public class ReadAndOperateExcel {

    /* REQUIRES: First row in each sheet are the column names
     * EFFECTS: reads every sheet in an Excel File and prints it in the console
     */
    public static JSONObject convertToJson(File excelFile) throws IOException, InvalidFormatException {
        JSONObject sheetsJsonObject = new JSONObject();
        Workbook workbook = null;

        workbook = new XSSFWorkbook(excelFile);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            JSONArray sheetArray = new JSONArray();
            ArrayList<String> columnNames = new ArrayList<String>();
            Sheet sheet = workbook.getSheetAt(i);
            Iterator<Row> sheetIterator = sheet.iterator();

            while (sheetIterator.hasNext()) {

                Row currentRow = sheetIterator.next();
                JSONObject jsonObject = new JSONObject();

                jsonObjForCurrRow(currentRow, jsonObject, columnNames, sheetArray);

            }

            sheetsJsonObject.put(workbook.getSheetName(i), sheetArray);

        }

        return sheetsJsonObject;

    }

    // Helper Function
    // REQUIRES: A valid Row number, JSON object and sheet array
    // EFFECTS: parses every row of the excel file and constructs a JSON object which would be pushed into
    //          the JSON Array. (This helper function would be called during conversion from a table/excel file)
    public static void jsonObjForCurrRow(Row currRow, JSONObject jsonObj,
                                         ArrayList<String> colNames, JSONArray sheetArray) {

        if (currRow.getRowNum() != 0) {

            for (int j = 0; j < colNames.size(); j++) {

                if (currRow.getCell(j) != null) {
                    if (currRow.getCell(j).getCellTypeEnum() == CellType.STRING) {
                        jsonObj.put(colNames.get(j), currRow.getCell(j).getStringCellValue());
                    }
                    if (currRow.getCell(j).getCellTypeEnum() == CellType.BOOLEAN) {
                        jsonObj.put(colNames.get(j), currRow.getCell(j).getBooleanCellValue());
                    }
                    if (currRow.getCell(j).getCellTypeEnum() == CellType.NUMERIC) {
                        jsonObj.put(colNames.get(j), currRow.getCell(j).getNumericCellValue());
                    }

                } else {
                    jsonObj.put(colNames.get(j), "");
                }

            }

            sheetArray.put(jsonObj);

        } else {
            // store column names
            for (int k = 0; k < currRow.getPhysicalNumberOfCells(); k++) {
                colNames.add(currRow.getCell(k).getStringCellValue()); //Add
            }
        }

    }

}