package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;


/*
This Class Represents the Graphical User Interface (GUI) for the DevTools Home Page
 */
public class HomePageUI extends JFrame {

    private JPanel mainPanel;
    private JLabel imgLogo;
    private JButton loginBtn;
    private JButton signUpButton;
    private JButton softwareUpdateBtn;
    private JButton viewLicenseBtn;
    private JButton viewReadmeBtn;
    private JButton quitBtn;
    private JButton viewGithubBtn;
    private JFrame frame;


    // MODIFIES: this
    // EFFECTS: Constructs and initializes all the data members of a HomePageUI object
    public HomePageUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        loginBtnClickListener();
        loginBtnMouseListener();
        signUpBtnClickListener();
        signUpBtnMouseListener();
        softwareUpdateBtnClickListener();
        softwareUpdateBtnMouseListener();
        viewLicenseBtnClickListener();
        viewLicenseBtnMouseListener();
        viewReadmeBtnClickListener();
        viewReadmeBtnMouseListener();
        viewGithubBtnClickListener();
        viewgithhubBtnMouseListener();
        quitBtnClickListener();
        quitBtnMouseListener();
        setLocationRelativeTo(null);

    }


    // MODIFIES: this
    // EFFECTS: Creates a custom UI component which renders the DevTools logo as a Jlabel
    private void createUIComponents() {
        imgLogo = new JLabel();
        imgLogo.setIcon(new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(
                200, 200, Image.SCALE_DEFAULT)));
    }

    // EFFECTS: Redirects to the Login Page if the Login Button is pressed
    private void loginBtnClickListener() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlaySounds.playClickBtnSound();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
                JFrame frame2 = new LoginPageUI("Login");
                frame2.setVisible(true);
                setVisible(false);
            }
        });
    }

    // EFFECTS: Redirects to the Signup Page if the Signup Button is pressed
    private void signUpBtnClickListener() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlaySounds.playClickBtnSound();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
                JFrame frame2 = new SignUpPageUI("DevTools Sign Up");
                frame2.setVisible(true);
                setVisible(false);
            }
        });
    }

    // EFFECTS: Checks if the Software update button is pressed but does nothing
    // FOR FUTURE IMPLEMENTATION
    private void softwareUpdateBtnClickListener() {
        softwareUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    // EFFECTS: Checks if the License button is pressed but does nothing
    // FOR FUTURE IMPLEMENTATION
    private void viewLicenseBtnClickListener() {
        viewLicenseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    // EFFECTS: Checks if the View Readme button is pressed but does nothing
    // FOR FUTURE IMPLEMENTATION
    private void viewReadmeBtnClickListener() {
        viewReadmeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    // EFFECTS: Checks if the View Github button and redirects to DevTools open source project repository
    private void viewGithubBtnClickListener() {
        viewGithubBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(URI.create("https://github.students.cs.ubc.ca/CPSC210-2020W-T1/project_w6y2b"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    // EFFECTS: Quits the application if the Quit button is pressed and calls a helper to play a sound while quitting.
    private void quitBtnClickListener() {
        quitBtn.addActionListener(new ActionListener() {
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

    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void signUpBtnMouseListener() {

        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                signUpButton.setBackground(Color.WHITE);
                signUpButton.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(11, 135, 10, temp);
                signUpButton.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                signUpButton.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void softwareUpdateBtnMouseListener() {

        softwareUpdateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                softwareUpdateBtn.setBackground(Color.WHITE);
                softwareUpdateBtn.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(212, 212, 6, temp);
                softwareUpdateBtn.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                softwareUpdateBtn.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }


    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void loginBtnMouseListener() {

        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loginBtn.setBackground(Color.WHITE);
                loginBtn.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(11, 135, 10, temp);
                loginBtn.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                loginBtn.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void viewLicenseBtnMouseListener() {

        viewLicenseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                viewLicenseBtn.setBackground(Color.WHITE);
                viewLicenseBtn.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(21, 41, 117, temp);
                viewLicenseBtn.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                viewLicenseBtn.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void viewgithhubBtnMouseListener() {

        viewGithubBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                viewGithubBtn.setBackground(Color.WHITE);
                viewGithubBtn.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(21, 41, 117, temp);
                viewGithubBtn.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                viewGithubBtn.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void viewReadmeBtnMouseListener() {

        viewReadmeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                viewReadmeBtn.setBackground(Color.WHITE);
                viewReadmeBtn.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                float[] temp = new float[3];
                Color.RGBtoHSB(21, 41, 117, temp);
                viewReadmeBtn.setBackground(Color.getHSBColor(temp[0], temp[1], temp[2]));
                viewReadmeBtn.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }

    // MODIFIES: this
    // EFFECTS: changes the color of the button when hovering over it and changes the mouse pointer
    private void quitBtnMouseListener() {

        quitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                quitBtn.setBackground(Color.WHITE);
                quitBtn.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                quitBtn.setBackground(Color.RED);
                quitBtn.setForeground(Color.WHITE);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

        });
    }


}
