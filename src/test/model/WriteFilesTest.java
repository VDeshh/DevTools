package model;

import com.aspose.cells.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
This class represents the jUnit tests for the WriteFiles Model Class methods
 */
public class WriteFilesTest {

    WriteFiles wF;

    @BeforeEach
    void setup() {
        wF = new WriteFiles();
    }

    //This Test is for testing if HSSF Workbook is written in an Excel File
    @Test
    void testWriteToExcel() throws Exception {
        HSSFWorkbook wb = ReadAndOperateCSV.convertExcel(new File("./data/gdpdemo.txt"), "\t");
        wF.writeToExcel(wb,"./data/NewExcelFile");

        File file = new File("./data/NewExcelFile.xls");
        FileFormatInfo fmt = FileFormatUtil.detectFileFormat(String.valueOf(file));
        assertEquals(fmt.getFileFormatType(), FileFormatType.EXCEL_97_TO_2003);
        file.delete();
    }


    //This Test is for testing if an JSON is written in an JSON File
    @Test
    void testWriteToJson() throws Exception {
        List<Map<?, ?>> jsonObjMap = ReadAndOperateCSV.convertToJson(new File("./data/SampleData.csv"));
        File file = new File("./data/JsonTest2.json");
        wF.writeAsJson(jsonObjMap, file);

        String jsonString = new String(Files.readAllBytes(Paths.get("./data/JsonTest2.json")));

        String jsonOutput = "[{\"OrderDate\":\"1/6/19\",\"Region\":\"East\"," +
                "\"Rep\":\"Jones\",\"Item\":\"Pencil\",\"Units\":\"95\",\"Unit Cost\":" +
                "\" 1.99 \",\"Total\":\" 189.05 \"},{\"OrderDate\":\"1/23/19\",\"Region\"" +
                ":\"Central\",\"Rep\":\"Kivell\",\"Item\":\"Binder\",\"Units\":\"50\"," +
                "\"Unit Cost\":\" 19.99 \",\"Total\":\" 999.50 \"},{\"OrderDate\":\"2/9/19\"" +
                ",\"Region\":\"Central\",\"Rep\":\"Jardine\",\"Item\":\"Pencil\",\"Units\":\"" +
                "36\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 179.64 \"},{\"OrderDate\":\"2/26/19\"" +
                ",\"Region\":\"Central\",\"Rep\":\"Gill\",\"Item\":\"Pen\",\"Units\":\"27\"," +
                "\"Unit Cost\":\" 19.99 \",\"Total\":\" 539.73 \"},{\"OrderDate\":\"3/15/19\"," +
                "\"Region\":\"West\",\"Rep\":\"Sorvino\",\"Item\":\"Pencil\",\"Units\":\"56\"," +
                "\"Unit Cost\":\" 2.99 \",\"Total\":\" 167.44 \"},{\"OrderDate\":\"4/1/19\"," +
                "\"Region\":\"East\",\"Rep\":\"Jones\",\"Item\":\"Binder\",\"Units\":\"60\"," +
                "\"Unit Cost\":\" 4.99 \",\"Total\":\" 299.40 \"},{\"OrderDate\":\"4/18/19\"" +
                ",\"Region\":\"Central\",\"Rep\":\"Andrews\",\"Item\":\"Pencil\",\"Units\":" +
                "\"75\",\"Unit Cost\":\" 1.99 \",\"Total\":\" 149.25 \"},{\"OrderDate\":\"" +
                "5/5/19\",\"Region\":\"Central\",\"Rep\":\"Jardine\",\"Item\":\"Pencil\",\"Units\":" +
                "\"90\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 449.10 \"},{\"OrderDate\":\"5/22/19\"," +
                "\"Region\":\"West\",\"Rep\":\"Thompson\",\"Item\":\"Pencil\",\"Units\":\"32\",\"Unit " +
                "Cost\":\" 1.99 \",\"Total\":\" 63.68 \"},{\"OrderDate\":\"6/8/19\",\"Region\":\"East" +
                "\",\"Rep\":\"Jones\",\"Item\":\"Binder\",\"Units\":\"60\",\"Unit Cost\":\" 8.99 \"," +
                "\"Total\":\" 539.40 \"},{\"OrderDate\":\"6/25/19\",\"Region\":\"Central\",\"Rep\":" +
                "\"Morgan\",\"Item\":\"Pencil\",\"Units\":\"90\",\"Unit Cost\":\" 4.99 \",\"Total\":" +
                "\" 449.10 \"},{\"OrderDate\":\"7/12/19\",\"Region\":\"East\",\"Rep\":\"Howard\",\"" +
                "Item\":\"Binder\",\"Units\":\"29\",\"Unit Cost\":\" 1.99 \",\"Total\":\" 57.71 \"}," +
                "{\"OrderDate\":\"7/29/19\",\"Region\":\"East\",\"Rep\":\"Parent\",\"Item\":\"Binder" +
                "\",\"Units\":\"81\",\"Unit Cost\":\" 19.99 \",\"Total\":\" 1,619.19 \"},{\"OrderDate" +
                "\":\"8/15/19\",\"Region\":\"East\",\"Rep\":\"Jones\",\"Item\":\"Pencil\",\"Units\":" +
                "\"35\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 174.65 \"},{\"OrderDate\":\"9/1/19\",\"" +
                "Region\":\"Central\",\"Rep\":\"Smith\",\"Item\":\"Desk\",\"Units\":\"2\",\"Unit Cost\"" +
                ":\" 125.00 \",\"Total\":\" 250.00 \"},{\"OrderDate\":\"9/18/19\",\"Region\":\"East\"," +
                "\"Rep\":\"Jones\",\"Item\":\"Pen Set\",\"Units\":\"16\",\"Unit Cost\":\" 15.99 \"," +
                "\"Total\":\" 255.84 \"},{\"OrderDate\":\"10/5/19\",\"Region\":\"Central\",\"Rep\":\"" +
                "Morgan\",\"Item\":\"Binder\",\"Units\":\"28\",\"Unit Cost\":\" 8.99 \",\"Total\":\" " +
                "251.72 \"},{\"OrderDate\":\"10/22/19\",\"Region\":\"East\",\"Rep\":\"Jones\",\"Item\":" +
                "\"Pen\",\"Units\":\"64\",\"Unit Cost\":\" 8.99 \",\"Total\":\" 575.36 \"},{\"OrderDate" +
                "\":\"11/8/19\",\"Region\":\"East\",\"Rep\":\"Parent\",\"Item\":\"Pen\",\"Units\":\"15\"" +
                ",\"Unit Cost\":\" 19.99 \",\"Total\":\" 299.85 \"},{\"OrderDate\":\"11/25/19\",\"Region" +
                "\":\"Central\",\"Rep\":\"Kivell\",\"Item\":\"Pen Set\",\"Units\":\"96\",\"Unit Cost\":\" " +
                "4.99 \",\"Total\":\" 479.04 \"},{\"OrderDate\":\"12/12/19\",\"Region\":\"" +
                "Central\",\"Rep\":\"Smith\",\"Item\":\"Pencil\",\"Units\":\"67\",\"Unit Cost\"" +
                ":\" 1.29 \",\"Total\":\" 86.43 \"},{\"OrderDate\":\"12/29/19\",\"Region\":\"East\"" +
                ",\"Rep\":\"Parent\",\"Item\":\"Pen Set\",\"Units\":\"74\",\"Unit Cost\":\" 15.99 " +
                "\",\"Total\":\" 1,183.26 \"},{\"OrderDate\":\"1/15/20\",\"Region\":\"Central\",\"" +
                "Rep\":\"Gill\",\"Item\":\"Binder\",\"Units\":\"46\",\"Unit Cost\":\" 8.99 \",\"Total\":" +
                "\" 413.54 \"},{\"OrderDate\":\"2/1/20\",\"Region\":\"Central\",\"Rep\":\"Smith\"," +
                "\"Item\":\"Binder\",\"Units\":\"87\",\"Unit Cost\":\" 15.00 \",\"Total\":\" 1,305.00 \"}," +
                "{\"OrderDate\":\"2/18/20\",\"Region\":\"East\",\"Rep\":\"Jones\",\"Item\":\"Binder\"," +
                "\"Units\":\"4\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 19.96 \"}," +
                "{\"OrderDate\":\"3/7/20\",\"Region\":\"West\",\"Rep\":\"Sorvino\",\"Item\":" +
                "\"Binder\",\"Units\":\"7\",\"Unit Cost\":\" 19.99 \",\"Total\":\" 139.93 \"}," +
                "{\"OrderDate\":\"3/24/20\",\"Region\":\"Central\",\"Rep\":\"Jardine\"," +
                "\"Item\":\"Pen Set\",\"Units\":\"50\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 249.50 \"}," +
                "{\"OrderDate\":\"4/10/20\",\"Region\":\"Central\",\"Rep\":\"Andrews\"," +
                "\"Item\":\"Pencil\",\"Units\":\"66\",\"Unit Cost\":\" 1.99 \",\"Total\":\" 131.34 \"}," +
                "{\"OrderDate\":\"4/27/20\",\"Region\":\"East\",\"Rep\":\"Howard\",\"Item\":" +
                "\"Pen\",\"Units\":\"96\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 479.04 \"}," +
                "{\"OrderDate\":\"5/14/20\",\"Region\":\"Central\",\"Rep\":\"Gill\",\"Item\":" +
                "\"Pencil\",\"Units\":\"53\",\"Unit Cost\":\" 1.29 \",\"Total\":\" 68.37 \"}," +
                "{\"OrderDate\":\"5/31/20\",\"Region\":\"Central\",\"Rep\":\"Gill\",\"Item\":" +
                "\"Binder\",\"Units\":\"80\",\"Unit Cost\":\" 8.99 \",\"Total\":\" 719.20 \"}," +
                "{\"OrderDate\":\"6/17/20\",\"Region\":\"Central\",\"Rep\":\"Kivell\",\"Item\":" +
                "\"Desk\",\"Units\":\"5\",\"Unit Cost\":\" 125.00 \",\"Total\":\" 625.00 \"}," +
                "{\"OrderDate\":\"7/4/20\",\"Region\":\"East\",\"Rep\":\"Jones\",\"Item\":" +
                "\"Pen Set\",\"Units\":\"62\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 309.38 \"}," +
                "{\"OrderDate\":\"7/21/20\",\"Region\":\"Central\",\"Rep\":\"Morgan\",\"Item\":" +
                "\"Pen Set\",\"Units\":\"55\",\"Unit Cost\":\" 12.49 \",\"Total\":\" 686.95 \"}," +
                "{\"OrderDate\":\"8/7/20\",\"Region\":\"Central\",\"Rep\":\"Kivell\",\"Item\":" +
                "\"Pen Set\",\"Units\":\"42\",\"Unit Cost\":\" 23.95 \",\"Total\":\" 1,005.90 \"}," +
                "{\"OrderDate\":\"8/24/20\",\"Region\":\"West\",\"Rep\":\"Sorvino\",\"Item\":" +
                "\"Desk\",\"Units\":\"3\",\"Unit Cost\":\" 275.00 \",\"Total\":\" 825.00 \"}," +
                "{\"OrderDate\":\"9/10/20\",\"Region\":\"Central\",\"Rep\":\"Gill\",\"Item\":" +
                "\"Pencil\",\"Units\":\"7\",\"Unit Cost\":\" 1.29 \",\"Total\":\" 9.03 \"}," +
                "{\"OrderDate\":\"9/27/20\",\"Region\":\"West\",\"Rep\":\"Sorvino\",\"Item\":" +
                "\"Pen\",\"Units\":\"76\",\"Unit Cost\":\" 1.99 \",\"Total\":\" 151.24 \"}," +
                "{\"OrderDate\":\"10/14/20\",\"Region\":\"West\",\"Rep\":\"Thompson\",\"Item\":" +
                "\"Binder\",\"Units\":\"57\",\"Unit Cost\":\" 19.99 \",\"Total\":\" 1,139.43 \"}," +
                "{\"OrderDate\":\"10/31/20\",\"Region\":\"Central\",\"Rep\":\"Andrews\",\"Item\":" +
                "\"Pencil\",\"Units\":\"14\",\"Unit Cost\":\" 1.29 \",\"Total\":\" 18.06 \"}," +
                "{\"OrderDate\":\"11/17/20\",\"Region\":\"Central\",\"Rep\":\"Jardine\",\"Item\":" +
                "\"Binder\",\"Units\":\"11\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 54.89 \"}," +
                "{\"OrderDate\":\"12/4/20\",\"Region\":\"Central\",\"Rep\":\"Jardine\",\"Item\":" +
                "\"Binder\",\"Units\":\"94\",\"Unit Cost\":\" 19.99 \",\"Total\":\" 1,879.06 \"}," +
                "{\"OrderDate\":\"12/21/20\",\"Region\":\"Central\",\"Rep\":\"Andrews\",\"Item\":" +
                "\"Binder\",\"Units\":\"28\",\"Unit Cost\":\" 4.99 \",\"Total\":\" 139.72 \"}]";

        String jsonWrongOutput = "{[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        assertTrue(jsonOutput.equals(jsonString));
        assertFalse(jsonWrongOutput.equals(jsonString));
        file.delete();
    }

