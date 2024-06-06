package Services;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import Model.MusicModel;

public class MusicService {
    
    public void getMusic() throws ParseException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Songs.json"))
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
            } catch (org.json.simple.parser.ParseException e1) {
                e1.printStackTrace();
            }
        })
        .exceptionally(e -> {
            System.out.println("Error: " + e.getMessage());
            return null;
        }).join();
    }
}
