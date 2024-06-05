import Services.MusicService;
import View.Home;

public class Main {
    public static void main(String[] args) {
        MusicService firebaseService = new MusicService();
        try {
            firebaseService.makeRequest();
            new Home();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 