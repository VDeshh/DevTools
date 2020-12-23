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
import java.io.IOException;
import java.util.List;


/*
This Class Represents the Graphical User Interface (GUI) for the WorkRoom User Display Page where
users can view the subset of all the users' name and their usernames registered in the DevTools WorkRoom
 */
public class WorkRoomUserDisplayUI extends JFrame {

    private static final String JSON_STORE = "./data/workroom.json";
    public static User currUser;
    private static WorkRoom workRoom;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton closeWindowButton;

    // MODIFIES: this
    // EFFECTS: Constructs and initializes all the data members of a WorkRoomUserDisplayUI object
    public WorkRoomUserDisplayUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);

        workRoom = new WorkRoom("Vishal's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currUser = new User("xyz", "xyz", "xyz", "xyz", false);
        textArea1.setEditable(false);
        getUsersFromWorkroom(textArea1);
        this.pack();
        setLocationRelativeTo(null);
        backBtnClickListener();
    }

    // MODIFIES: this
    // EFFECTS: Fetches all the users from the DevTools WorkRoom
    public static void getUsersFromWorkroom(JTextArea txtArea) {
        loadWorkRoom();
        List<User> users = workRoom.getUsers();

        for (User u : users) {
            txtArea.append(u.getName() + "\t" + u.getUserName() + "\n");
        }

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

    // MODIFIES: this
    // EFFECTS: Checks if back button is clicked and redirects to previous frame
    private void backBtnClickListener() {
        closeWindowButton.addActionListener(new ActionListener() {
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
                setVisible(false);
            }

        });
    }

}
