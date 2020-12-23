package model;

import com.aspose.cells.Workbook;
import org.json.JSONObject;

import java.io.File;


/*
Represents the Class which reads an XML File as the primary source file and performs conversions to different target
file formats and object structures.
*/
public class ReadAndOperateXML {


    //EFFECTS: reads and parses the XML file in the given file path and converts and returns an excel workbook
    public static Workbook convertExcel(String filePath) throws Exception {

        // Create a workbook
        Workbook workbook = new Workbook();

        // File Path that contains the sample XML data for mapping
        String xml = filePath;

        // Import your XML Map data starting from cell A1
        workbook.importXml(xml, "Sheet1", 0, 0);

        return workbook;
    }


    //EFFECTS: reads and parses the XML file in the given file path and converts it into a JSON structure
    public static JSONObject convertToJson(String filePath) throws Exception {
        //Converts to Excel
        Workbook w = convertExcel(filePath);
        WriteFiles.writeToExcel(w, "temp");
        String newPath = "./data/temp.xlsx";
        JSONObject obj = ReadAndOperateExcel.convertToJson(new File(newPath));
        return obj;
    }
}