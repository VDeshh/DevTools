package ui;

import model.User;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


/*
This Class Represents the Graphical User Interface (GUI) for the DevTools Login Page
 */
public class LoginPageUI extends JFrame {
    private static final String JSON_STORE = "./data/workroom.json";
    public static User currUser;
    private static WorkRoom workRoom;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private JLabel title;
    private JPanel panel1;
    private JFormattedTextField userNameField;
    private JPasswordField passwordField;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JButton backButton;
    private JButton loginButton;
    private JButton quitButton;
    private JCheckBox showPasswordCheckBox;
    private JButton resetBtn;
    private JButton seeRegisteredUserListButton;


    // MODIFIES: this
    // EFFECTS: Constructs and initializes all the data members of a LoginPageUI object
    public LoginPageUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        loginBtnClickListener();
        loginBtnMouseListener();
        quitBtnClickListener();
        quitBtnMouseListener();
        backBtnClickListener();
        backBtnMouseListener();
        resetBtnClickListener();
        showPasswordListener();
        registeredUserListBtnClickListener();
        setLocationRelativeTo(null);

        workRoom = new WorkRoom("Vishal's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currUser = new User("xyz", "xyz", "xyz", "xyz", false);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private static void loadWorkRoom() {
        try {
            workRoom = jsonReader.read();
            //System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Checks if the Password is valid and user can login if the condition is satisfied
    private void testPassword(User u) {
        if (u.getPassWord().compareTo(passwordField.getText()) == 0) {
            currUser = u;
            if (u.isAccessPermission()) {
                try {
                    PlaySounds.playClickBtnSound();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                JFrame frame = new ConverterPageUI("DevTools HUB", u);
                frame.setVisible(true);
            } else {
                JOptionPane.showConfirmDialog(
                        null, "You do not have access to operate DevTools",
                        "Access Denied", JOptionPane.DEFAULT_OPTION);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Invalid Password");

        }
    }


    // EFFECTS: Checks for the password entered by the user
    private void showPasswordListener() {
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
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
                userNameField.setText("");
                passwordField.setText("");
            }

        });
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

    // EFFECTS: Checks if the RegisteredUser button is pressed and opens the WorkRoom Display UI window
    private void registeredUserListBtnClickListener() {

        seeRegisteredUserListButton.addActionListener(new ActionListener() {
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
                JFrame frame = new WorkRoomUserDisplayUI("DevTools");
                frame.setVisible(true);
            }

        });

    }

    // MODIFIES: this
    // EFFECTS: Checks if the Login button is pressed and checks if the the user name is valid
    //          and calls testPassword Helper
    private void loginBtnClickListener() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWorkRoom();

                if (workRoom.getUserWithUserName(userNameField.getText()) != null) {
                    User u = workRoom.getUserWithUserName(userNameField.getText());
                    testPassword(u);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid UserName");
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void loginBtnMouseListener() {

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loginButton.setBackground(Color.GREEN);
                loginButton.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(11, 135, 10, temp);
                loginButton.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                loginButton.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // EFFECTS: Quits the application if the Quit button is pressed and calls a helper to play a sound while quitting.
    private void quitBtnMouseListener() {

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                quitButton.setBackground(Color.RED);
                quitButton.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(193, 4, 1, temp);
                quitButton.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                quitButton.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // EFFECTS: Helper function to play a sound while quitting the application.
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
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void backBtnMouseListener() {

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backButton.setBackground(Color.BLUE);
                backButton.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(21, 41, 117, temp);
                backButton.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                backButton.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }
}
