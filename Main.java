import Services.MusicService;
import Services.PlaylistService;
import View.Home;

public class Main {
    public static void main(String[] args) {
        try {
            new MusicService().getMusic();
            new PlaylistService().getPlaylist();
            new Home();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
} 