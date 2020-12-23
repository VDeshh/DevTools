package model;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;

import model.ReadAndOperateXML;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


/*
This class represents the jUnit tests for the ReadAndOperateXML Model Class methods
 */
public class ReadAndOperateXMLTest {

    String xmlTestFile;
    ReadAndOperateXML testObj;

    @BeforeEach
    void setup() {
        xmlTestFile = "./data/XMLFile.xml";
        testObj = new ReadAndOperateXML();

    }

    @Test
    void testConvertExcel() throws Exception {

        Workbook wb = testObj.convertExcel(xmlTestFile);


        Cells cells = wb.getWorksheets().get(0).getCells();

        //Put a string into a cell
        Cell cell1 = cells.get(0, 0);

        assertTrue(cell1.getValue().toString().equals("id"));
        assertFalse(cell1.getValue().toString().equals("StudentID"));


        Cell cell2 = cells.get(0, 1);
        assertTrue(cell2.getValue().toString().equals("firstname"));
        assertFalse(cell2.getValue().toString().equals("StudentID"));

        Cell cell3 = cells.get(0, 2);
        assertTrue(cell3.getValue().toString().equals("lastname"));
        assertFalse(cell3.getValue().toString().equals("StudentID"));

    }

    @Test
    void testConvertToJson() throws Exception {
        String json = testObj.convertToJson(xmlTestFile).toString();

        String jsonOutput = "{\"Sheet1\":[{\"firstname\":\"Harry\",\"subject\":\"Math\",\"id\":\"101\"," +
                "\"marks\":\"83\",\"lastname\":\"Smith\"}," +
                "{\"firstname\":\"Chip\",\"subject\":\"Chemistry\",\"id\":\"102\"," +
                "\"marks\":\"60\",\"lastname\":\"Wilson\"}," +
                "{\"firstname\":\"John\",\"subject\":\"English\",\"id\":\"103\",\"marks\":\"70\"," +
                "\"lastname\":\"Lee\"},{\"firstname\":\"Jitesh\",\"subject\":\"Physics\"," +
                "\"id\":\"104\",\"marks\":\"76\",\"lastname\":\"Singh\"}],\"Evaluation Warning\":[{}]}";

        String jsonWrongOutput = "{[{\"Height\":\"6'3\\\"\",\"Name \":\"John \",\"Age\":23}," +
                "{\"Height\":\"7'\",\"Name \":\"Matt\",\"Age\":25}," +
                "{\"Height\":\"6'11\\\"\",\"Name \":\"Phillip\",\"Age\":30}," +
                "{\"Height\":\"5'3\\\"\",\"Name \":\"Aiden\",\"Age\":15}]}";

        assertTrue(jsonOutput.equals(json));
        assertFalse(jsonWrongOutput.equals(json));

    }
}
