package Services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import Model.MusicModel;
import Model.PlaylistModel;

public class PlaylistService {
    
    @SuppressWarnings("unchecked")
    public void getPlaylist() {
        PlaylistModel.playlist.clear();
    
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist.json"))
                .build();
        
        client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(jsonBody -> {
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
            JSONObject playlistObject = (JSONObject) element;
            String playlistName = (String) playlistObject.get("playlistName");
            String description = (String) playlistObject.get("description");
            String playlistImage = (String) playlistObject.get("playlistImage");
            JSONArray songsArray = (JSONArray) playlistObject.get("songs");

            List<MusicModel> songsList = new ArrayList<>();
            if (songsArray != null) {
                for (Object songObj : songsArray) {
                    if (songObj instanceof JSONObject) {
                        JSONObject songJSON = (JSONObject) songObj;
                        MusicModel song = new MusicModel(
                            (String) songJSON.get("title"),
                            (String) songJSON.get("artist"),
                            (String) songJSON.get("album"),
                            (String) songJSON.get("duration"),
                            (String) songJSON.get("link"),
                            (String) songJSON.get("image")
                        );
                        songsList.add(song);
                    }
                }
            }

            PlaylistModel playList = new PlaylistModel(
                playlistName,
                description,
                playlistImage,
                id,
                songsList
            );
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
            client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }   
    }

    public void updatePlaylist(String playlistName, String description, String playlistImage, String uniqueId) throws ParseException {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = String.format("{\"playlistName\":\"%s\",\"description\":\"%s\", \"playlistImage\":\"%s\"}", playlistName, description, playlistImage);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist/"+uniqueId+".json"))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }   
    }

    public void deletePlaylist(String uniqueId) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist/"+uniqueId+".json"))
                .DELETE()
                .build();

        try {
            client.send(request, BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void addMusic(PlaylistModel playList, MusicModel newMusic) {
        HttpClient client = HttpClient.newHttpClient();
        List<MusicModel> songs = playList.getMusicList();
        songs.add(newMusic);
    
        JSONObject songsObject = new JSONObject();
        int index = 1;
        for (MusicModel song : songs) {
            JSONObject songObject = new JSONObject();
            songObject.put("title", song.getMusicTitle());
            songObject.put("artist", song.getMusicArtist());
            songObject.put("album", song.getMusicAlbum());
            songObject.put("duration", song.getMusicDuration());
            songObject.put("image", song.getMusicImage());
            songObject.put("link", song.getMusicPath());
            songsObject.put(String.valueOf(index++), songObject);
        }

        String jsonBody = songsObject.toJSONString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://music-player-b2c06-default-rtdb.asia-southeast1.firebasedatabase.app/Playlist/"+playList.getUniqueId() + "/songs.json"))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());       
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
