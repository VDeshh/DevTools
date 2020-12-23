package ui;

import model.User;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// This class represents the UI for the User to Login / Signup to DevTools.
public class Login {

    private static final String JSON_STORE = "./data/workroom.json";
    public static User currUser;
    private static WorkRoom workRoom;
    private static Scanner input;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;

    // EFFECTS: constructs workroom and runs application
    public Login() throws Exception {
        input = new Scanner(System.in);
        workRoom = new WorkRoom("Vishal's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currUser = new User("xyz","xyz","xyz","xyz",false);
        printLoginMenu();
    }

    // EFFECTS: Display the Login Menu
    public static void printLoginMenu() throws Exception {
        Scanner input = new Scanner(System.in);   //Declare input as scanner
        System.out.println("LOGIN OR CREATE AN ACCOUNT");
        System.out.println();

        System.out.println("Do want to login to your existing id? (Y/N)");
        String str = input.next().toLowerCase();
        if (str.compareTo("y") == 0) {
            proceedWithLogin();
        } else {

            System.out.println("Do want to proceed with signup? (Y/N)");
            str = input.next().toLowerCase();
            if (str.compareTo("y") == 0) {
                proceedWithSignUp();
            } else {
                System.out.println("Sorry! Unfortunately you cannot proceed with the application without login");
                quitDevTools();
            }
        }

    }

    // EFFECTS: Menu to Login in and check with the backend models for authentication
    public static void proceedWithLogin() throws Exception {
        loadWorkRoom();
        System.out.println("Enter your UserName");
        String userName = input.nextLine();
        if (workRoom.getUserWithUserName(userName) != null) {
            User u = workRoom.getUserWithUserName(userName);
            System.out.println("Enter your Password");
            String passWord = input.nextLine();
            if (u.getPassWord().compareTo(passWord) == 0) {
                System.out.println("Login Successful");
                currUser = u;

            } else {
                System.out.println("Invalid Password");
                printLoginMenu();
            }
        } else {
            System.out.println("Invalid UserName");
            printLoginMenu();
        }
    }

    // EFFECTS: Menu to Sign-up and check with the backend models for authentication
    public static void proceedWithSignUp() throws Exception {
        loadWorkRoom();
        Scanner input = new Scanner(System.in);   //Declare input as scanner
        System.out.println("Enter your Name");
        String name = input.nextLine();
        System.out.println("Enter your UserName");
        String userName = input.nextLine(); //case sensitive
        while (!workRoom.checkUserName(userName)) {
            System.out.println("Your Username has already been taken, Please enter a new Username");
            userName = input.nextLine(); //case sensitive
        }
        System.out.println("Enter your Password");
        String passWord = input.nextLine(); //case sensitive
        System.out.println("Enter your Operating System");
        String operatingSystem = input.nextLine();
        System.out.println("Enter your access Permission (Child Safety)");
        Boolean accessPermission = input.nextBoolean();

        workRoom.addUser(new User(name, userName, passWord, operatingSystem, accessPermission));
        saveWorkRoom();
        printLoginMenu();
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private static void loadWorkRoom() {
        try {
            workRoom = jsonReader.read();
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Quits the application
    public static void quitDevTools() {
        System.out.println("Quitting DevTools.....");
        System.exit(0);
    }

    // EFFECTS: saves the workroom to file
    private static void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the names of the Users in workroom to the console
    public void printUsers() {
        List<User> users = workRoom.getUsers();
        int index = 1;
        for (User u : users) {
            System.out.println("User " + index + ": " + u.getName());
            index++;
        }
    }


}
