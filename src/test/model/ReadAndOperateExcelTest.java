package model;

import model.ReadAndOperateExcel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


/*
This class represents the jUnit tests for the ReadAndOperateExcel Model Class methods
 */
public class ReadAndOperateExcelTest {

    String json = new String();
    ReadAndOperateExcel readExcelObj = new ReadAndOperateExcel();

    File excelTestFile;
    File booleanTestFile;
    File blankTestFile;

    @BeforeEach
    void setup() {
        excelTestFile = new File("./data/student.xlsx"); //creating a new file instance
        booleanTestFile = new File("./data/studentTestBoolean.xlsx"); //creating a new file instance
        blankTestFile = new File("./data/studentTestBlank.xlsx"); //creating a new file instance
    }

    @Test
    void testConvertToJson() throws IOException, InvalidFormatException {
        json = readExcelObj.convertToJson(excelTestFile).toString();
        String jsonOutput = "{\"Sheet1\":" +
                "[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        String jsonWrongOutput = "{[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        assertTrue(jsonOutput.equals(json));
        assertFalse(jsonWrongOutput.equals(json));

    }

    @Test
    void testConvertToJsonBoolean() throws IOException, InvalidFormatException {
        json = readExcelObj.convertToJson(booleanTestFile).toString();
        String jsonOutput = "{\"Sheet1\":[{\"Name \":\"Vdesh\",\"True/False\":false},{\"Name \":\"Vishal\",\"True/False\":true}]}";
        String jsonWrongOutput = "{[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        assertTrue(jsonOutput.equals(json));
        assertFalse(jsonWrongOutput.equals(json));

    }

    @Test
    void testConvertToJsonBlank() throws IOException, InvalidFormatException {
        json = readExcelObj.convertToJson(blankTestFile).toString();
        String jsonOutput = "{\"Sheet1\":[{\"Grade\":95,\"Name\":\"Vdesh\"}," +
                "{\"Grade\":98,\"Name\":\"John \"}," +
                "{\"Grade\":\"\",\"Name\":\"Vishal \"}," +
                "{\"Grade\":99,\"Name\":\"Phillip\"}]}";
        String jsonWrongOutput = "{[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        assertTrue(jsonOutput.equals(json));
        assertFalse(jsonWrongOutput.equals(json));

    }

    @Test
    void testjsonObjForCurrRowCorrect() throws IOException, InvalidFormatException {
        JSONObject sheetsJsonObject = new JSONObject();
        XSSFWorkbook workbook = new XSSFWorkbook(excelTestFile);


        JSONArray sheetArray = new JSONArray();
        ArrayList<String> columnNames = new ArrayList<String>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> sheetIterator = sheet.iterator();

        Row currentRow = sheetIterator.next();
        JSONObject jsonObject = new JSONObject();

        readExcelObj.jsonObjForCurrRow(currentRow, jsonObject, columnNames, sheetArray);

        sheetsJsonObject.put(workbook.getSheetName(0), sheetArray);

        assertEquals(0, sheetsJsonObject.toString().compareTo("{\"Sheet1\":[]}"));

    }

    @Test
    void testjsonObjForCurrRowIncorrect() throws IOException, InvalidFormatException {
        JSONObject sheetsJsonObject = new JSONObject();
        XSSFWorkbook workbook = new XSSFWorkbook(excelTestFile);


        JSONArray sheetArray = new JSONArray();
        ArrayList<String> columnNames = new ArrayList<String>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> sheetIterator = sheet.iterator();

        Row currentRow = sheetIterator.next();
        JSONObject jsonObject = new JSONObject();

        readExcelObj.jsonObjForCurrRow(currentRow, jsonObject, columnNames, sheetArray);

        sheetsJsonObject.put(workbook.getSheetName(0), sheetArray);

        assertFalse(sheetsJsonObject.toString().equals("{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}"));
        assertFalse(sheetsJsonObject.toString().equals("{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}"));

    }


    @Test
    void testjsonObjForCurrRowBoolCorrect() throws IOException, InvalidFormatException {
        JSONObject sheetsJsonObject = new JSONObject();
        XSSFWorkbook workbook = new XSSFWorkbook(booleanTestFile);


        JSONArray sheetArray = new JSONArray();
        ArrayList<String> columnNames = new ArrayList<String>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> sheetIterator = sheet.iterator();

        sheetIterator.next();
        sheetIterator.next();
        Row currentRow = sheetIterator.next();
        JSONObject jsonObject = new JSONObject();

        readExcelObj.jsonObjForCurrRow(currentRow, jsonObject, columnNames, sheetArray);

        sheetsJsonObject.put(workbook.getSheetName(0), sheetArray);

        System.out.println(sheetsJsonObject.toString());
        assertEquals(0, sheetsJsonObject.toString().compareTo("{\"Sheet1\":[{}]}"));

    }


}
