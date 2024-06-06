package Services;

// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;
// import java.net.URI;
// import java.net.http.*;
// import java.net.http.HttpResponse.BodyHandlers;

// import Model.PlaylistModel;
// import Model.MusicModel;

// import java.util.List;

public class PlaylistService {
    
    // public void getPlaylist() throws ParseException {

        //  HttpClient client = HttpClient.newHttpClient();
        // HttpRequest request = HttpRequest.newBuilder()
        //         .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist.json"))
        //         .build();

        // client.sendAsync(request, BodyHandlers.ofString())
        // .thenApply(HttpResponse::body)
        // .thenAccept(jsonBody -> {
        //     try {
        //         JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonBody);
        //         for (Object obj : jsonArray) {
        //             JSONObject jsonObject = (JSONObject) obj;
        //             PlaylistModel playlistModel = new PlaylistModel(
        //                 (String) jsonObject.get("name"),
        //                 (String) jsonObject.get("description"),
        //                 (String) jsonObject.get("image"),
        //                 // List<MusicModel> jsonObject.get("music")
        //             );
        //             // PlaylistModel.musicList.add(playlistModel);
        //         }
        //     } catch (org.json.simple.parser.ParseException e1) {
        //         e1.printStackTrace();
        //     }
        // })
        // .exceptionally(e -> {
        //     System.out.println("Error: " + e.getMessage());
        //     return null;
        // }).join();
    // }
}
