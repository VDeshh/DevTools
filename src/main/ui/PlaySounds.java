package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/*
This Class Represents the set of methods which play a sound when called.
 */
public class PlaySounds {

    // EFFECTS: Plays the sound any green button click
    public static void playClickBtnSound() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File f = new File("./data/sounds/click.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    // EFFECTS: Plays the sound for the back button
    public static void playBackBtnSound() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File f = new File("./data/sounds/backBtn.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    // EFFECTS: Plays the sound any Yellow button click
    public static void playYellowBtnSound() throws IOException,
            LineUnavailableException, UnsupportedAudioFileException {
        File f = new File("./data/sounds/yellowBtn.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    // EFFECTS: Plays the sound for quitting the application
    public static void playQuitBtnSound() throws IOException,
            LineUnavailableException, UnsupportedAudioFileException {
        File f = new File("./data/sounds/quitbtn.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    // EFFECTS: Plays the sound for quit button click
    public static void playQuitBtnClickSound() throws IOException,
            LineUnavailableException, UnsupportedAudioFileException {
        File f = new File("./data/sounds/quitBtnClick.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }


}
