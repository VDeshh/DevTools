package ui;

import com.aspose.cells.Workbook;
import model.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;

/*
This Class Represents the Graphical User Interface (GUI) for the DevTools Hub where
users can convert files and file structures.
 */
public class ConverterPageUI extends JFrame {
    public String delim;
    private JTextArea hiUserTextArea;
    private JButton browseButton1;
    private JTextArea textArea1;
    private JLabel sourceFileTypeLabel;
    private JPanel panel1;
    private JComboBox<String> sourceFileComboBox;
    private JComboBox<String> targetFileComboBox;
    private JComboBox<String> delimComboBox;
    private JTextArea textArea2;
    private JButton browseButton2;
    private JButton convertButton;
    private JButton quitButton;
    private JButton backToHomePageButton;
    private JLabel delimiterLabel;
    private String sourceFileType;
    private String targetFileType;

    // MODIFIES: this
    // EFFECTS: Constructs and initializes all the data members of a ConverterPageUI object
    public ConverterPageUI(String title, User user) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        hiUserTextAreaInitialize(user);
        sourceFileComboBox.setEditable(true);
        targetFileComboBox.setEditable(true);
        delimComboBox.setEditable(true);
        targetFileType = new String();
        sourceFileType = new String();
        delim = new String();
        constructSourceFileComboBox();
        targetFileComboBoxListener();
        sourceFileComboBoxListener();
        browseButton1ClickListener();
        browseButton2ClickListener();
        delimComboBoxListener();
        quitBtnClickListener();
        convertButtonClickListener();
        setLocationRelativeTo(null);
        backToHomePageButtonListener();
        this.pack();


    }

    // MODIFIES: this
    // EFFECTS: Checks if back button is clicked and redirects to previous frame
    private void backToHomePageButtonListener() {
        backToHomePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int input = JOptionPane.showConfirmDialog(
                        null, "Do you want to go back DevTools Home?",
                        "Redirect To DevTools Home Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (input == 0) {
                    JFrame frame2 = new HomePageUI("DevTools!");
                    frame2.setVisible(true);
                    setVisible(false);
                }

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: Checks if Convert button is clicked and calls a helper function
    private void convertButtonClickListener() {

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkSourceFile();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


    }

    // EFFECTS: Helper function to call the appropriate convertTo function
    private void checkSourceFile() throws Exception {
        switch (sourceFileType) {
            case "excel":
                convertExcelToX(targetFileType);
                break;
            case "xml":
                convertXmlToX(targetFileType);
                break;
            case "text":
                convertTextToX(targetFileType);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Please check the entry again");
        }
    }

    // MODIFIES: this
    // EFFECTS: Constructs the Source File ComboBox
    private void constructSourceFileComboBox() {
        sourceFileComboBox.addItem("Excel File");
        sourceFileComboBox.addItem("Text File");
        sourceFileComboBox.addItem("XML File");
    }


    // MODIFIES: this
    // EFFECTS: Displays and welcomes the current user's name
    private void hiUserTextAreaInitialize(User user) {
        hiUserTextArea.setEditable(false);
        hiUserTextArea.append("Hi " + user.getName() + "!");
    }

    // MODIFIES: this
    // EFFECTS: Checks if the browse button is clicked and adds the file path to the text area
    private void browseButton1ClickListener() {
        browseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textArea1.append(selectedFile.getAbsolutePath());
                }
                pack();
            }
        });


    }

    // MODIFIES: this
    // EFFECTS: Checks if the 2nd browse button is clicked and adds the file path to the text area
    private void browseButton2ClickListener() {
        browseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    textArea2.append(selectedFile.getAbsolutePath());
                }
                pack();
            }
        });


    }

    // MODIFIES: this
    // EFFECTS: Listener for the source file combobox which calls a helper function when selected.
    public void sourceFileComboBoxListener() {
        sourceFileComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                assignTargetComboBoxItemsAccordingly();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: Based on the input in the sourceFile combo box the target file combo box elements are added.
    public void assignTargetComboBoxItemsAccordingly() {
        String selectedBook = "0";
        String str = (String) sourceFileComboBox.getSelectedItem();
        if (str != null) {
            selectedBook = str;
        }
        targetFileComboBox.removeAllItems();
        if (selectedBook.equals("Excel File")) {
            sourceFileType = "excel";
            targetFileComboBox.addItem("JSON");
            delimComboBox.removeAllItems();
        } else if (selectedBook.equals("Text File")) {
            delimComboBox.removeAllItems();
            sourceFileType = "text";
            targetFileComboBox.addItem("JSON");
            targetFileComboBox.addItem("Excel");
            delimComboBox.addItem(",");
            delimComboBox.addItem("\\t");
        } else {
            sourceFileType = "xml";
            targetFileComboBox.addItem("JSON");
            targetFileComboBox.addItem("Excel");
            delimComboBox.removeAllItems();
        }

    }


    // MODIFIES: this
    // EFFECTS: Based on the input in the sourceFile combo box the Delim combo box elements are added.
    public void delimComboBoxListener() {
        delimComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedBook = "0";
                String delimiter = (String) delimComboBox.getSelectedItem();
                if (delimiter != null) {
                    selectedBook = delimiter;
                }
                if (selectedBook.equals(",")) {
                    delim = ",";
                } else if (selectedBook.equals("\\t")) {
                    delim = "\t";
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: Listens to the input of the target file ComboBox and assigns the target file type
    public void targetFileComboBoxListener() {
        targetFileComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String selectedBook = "0";
                String str = (String) targetFileComboBox.getSelectedItem();
                if (str != null) {
                    selectedBook = str;
                }
                if (selectedBook.equals("JSON")) {
                    targetFileType = "json";
                } else {
                    targetFileType = "excel";
                }

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Converts Excel file to respective target file type and calls a helper to be run in the GUI backend.
    public void convertExcelToX(String targetFileType) throws IOException, InvalidFormatException {
        switch (targetFileType) {
            case "json":
                convertExcelToJsonGuiTask();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Please check the entry again");
        }

    }

    // MODIFIES: this
    // EFFECTS: Converts Excel file to Json by the GUI
    private void convertExcelToJsonGuiTask() throws IOException, InvalidFormatException {
        File file2 = new File(textArea1.getText()); //creating a new file instance using the user's file path
        JSONObject obj = ReadAndOperateExcel.convertToJson(file2);
        WriteFiles.writeToJsonFile(obj, textArea2.getText());
        JOptionPane.showMessageDialog(null, "Successfully Converted your Excel to a JSON File");

    }


    // MODIFIES: this
    // EFFECTS: Converts XML file to respective target file type and calls helpers to be run in the GUI backend.
    public void convertXmlToX(String targetFileType) throws Exception {
        switch (targetFileType) {
            case "json":
                convertXmlToJsonGuiTask();
                break;
            case "excel":
                convertXmlToExcelGuiTask();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Please check the entry again");
        }

    }

    // MODIFIES: this
    // EFFECTS: Converts XML file to Excel by the GUI
    private void convertXmlToExcelGuiTask() throws Exception {

        Workbook wb = ReadAndOperateXML.convertExcel(textArea1.getText());
        WriteFiles.writeToExcel(wb, textArea2.getText());
        JOptionPane.showMessageDialog(null, "Successfully Converted your XML to an Excel File");
    }

    // MODIFIES: this
    // EFFECTS: Converts XML file to JSON by the GUI
    private void convertXmlToJsonGuiTask() throws Exception {
        JSONObject obj = ReadAndOperateXML.convertToJson(textArea1.getText());
        WriteFiles.writeToJsonFile(obj, textArea2.getText());
        JOptionPane.showMessageDialog(null, "Successfully Converted your XML to a JSON File");

    }

    // MODIFIES: this
    // EFFECTS: Converts Text file to respective target file type and calls helpers to be run in the GUI backend.
    public void convertTextToX(String targetFileType) throws IOException {
        switch (targetFileType) {
            case "json":
                convertTextToJsonGuiTask();
                break;
            case "excel":
                convertTextToExcelGuiTask();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Please check the entry again");
        }

    }

    // MODIFIES: this
    // EFFECTS: Converts Text file to Excel by the GUI
    private void convertTextToExcelGuiTask() throws IOException {

        HSSFWorkbook workbook = ReadAndOperateCSV.convertExcel(new File(textArea1.getText()), delim);
        WriteFiles.writeToExcel(workbook, textArea2.getText());
        JOptionPane.showMessageDialog(null, "Successfully Converted your Text to a Excel File");
    }

    // MODIFIES: this
    // EFFECTS: Converts Text file to JSON by the GUI
    private void convertTextToJsonGuiTask() throws IOException {

        List<Map<?, ?>> mapObj = ReadAndOperateCSV.convertToJson(new File(textArea1.getText()));
        WriteFiles.writeAsJson(mapObj, new File(textArea2.getText()));
        JOptionPane.showMessageDialog(null, "Successfully Converted your Text to a JSON File");

    }

    // EFFECTS: Quits the application if the Quit button is pressed and calls a helper to play a sound while quitting.
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
}