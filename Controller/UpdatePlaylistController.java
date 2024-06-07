package Controller;

import Services.PlaylistService;

public class UpdatePlaylistController {
    public void updatePlaylist(String playlistName, String description, String playlistImage, String uniqueId) {
        try {
            PlaylistService playlistService = new PlaylistService();
            playlistService.updatePlaylist(playlistName, description, playlistImage, uniqueId);
            playlistService.getPlaylist();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
