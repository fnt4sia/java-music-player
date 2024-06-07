package Services;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
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
                        JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonBody);
                        for (Object key : jsonObject.keySet()) {
                            JSONObject songObject = (JSONObject) jsonObject.get(key);

                            MusicModel musicModel = new MusicModel(
                                (String) songObject.get("title"),
                                (String) songObject.get("artist"),
                                (String) songObject.get("album"),
                                (String) songObject.get("duration"),
                                (String) songObject.get("link"),
                                (String) songObject.get("image")
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