    //This Test is for testing if Aspose Workbook is written in an Excel File
    @Test
    void testWriteToExcelAspose() throws Exception {
        Workbook wb = ReadAndOperateXML.convertExcel("./data/XMLFile.xml");
        wF.writeToExcel(wb, "./data/NewExcelFile");
        File file = new File("./data/NewExcelFile.xlsx");
        FileFormatInfo fmt = FileFormatUtil.detectFileFormat(String.valueOf(file));
        assertEquals(fmt.getFileFormatType(), FileFormatType.XLSX);
        file.delete();
    }

    //This Test is for testing if a List Object is written in an JSON File
    @Test
    void testWriteAsJson() throws Exception {
        JSONObject obj = ReadAndOperateXML.convertToJson("./data/XMLFile.xml");
        JSONObject obj2 = ReadAndOperateXML.convertToJson("./data/XMLFile.xml");
        try {
            wF.writeToJsonFile(obj, "./data/JsonTest");
        } catch (Exception e) {
            fail();
        }

        try {
            wF.writeToJsonFile(obj, "./data/--12^//JsonTest");
            fail();
        } catch (IOException e) {
            //pass
        }

        File file = new File("./data/JsonTest.json");
        String jsonString = new String(Files.readAllBytes(Paths.get("./data/JsonTest.json")));

        String jsonOutput = "{\"Sheet1\":[{\"firstname\":\"Harry\",\"subject\":\"Math\"," +
                "\"id\":\"101\",\"marks\":\"83\",\"lastname\":\"Smith\"},{\"firstname\":\"Chip\"," +
                "\"subject\":\"Chemistry\",\"id\":\"102\",\"marks\":\"60\",\"lastname\":\"Wilson\"}," +
                "{\"firstname\":\"John\",\"subject\":" +
                "\"English\",\"id\":\"103\",\"marks\":\"70\",\"lastname\":\"Lee\"}" +
                ",{\"firstname\":\"Jitesh\",\"subject\":\"Physics\",\"id\":\"104\",\"marks\":" +
                "\"76\",\"lastname\":\"Singh\"}],\"Evaluation Warning\":[{}]}";

        String jsonWrongOutput = "{[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        assertTrue(jsonOutput.equals(jsonString));
        assertFalse(jsonWrongOutput.equals(jsonString));
        file.delete();
    }

}