package Services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

import Model.PlaylistModel;
import View.Playlist;
import Model.MusicModel;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    
    public void getPlaylist() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist.json"))
                .build();
    
        client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(jsonBody -> {
            System.err.println(jsonBody);
            try {
                Object json = new JSONParser().parse(jsonBody);
                if (json instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) json;
                    for (Object element : jsonArray) {
                        processPlaylistElement(element);
                    }
                } else if (json instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) json;
                    for (Object key : jsonObject.keySet()) {
                        Object element = jsonObject.get(key);
                        processPlaylistElement(element);
                    }
                }
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private void processPlaylistElement(Object element) {
        if (element != null) { // Skip null values
            JSONObject songObject = (JSONObject) element;
            System.out.println(songObject.get("playlistName"));
            PlaylistModel playList = new PlaylistModel(
                (String) songObject.get("playlistName"),
                (String) songObject.get("description"),
                (String) songObject.get("playlistImage")
            );
            // Assuming PlaylistModel.playlist is a static List to add playlists
            PlaylistModel.playlist.add(playList);
        }
    }

    public void addPlaylist(String playlistName, String description, String playlistImage) throws ParseException {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = String.format("{\"playlistName\":\"%s\",\"description\":\"%s\", \"playlistImage\":\"%s\"}", playlistName, description, playlistImage);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist.json"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.statusCode() == 200){
                System.out.println("Playlist added successfully");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
