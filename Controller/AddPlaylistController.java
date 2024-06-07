package Controller;

import Services.PlaylistService;

public class AddPlaylistController {
    
    public void addPlaylist(String playlistName, String description, String playlistImage) {
        PlaylistService playlistService = new PlaylistService();
        try {
            playlistService.addPlaylist(playlistName, description, playlistImage);
            System.out.println("Controller aman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
