package model;

import com.aspose.cells.Workbook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//Class to write to different files formats and objects into the local disk
public class WriteFiles {

    /* REQUIRES: Non-empty valid JSON Object
     * EFFECTS: Writes the given data into a JSON structure and saves it into a Json file with the given file name.
     */
    public static void writeToJsonFile(JSONObject jsonObject, String filePathAndName) throws IOException {

        //Write JSON file
        FileWriter file = new FileWriter(filePathAndName + ".json");

        file.write(jsonObject.toString());
        file.flush();


    }

    // EFFECTS: Writes the given Workbook Object (HSSFWorkbook) into a Excel file with the given file name.
    public static void writeToExcel(HSSFWorkbook workbook, String filePath) throws IOException {
        String filename = filePath + ".xls";
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }

    // EFFECTS: Writes the given Workbook Object (Aspose Workbook) into a Excel file with the given file name.
    public static void writeToExcel(Workbook workbook, String filePathName) throws Exception {
        // Save workbook
        workbook.save(filePathName + ".xlsx");

    }

    // EFFECTS: Writes the given ListObject into the given output JSON file.
    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }
}