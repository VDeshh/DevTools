package ui;

import model.ReadAndOperateCSV;
import model.WriteFiles;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Class to stream read from a text file and display the data.
public class DisplayTextFileConsole {

    //EFFECTS: Prints the Menu for an Excel file
    public static void menuToReadText() throws IOException {
        System.out.println("Press 1 to Display the Text / CSV File on the console.");
        System.out.println("Press 2 to Convert The CSV File to an Excel File");
        System.out.println("Press 3 to Convert The CSV File to a JSON File");
        System.out.println("Press 4 to Quit DevTools");


        //Declare input as scanner
        Scanner input = new Scanner(System.in);
        char choice = input.next().charAt(0);

        //Perform Operations based on user's input
        switch (choice) {

            case '1':
                readAndDisplayTextFileMenu();
                break;
            case '2':
                menuToConvertToExcel();
                break;

            case '3':
                menuToConvertToJson();
                break;

            case '4':
                quitDevTools();
                break;

        }
    }

    // EFFECTS: Quits the application
    private static void quitDevTools() {
        System.out.println("Quitting DevTools.....");
        System.exit(0);
    }

    // EFFECTS: Menu driven to call the function that reads a Text / CSV File and prints it in the console
    public static void readAndDisplayTextFileMenu() throws FileNotFoundException {
        System.out.println("Enter the file path from which you want to read the Text File");
        Scanner scnr = new Scanner(System.in);
        String filePath = scnr.nextLine();
        readForDisplayTextFile(filePath); //creating a new file instance using the user's file path to read
    }

    // EFFECTS: Menu driven to convert the text file to a excel file
    public static void menuToConvertToExcel() throws IOException {
        System.out.println("Enter the file path from which you want to read the Text File");
        Scanner scnr2 = new Scanner(System.in);
        String path = scnr2.nextLine();
        System.out.println("Enter the Delimiter");
        String delim = scnr2.nextLine();
        System.out.println("Converting.....");
        HSSFWorkbook workbook = ReadAndOperateCSV.convertExcel(new File(path), delim);
        System.out.println("Enter the destination file path");
        String filepath = scnr2.nextLine();
        WriteFiles.writeToExcel(workbook,filepath);
        System.out.println("Hurray! Your File has been successfully converted.");
    }

    // EFFECTS: Menu driven to convert the CSV file to a JSON file
    public static void menuToConvertToJson() throws IOException {
        System.out.println("Enter the file path from which you want to read the Text File");
        Scanner scnr2 = new Scanner(System.in);
        String path = scnr2.nextLine();
        List<Map<?, ?>> mapObj = ReadAndOperateCSV.convertToJson(new File(path));
        System.out.println("Enter the file path to which you wanna download your Json File");
        path = scnr2.nextLine();
        WriteFiles.writeAsJson(mapObj, new File(path));
        System.out.println("Hurray! Your File has been successfully converted.");
    }

    //EFFECTS: Reads and displays the content of the text file
    public static void readForDisplayTextFile(String file) throws FileNotFoundException {
        Scanner scnr = new Scanner(new FileInputStream(file));
        while (scnr.hasNextLine()) {
            System.out.println(scnr.nextLine());
        }
        scnr.close();
    }

}
