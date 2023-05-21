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

    private static final String root = System.getProperty("user.dir") + "\\sounds\\sounds\\";
    private static final String pathToLost = root + "lost.wav";
    private static final String pathToStartClick = root + "startClick.wav";
    private static final String pathToStart = root + "start.wav";
    private static final String pathToMine = root + "mine.wav";
    private static final String pathToWin = root + "win.wav";
    private static final String pathToMouse = root + "mouse.wav";
    private static final String pathToClick = root + "click.wav";

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

    private static void soundWait() {
        boolean soundRunning;
        do {
            soundRunning = lostClip.isRunning() || startClickClip.isRunning() ||
                    startClip.isRunning() || mineClick.isRunning() || winClip.isRunning() ||
                    mouseClip.isRunning() || clickClip.isRunning();
        } while (soundRunning);
    }

    public static void dispose() {
        soundWait();

        lostClip.close();
        startClickClip.close();
        startClip.close();
        mineClick.close();
        winClip.close();
        mouseClip.close();
        clickClip.close();
    }
}
