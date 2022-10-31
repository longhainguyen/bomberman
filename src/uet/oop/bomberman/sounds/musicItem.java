package uet.oop.bomberman.sounds;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import javafx.scene.media.Media;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class musicItem {
    private Media mediaItem;
    private MediaPlayer mediaPlayer;

    public static final String collectItem = "res/audio/getitem.wav";

    public static final String explosionBomb = "res/audio/boom.wav";

    public static final String layBomb = "res/audio/putbomb.wav";


    public static final String deadSound = "res/audio/dead.mp3";

    public static final String gameWin = "res/audio/win.wav";
    public static final String clear = "res/audio/clear.wav";

    public static final String click = "res/audio/press.wav";

    public static final String lobby = "res/audio/lobby.mp3";

    public static final String roblox = "res/audio/roblox.mp3";


    private List<String> Music = new ArrayList<>();

    private boolean is_playing;

    private int cycle;

    private double volume;

    public musicItem(int cycle, int volume, String fileMedia) {
        this.cycle = cycle;
        this.is_playing = false;
        this.volume = volume;
        try {
            //  JFXPanel x = new JFXPanel();
            String path = new File(fileMedia).toURI().toString();
            this.mediaItem = new Media(path);
            this.mediaPlayer = new MediaPlayer(this.mediaItem);
            this.mediaPlayer.setCycleCount(this.cycle);
            this.mediaPlayer.setVolume(this.volume);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public Media getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(Media mediaItem) {
        this.mediaItem = mediaItem;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public boolean isIs_playing() {
        return is_playing;
    }

    public void setIs_playing(boolean is_playing) {
        this.is_playing = is_playing;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void playSound() {
        this.mediaPlayer.play();
        this.is_playing = true;
    }

    public void soundclick(String fileMedia) {
        try {
            String path = new File(fileMedia).toURI().toString();
            this.mediaItem = new Media(path);
            this.mediaPlayer = new MediaPlayer(this.mediaItem);
            this.mediaPlayer.setCycleCount(this.cycle);
            this.mediaPlayer.setVolume(this.volume);
            this.mediaPlayer.play();
            this.is_playing = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
