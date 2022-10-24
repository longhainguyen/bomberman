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

    public static final String playMusic = "res/audio/gameaudio.wav";

    public static final String deadSound = "res/audio/dead.mp3";

    public static final String gameWin = "res/audio/win.wav";

    public static final String clear = "res/audio/clear.wav";

    public static String currentMusic;

    private List<String> Music = new ArrayList<>();

    private boolean is_playing;

    private int cycle;

    private double volume;

    public musicItem(int cycle, int volume) {
        this.cycle = cycle;
        this.is_playing = false;
        this.volume = volume;
        currentMusic = "res/music/On my way.mp3";
        Music.add("res/music/On my way.mp3");
        Music.add("res/music/Ignite.mp3");
        Music.add("res/music/Lily.mp3");
        Music.add("res/music/Bones.mp3");
        Music.add("res/music/I_Want_You_To_Know_Remix.mp3");
        Music.add("res/music/Natural.mp3");
        Music.add("res/music/Lost_Sky.mp3");
        Music.add("res/music/Never.mp3");
        Music.add("res/music/Nightcore_Centuries.mp3");
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

    public void playSound(String fileMedia) {
        try {
            JFXPanel x = new JFXPanel();
            String path = new File(fileMedia).toURI().toString();
            this.mediaItem = new Media(path);
            this.mediaPlayer = new MediaPlayer(this.mediaItem);
            this.mediaPlayer.setCycleCount(this.cycle);
            this.mediaPlayer.setVolume(this.volume);
            int index = 0;
            if(cycle < 0){
                this.mediaPlayer.play();
            }else {
                while (index < cycle) {
                    this.mediaPlayer.play();
                    index++;
                }
            }
            this.is_playing = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void play() {
        String filePath = "src/uet/oop/bomberman/sounds/audio/homestart.mp3";
       /* try {
            AudioData d = new AudioStream(new FileInputStream(filePath)).getData();
            ContinuousAudioDataStream s = new ContinuousAudioDataStream(d);
            AudioDataStream p = new AudioDataStream(d);
            AudioPlayer.player.start(s);

        } catch (
                Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }*/
       /* try{

            FileInputStream fs = new FileInputStream("E:\\DataBase\\Homework_week7\\intro.wav");
            Player player = new Player(fs);
            player.play();

        } catch (Exception e){
          e.printStackTrace();
        }*/
        try {
            JFXPanel x = new JFXPanel();
            String u = new File(filePath).toURI().toString();
            MediaPlayer music = new MediaPlayer(new Media(u));
            music.setCycleCount(0);
            while (true) {
                music.play();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void changeMusic(){
        for(int i = 0; i < Music.size(); i++){
            if(this.currentMusic.equals(Music.get(i))){
                if(i == Music.size() - 1){
                    currentMusic = Music.get(0);
                }else{
                    currentMusic = Music.get(i + 1);
                }
                break;
            }
        }
        this.mediaPlayer.pause();
        playSound(currentMusic);
    }

}
