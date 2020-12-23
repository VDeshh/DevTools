package ui;

import model.User;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
This Class Represents the Graphical User Interface (GUI) for the SignUp Page where
users can sign up to devtools and add their account to the DevTools WorkRoom
 */
public class SignUpPageUI extends JFrame {
    private static final String JSON_STORE = "./data/workroom.json";
    public static User currUser;
    private static WorkRoom workRoom;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private JPanel panel1;
    private JLabel title;
    private JFormattedTextField nameField;
    private JLabel nameLabel;
    private JLabel userNameLabel;
    private JButton backButton;
    private JButton signUpButton;
    private JButton quitButton;
    private JCheckBox showPasswordCheckBox;
    private JButton resetBtn;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JTextField operatingSystemField;
    private JComboBox accessPermissionComboBox;


    // MODIFIES: this
    // EFFECTS: Constructs and initializes all the data members of a SignUpPageUI object
    public SignUpPageUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        setLocationRelativeTo(null);

        workRoom = new WorkRoom("Vishal's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currUser = new User("xyz", "xyz", "xyz", "xyz", false);
        accessPermissionComboBox.addItem("true");
        accessPermissionComboBox.addItem("false");
        resetBtnClickListener();
        backBtnClickListener();
        signUpButtonListener();
        quitBtnClickListener();

    }

    // MODIFIES: this
    // EFFECTS: saves the workroom to file
    private static void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            //System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private static void loadWorkRoom() {
        try {
            workRoom = jsonReader.read();
           // System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Checks if the Reset button is pressed and resets all the fields
    private void resetBtnClickListener() {
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlaySounds.playYellowBtnSound();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
                nameField.setText("");
                userNameField.setText("");
                passwordField.setText("");
                operatingSystemField.setText("");

            }

        });
    }

    // EFFECTS: Checks if the quit button is pressed and quits the application and
    //          calls a helper to play a sound while quitting
    private void quitBtnClickListener() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlaySounds.playQuitBtnClickSound();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
                int input = JOptionPane.showConfirmDialog(
                        null, "Do you want to Quit DevTools?", "Exit DevTools Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (input == 0) {
                    quitBtnSoundHelper();
                    System.exit(0);
                }
            }

        });
    }

    // EFFECTS: Quits the application if the Quit button is pressed and calls a helper to play a sound while quitting.
    private void quitBtnSoundHelper() {
        try {
            PlaySounds.playQuitBtnSound();
            Thread.sleep(1000);
            setVisible(false);
            Thread.sleep(2000);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: Checks if back button is clicked and redirects to previous frame
    private void backBtnClickListener() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlaySounds.playBackBtnSound();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
                JFrame frame2 = new HomePageUI("DevTools!");
                frame2.setVisible(true);
                setVisible(false);
            }

        });
    }

    // MODIFIES: this
    // EFFECTS: Checks if the sign-up button is pressed and checks if the the user name is already taken
    //          and if not, it calls the helper function to proceed
    private void signUpButtonListener() {

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWorkRoom();
                if (!workRoom.checkUserName(userNameField.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "Your Username has already been taken, Please enter a new Username");

                } else {

                    checkFieldEmptyAndSignUpAccordingly();

                }
            }

        });

    }

    // MODIFIES: this
    // EFFECTS: Helper function to proceed with sign-up and redirect to Home-Page UI after sign-up is successful.
    private void checkFieldEmptyAndSignUpAccordingly() {
        if (!nameField.getText().equals("") && !(userNameField.getText().equals(""))
                && !(passwordField.getText().equals("")) && !(operatingSystemField.getText().equals(""))) {
            workRoom.addUser(new User(nameField.getText(), userNameField.getText(), passwordField.getText(),
                    operatingSystemField.getText(),
                    Boolean.parseBoolean((String) accessPermissionComboBox.getSelectedItem())));
            saveWorkRoom();
            JOptionPane.showMessageDialog(null,
                    "Thank You for Signing Up with DevTools! Now Redirecting you to the Main Menu.");
            JFrame frame2 = new HomePageUI("DevTools!");
            frame2.setVisible(true);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Missing Fields Please Check");
        }
    }
}
