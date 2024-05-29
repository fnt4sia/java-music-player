package Services;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import Model.MusicModel;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FirebaseService {
    
    public void makeRequest() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/.json"))
                .build();

        client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(jsonBody -> {
            try {
                JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonBody);
                for (Object obj : jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;
                    MusicModel musicModel = new MusicModel(
                        (String) jsonObject.get("title"),
                        (String) jsonObject.get("artist"),
                        (String) jsonObject.get("album"),
                        (String) jsonObject.get("duration"),
                        (String) jsonObject.get("link"),
                        (String) jsonObject.get("image")
                    );
                    MusicModel.musicList.add(musicModel);
                }
            } catch (ParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        })
        .exceptionally(e -> {
            System.out.println("Error: " + e.getMessage());
            return null;
        }).join();
    }
}
