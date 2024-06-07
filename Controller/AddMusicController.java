package Controller;

import Model.MusicModel;
import Model.PlaylistModel;
import Services.PlaylistService;

public class AddMusicController {
    public void addMusic(PlaylistModel playlist, MusicModel newMusic) {
        try {
            PlaylistService playlistService = new PlaylistService();
            playlistService.addMusic(playlist, newMusic);
            playlistService.getPlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
