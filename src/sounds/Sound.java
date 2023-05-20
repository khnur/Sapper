package sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private static Clip lostClip;
    private static Clip startClickClip;
    private static Clip startClip;
    private static Clip mineClick;
    private static Clip winClip;
    private static Clip mouseClip;
    private static Clip clickClip;

    private static final String pathToLost = "assets/sounds/lost.wav";
    private static final String pathToStartClick = "assets/sounds/startClick.wav";
    private static final String pathToStart = "assets/sounds/start.wav";
    private static final String pathToMine = "assets/sounds/mine.wav";
    private static final String pathToWin = "assets/sounds/win.wav";
    private static final String pathToMouse = "assets/sounds/mouse.wav";
    private static final String pathToClick = "assets/sounds/click.wav";

    private static Clip initSound(String path) {
        try {
            File soundFile = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void startSound() {
        lostClip = initSound(pathToLost);
        startClickClip = initSound(pathToStartClick);
        startClip = initSound(pathToStart);
        mineClick = initSound(pathToMine);
        winClip = initSound(pathToWin);
        mouseClip = initSound(pathToMouse);
        clickClip = initSound(pathToClick);

    }
    private static void playSound(Clip clip) {
        clip.drain();
        clip.setFramePosition(0);
        clip.start();
    }

    public static void playClick() {
        playSound(clickClip);
    }

    public static void playMouse() {
        playSound(mouseClip);
    }

    public static void playStart() {
        playSound(startClip);
    }

    public static void playStartClick() {
        playSound(startClickClip);
    }

    public static void playLost() {
        playSound(lostClip);
    }

    public static void playMine() {
        playSound(mineClick);
    }

    public static void playWin() {
        playSound(winClip);
    }

    public static void dispose() {
        lostClip.close();
        startClickClip.close();
        startClip.close();
        mineClick.close();
        winClip.close();
        mouseClip.close();
        clickClip.close();
    }
}
