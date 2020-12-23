package ui;

import com.fasterxml.jackson.databind.JsonMappingException;


import java.io.FileNotFoundException;

import java.util.Scanner;

//Driver Class for User Interface
public class MainConsole {

    //Driver Method - Main()
    public static void main(String[] args) throws Exception, JsonMappingException {

        renderTitle(); //Renders the cool project title
        try {
            Login lgn = new Login();
            if (lgn.currUser.isAccessPermission()) {
                printMenu();      //Renders the Menu
            } else {
                System.out.println("Sorry! You do not have Access to Proceed With the Application");
                lgn.quitDevTools();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: JSON file not found");
            System.out.println("Please contact technical support to fix your local backend database");
        }

    }


    //EFFECTS : Prints the Main Menu Options
    public static void printMenu() throws Exception {

        System.out.println();

        //Print the options
        System.out.println("Enter 1 to Read an Excel File");
        System.out.println("Enter 2 to Read a XML File");
        System.out.println("Enter 3 to Read a Text / CSV File");
        System.out.println("Enter 4 to Quit the Program");


        getInputForMenu(); //Function call to receive the input from the User
    }

    //EFFECTS : Gets the input to render the display / action menu
    public static void getInputForMenu() throws Exception {
        Scanner input = new Scanner(System.in);   //Declare input as scanner
        String str = input.next();
        char choice = str.charAt(0);
        performOperationsForMenu(choice);
    }

    //EFFECTS : Based on the Choice, redirects you to the appropriate display method
    public static void performOperationsForMenu(char choice) throws Exception {

        switch (choice) {
            case '1':
                DisplayExcelConsole.menuToReadExcel(); //getting the file path from the User
                break;
            case '2':
                DisplayXmlConsole.menuToReadXml();
                break;
            case '3':
                DisplayTextFileConsole.menuToReadText();
                break;
            case '4':
                System.out.println("Quitting DevTools.....");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input...redirecting to Main Menu");
                clrscr();
                printMenu();
                break;
        }
        System.out.println("--------------------------------");
        printMenu();

    }


    //EFFECTS : Skips lines to show an empty console so that it works both on MAC and Windows
    public static void clrscr() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    //EFFECTS : Renders the title in java
    public static void renderTitle() {
        System.out.println(" _       __     __                             __           ____           ______       "
                + "   "
                + "  __        __\n"
                + "| |     / /__  / /________  ____ ___  ___     / /_____     / __ \\___ _  "
                + " _/_  __/___  ____  / /____ "
                + " "
                + " / /\n"
                + "| | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\   / __/ __ \\   / / / / _ \\ | /"
                + " // / / __ \\/ __ \\/ / ___/  / / \n"
                + "| |/ |/ /  __/ / /__/ /_/ / / / / / /  __/  / /_/ /_/ /  / /_/ / "
                + " __/ |/ // / / /_/ / /_/ / (__  )  /_/  \n"
                + "|__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/   \\"
                + "__/\\____/  /_____/\\___/|___//_/  \\____/\\____/_/____/  (_)   \n"
                + "                                                                                     "
                + "                  ");


        System.out.println();
        System.out.println("Developed by Vishal for CPSC 210. This project is Open Source and is fully extensible.");
        System.out.println("2020 CopyWrite© (C) Vishal Desh");

    }

}