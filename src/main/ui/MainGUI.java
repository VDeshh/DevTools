package ui;

import javax.swing.*;

/*
This Class represents the Driver Class for the the DevTools Application with a Graphical User Interface
 */
public class MainGUI {

    // EFFECTS: Driver function to run the Graphical User Interface (GUI)
    public static void main(String[] args) {
        JFrame frame = new HomePageUI("DevTools");
        frame.setVisible(true);
    }

}
