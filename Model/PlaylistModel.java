package Model;

import java.util.List;
import java.util.ArrayList;

public class PlaylistModel {
    private final String playlistName;
    private final String description;
    private final String playlistImage;
    private final String uniqueId;
    // private final List<MusicModel> musicList;
    public static List<PlaylistModel> playlist = new ArrayList<PlaylistModel>();
    
    public PlaylistModel(String playlistName, String description, String playlistImage, String uniqueId) {
        this.playlistName = playlistName;
        this.description = description;
        this.playlistImage = playlistImage;
        this.uniqueId = uniqueId;
        // this.musicList = musicList;
    }

    public String getPlaylistName() {
        return playlistName;
    }
    public String getDescription() {
        return description;
    }
    public String getPlaylistImage() {
        return playlistImage;
    }

    public String getUniqueId() {
        return uniqueId;
    }
    // public List<MusicModel> getMusicList() {
    //     return musicList;
    // }


}
