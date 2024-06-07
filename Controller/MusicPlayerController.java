package Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Model.MusicModel;
import View.Music;

import java.net.URL;
import java.net.URI;

public class MusicPlayerController {

    public Clip clip;

    public MusicPlayerController(String audioUrl) {
        try {
            URL soundFile = new URI(audioUrl).toURL();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void seek(int seconds) {
        if (clip != null) {
            int framesPerSecond = (int) clip.getFormat().getFrameRate();
            clip.setFramePosition(seconds * framesPerSecond);
        }
    }

    public void nextMusic(String musicTitle) {
        for(int i = 0; i < MusicModel.musicList.size(); i++) {
            if(MusicModel.musicList.get(i).getMusicTitle().equals(musicTitle)){
                if(i == MusicModel.musicList.size() - 1) {
                    clip.close();
                    new Music(MusicModel.musicList.get(0));
                    break;
                } else {
                    clip.close();
                    new Music(MusicModel.musicList.get(i + 1));
                    break;
                }
            }
        }
    }

    public void previousMusic(String musicTitle) {
        for(int i = 0; i < MusicModel.musicList.size(); i++) {
            if(MusicModel.musicList.get(i).getMusicTitle().equals(musicTitle)){
                if(i == 0) {
                    clip.close();
                    new Music(MusicModel.musicList.get(MusicModel.musicList.size() - 1));
                    break;
                } else {
                    clip.close();
                    new Music(MusicModel.musicList.get(i - 1));
                    break;
                }
            }
        }
    }

}
