package ui;

import com.aspose.cells.Workbook;
import model.ReadAndOperateXML;
import model.WriteFiles;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

//Class to Read and Display the Data in an XML File.
public class DisplayXmlConsole {

    //Prints the Menu for an XML file
    public static void menuToReadXml() throws Exception {
        System.out.println("Press 1 to Display the XML File on the console.");
        System.out.println("Press 2 to Convert the XML File to an excel File");
        System.out.println("Press 3 to Convert the XML File to a JSON File");
        System.out.println("Press 4 to Quit DevTools.");

        //Declare input as scanner
        Scanner input = new Scanner(System.in);
        char choice = input.next().charAt(0);

        //Perform Operations based on user's input
        switch (choice) {

            case '1':
                menuToReadAndDisplayXml(); // Menu to Display XML file
                break;

            case '2':
                menuToConvertToExcel(); // Menu to convert to Excel
                break;

            case '3':
                menuToConvertToJson(); // Menu to convert to JSON
                break;

            case '4':
                quitDevTools(); // Quit DevTools
                break;
        }
    }

    // EFFECTS: Menu driven to call the function that converts a XML File to a JSON file
    private static void menuToConvertToJson() throws Exception {
        System.out.println("Enter the file path from which you want to read the XML File");
        Scanner scnr = new Scanner(System.in);
        String filePath = scnr.nextLine();
        JSONObject obj = ReadAndOperateXML.convertToJson(filePath);
        System.out.println("Enter the file path to which you wanna download your JSON File");
        filePath = scnr.nextLine();
        WriteFiles.writeToJsonFile(obj, filePath);
    }

    // EFFECTS: Menu driven to call the function that reads a XML File and displays it in the console
    public static void menuToReadAndDisplayXml() {
        System.out.println("Enter the file path from which you want to read the XML File");
        Scanner scnr = new Scanner(System.in);
        String filePath = scnr.nextLine();
        readAndDisplay(new File(filePath)); //creating a new file instance using the user's file path to read
    }

    // EFFECTS: Menu driven to call the function that converts a XML File to a excel file
    public static void menuToConvertToExcel() throws Exception {
        System.out.println("Enter the file path from which you want to read the XML File");
        Scanner scnr2 = new Scanner(System.in);
        String filePath2 = scnr2.nextLine();
        Workbook wb = ReadAndOperateXML.convertExcel(filePath2);
        System.out.println("Enter the file path to which you wanna download your Excel File");
        filePath2 = scnr2.nextLine();
        WriteFiles.writeToExcel(wb, filePath2);
        System.out.println("Hurray! You have successfully converted your file to an excel file");
    }

    // EFFECTS: Quits the application
    private static void quitDevTools() {
        System.out.println("Quitting DevTools.....");
        System.exit(0);
    }


    //EFFECTS: Reads and displays the contents of the XML file
    // Uses a helper function to print each of its Child Nodes.
    public static void readAndDisplay(File file) {

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            if (document.hasChildNodes()) {
                printNodeList(document.getChildNodes());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //EFFECTS: Helper function to print the list of nodes
    private static void printNodeList(NodeList nodeList) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node elemNode = nodeList.item(count);
            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + elemNode.getNodeName() + " [OPEN]");
                System.out.println("Node Content =" + elemNode.getTextContent());

                if (elemNode.hasAttributes()) {
                    NamedNodeMap nodeMap = elemNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());
                    }
                }

                if (elemNode.hasChildNodes()) {
                    printNodeList(elemNode.getChildNodes()); //recursive call if the node has child nodes
                }

                System.out.println("Node Name =" + elemNode.getNodeName() + " [CLOSE]");
            }
        }
    }
}
