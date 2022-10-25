package uet.oop.bomberman.sounds;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JOptionPane;
import java.awt.Button;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class musicGame {
    public static final int Cycle = -1;
    private List<String> Music = new ArrayList<>();

    public static String currentMusic;
    private Media mediaItem;
    private MediaPlayer mediaPlayer;
    private boolean is_playing;


    public musicGame() {
        this.is_playing = false;
        init();
    }

    public void init() {
        currentMusic = "res/music/gameaudio.wav";
        Music.add("res/music/gameaudio.wav");
        Music.add("res/music/On my way.mp3");
        Music.add("res/music/Ignite.mp3");
        Music.add("res/music/Lily.mp3");
        Music.add("res/music/Bones.mp3");
        Music.add("res/music/I_want_you.mp3");
        Music.add("res/music/Natural.mp3");
        Music.add("res/music/Lost_Sky.mp3");
        Music.add("res/music/Never.mp3");
        Music.add("res/music/Centuries.mp3");
        Music.add("res/music/That_Girl_Remix.mp3");

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

    public List<String> getMusic() {
        return Music;
    }

    public void setMusic(List<String> music) {
        Music = music;
    }

    public static String getCurrentMusic() {
        return currentMusic;
    }

    public static void setCurrentMusic(String currentMusic) {
        musicGame.currentMusic = currentMusic;
    }

    public void playMusic() {
        try {
            String path = new File(currentMusic).toURI().toString();
            this.mediaItem = new Media(path);
            this.mediaPlayer = new MediaPlayer(this.mediaItem);
            this.mediaPlayer.setCycleCount(this.Cycle);
            this.mediaPlayer.play();
            this.is_playing = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void playLeft() {
        for (int i = 0; i < Music.size(); i++) {
            if (this.currentMusic.equals(Music.get(i))) {
                if (i == 0) {
                    currentMusic = Music.get(Music.size() - 1);
                } else {
                    currentMusic = Music.get(i - 1);
                }
                break;
            }
        }
        boolean temp = this.mediaPlayer.isMute();
        this.mediaPlayer.pause();
        playMusic();
        this.mediaPlayer.setMute(temp);
    }


    public void playRight() {
        for (int i = 0; i < Music.size(); i++) {
            if (this.currentMusic.equals(Music.get(i))) {
                if (i == Music.size() - 1) {
                    currentMusic = Music.get(0);
                } else {
                    currentMusic = Music.get(i + 1);
                }
                break;
            }
        }
        boolean temp = this.mediaPlayer.isMute();
        this.mediaPlayer.pause();
        playMusic();
        this.mediaPlayer.setMute(temp);
    }

    public void pause(){
        this.mediaPlayer.pause();
        this.is_playing = false;
    }

    public void resumme(){
        this.mediaPlayer.play();
        this.is_playing = true;
    }
}
