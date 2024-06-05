import Services.FirebaseService;
import View.Home;

public class Main {
    public static void main(String[] args) {
        FirebaseService firebaseService = new FirebaseService();
        try {
            firebaseService.makeRequest();
            new Home();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
} 