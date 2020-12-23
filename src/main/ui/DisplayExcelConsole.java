package ui;

import model.ReadAndOperateExcel;
import model.WriteFiles;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

// Class to read and display an excel file
public class DisplayExcelConsole {

    //EFFECTS: Prints the menu for an excel file
    public static void menuToReadExcel() throws IOException, InvalidFormatException {
        System.out.println("Press 1 to Display the Excel File on the console.");
        System.out.println("Press 2 to Convert and Display the File as a JSON structure.");
        System.out.println("Press 3 to Quit DevTools.");

        // Declare input as scanner
        Scanner input = new Scanner(System.in);
        char choice = input.next().charAt(0);

        // Perform operations based on user's input
        switch (choice) {

            case '1':
                readAndDisplayMenu(); // Read and display the excel file
                break;

            case '2':
                convertToJsonMenu(); // Convert and save the excel file to a JSON file
                break;

            case '3':
                quitDevToolsMenu(); // Quit DevTools
                break;
        }
    }

    // EFFECTS: Menu driven to call the function that reads every sheet in an Excel File and prints it in the console
    public static void readAndDisplayMenu() throws IOException {
        System.out.println("Enter the file path from which you want to read the Excel File");
        Scanner scnr = new Scanner(System.in);
        String filePath = scnr.nextLine();
        readForDisplay(new File(filePath)); //creating a new file instance using the user's file path to read
    }

    // EFFECTS: Menu driven to call the function that converts an Excel File to a JSON file
    public static void convertToJsonMenu() throws IOException, InvalidFormatException {
        System.out.println("Enter the file path from which you want to read the Excel File");
        Scanner scnr2 = new Scanner(System.in);
        String filePath2 = scnr2.nextLine();
        File file2 = new File(filePath2); //creating a new file instance using the user's file path
        JSONObject obj = ReadAndOperateExcel.convertToJson(file2);
        System.out.println("Your Json Output");
        System.out.println(obj.toString());
        String defaultPath = "./data/Your Output";
        WriteFiles.writeToJsonFile(obj,defaultPath);
    }

    // EFFECTS: Quits the application
    private static void quitDevToolsMenu() {
        System.out.println("Quitting DevTools.....");
        System.exit(0);
    }

    /*
     * REQUIRES: File is a non-empty excel File
     * EFFECTS: reads every sheet in an Excel File and prints it in the console
     */
    public static void readForDisplay(File file) throws IOException {

        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file

        //creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file

        while (itr.hasNext()) {
            Row row = itr.next();
            //iterating over each column
            Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = (Cell) cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                        System.out.print(cell.getStringCellValue() + "\t\t\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                        System.out.print(cell.getNumericCellValue() + "\t\t\t");
                        break;
                    default:
                }
            }
            System.out.println("");
        }

    }


}
