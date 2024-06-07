package Controller;

import Services.PlaylistService;

public class UpdatePlaylistController {
    public void updatePlaylist(String playlistName, String description, String playlistImage) {
        try {
            PlaylistService playlistService = new PlaylistService();
            playlistService.updatePlaylist(playlistName, description, playlistImage);
            playlistService.getPlaylist();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
