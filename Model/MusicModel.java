package Model;
import java.util.List;
import java.util.ArrayList;

public class MusicModel {
    private final String musicTitle;
    private final String musicArtist;
    private final String musicAlbum;
    private final String musicDuration;
    private final String musicPath;
    private final String musicImage;
    private int musicDurationSeconds;

    public static List<MusicModel> musicList = new ArrayList<>();
    
    public MusicModel(String musicTitle, String musicArtist, String musicAlbum, String musicDuration, String musicPath, String musicImage) {
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicAlbum = musicAlbum;
        this.musicDuration = musicDuration;
        this.musicPath = musicPath;
        this.musicImage = musicImage;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public String getMusicDuration() {
        return musicDuration;
    }

    public String getMusicPath() {
        System.out.println(musicPath);
        return musicPath;
    }

    public String getMusicImage() {
        return musicImage;
    }

    public int getMusicDurationSeconds() {
        musicDurationSeconds = Integer.parseInt(musicDuration.split(":")[0]) * 60 + Integer.parseInt(musicDuration.split(":")[1]);
        System.out.println(musicDurationSeconds);
        return musicDurationSeconds;
    }
}
