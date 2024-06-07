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

public class PlaylistService {
    
    public void getPlaylist() {
        PlaylistModel.playlist.clear();
    
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist.json"))
                .build();
        
        client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(jsonBody -> {
            System.out.println(jsonBody);
            try {
                Object json = new JSONParser().parse(jsonBody);
                if (json instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) json;
                    for (Object element : jsonArray) {
                        if (element instanceof JSONObject) {
                            JSONObject jsonObject = (JSONObject) element;
                            String id = jsonObject.get("id").toString();
                            processPlaylistElement(id, jsonObject);
                        }
                    }
                } else if (json instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) json;
                    jsonObject.forEach((key, value) -> {
                        processPlaylistElement(key.toString(), value);
                    });
                }
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        });

    }
    
    private void processPlaylistElement(String id, Object element) {
        if (element != null) {
            JSONObject songObject = (JSONObject) element;
            PlaylistModel playList = new PlaylistModel(
                (String) songObject.get("playlistName"),
                (String) songObject.get("description"),
                (String) songObject.get("playlistImage"),
                id
            );
            System.out.println(playList.getUniqueId());
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
            if (response.statusCode() == 200){
                System.out.println("Playlist added successfully");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }   
    }

    public void updatePlaylist(String playlistName, String description, String playlistImage) throws ParseException {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = String.format("{\"playlistName\":\"%s\",\"description\":\"%s\", \"playlistImage\":\"%s\"}", playlistName, description, playlistImage);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist.json"))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200){
                System.out.println("Playlist updated successfully");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }   
    }
}
