package model;

import com.fasterxml.jackson.databind.MappingIterator;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
Represents the Class which reads a CSV File as the primary source file and performs conversions to different target
file formats and object structures.
*/
public class ReadAndOperateCSV {

    /* REQUIRES: Valid delimiter - (eg: "\t")
     * EFFECTS: reads the CSV file and creates a workbook object
     */
    public static HSSFWorkbook convertExcel(File dir, String delim) throws IOException {

        //Creates a Workbook instance
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FirstSheet");


        int rowNo = 0;
        int columnNo = 0;

        Scanner scanner = new Scanner(dir);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Gets the row and if it doesn't exist it will create it.
            sheet.getRow(rowNo);
            Row tempRow;
            tempRow = sheet.createRow(rowNo);

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(delim);
            // While there is more text to get it will loop.
            while (lineScanner.hasNext()) {
                // Gets the cell in that row and if it is null then it will be created.
                Cell tempCell = tempRow.getCell(columnNo, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String output = lineScanner.next();
                // Write the output to that cell.
                tempCell.setCellValue(new HSSFRichTextString(output));
                columnNo++;
            }

            // Resets the column count for the new row.
            columnNo = 0;
            rowNo++;
        }
        return workbook;

    }

    // EFFECTS: reads the CSV file and creates a Map object which can be parsed to a JSON structure
    public static List<Map<?, ?>> convertToJson(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }


}