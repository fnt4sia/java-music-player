package View;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import Model.MusicModel;
import java.util.List;
import java.awt.*;
import java.util.stream.Collectors;

public class Search {
    
    JFrame window = new JFrame("Search Music");
    JTextField searchTextField = new JTextField();
    JButton searchButton = new JButton("Search");
    JLabel textResult = new JLabel("Search Result For ...");
    JButton backButton = new JButton("Back");
    JLabel errorText = new JLabel("");
    JLabel emptyList = new JLabel("No Music Found :(");

    public final String searchText;
    public final List<MusicModel> searchedMusic;

    public Search(String searchTextString){

        this.searchText = searchTextString;
        searchedMusic = searchMusic();

        textResult.setText("Search Result For " + searchText);
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setVisible(true);

        addComponents(searchedMusic);
        setBounds();
        customComponents();
        buttonFunction();
    }

    private void addComponents(List<MusicModel> searchMusic){
        window.add(textResult);
        window.add(searchTextField);
        window.add(searchButton);
        window.add(backButton);
        window.add(errorText);
        if(searchMusic.isEmpty()){
            window.add(emptyList);
        }
        musicContainer(searchMusic);
    }

    private void setBounds(){
        searchTextField.setBounds(50, 60, 290, 25);
        searchButton.setBounds(350, 60, 100, 25);
        textResult.setBounds(50, 20, 300, 30);
        backButton.setBounds(400, 20, 75, 20);
        errorText.setBounds(50, 85, 300, 20);
        emptyList.setBounds(50, 110, 300, 20);
    }

    private void customComponents(){
        window.getContentPane().setBackground(new Color(238, 249, 253));

        textResult.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        textResult.setForeground(new Color(80, 196, 237));

        errorText.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        errorText.setForeground(new Color(255, 0, 0));

        emptyList.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
    }

    private void musicContainer(List<MusicModel> searchedMusic) {
        int initHeight = 100;

        for(int i = 0;i < searchedMusic.size();i++) {
            final int index = i;
            JPanel musicContainer = new JPanel();

            musicContainer.setBounds(50, initHeight, 400, 45);
            musicContainer.setLayout(null);
            musicContainer.setOpaque(false);
            musicContainer.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

            window.add(musicContainer);
            initHeight += 50;

            JLabel musicTitle = new JLabel(searchedMusic.get(i).getMusicTitle());
            musicTitle.setBounds(0, 0, 150, 20);
            musicTitle.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            musicTitle.setForeground(new Color(20, 20 ,20));
            musicContainer.add(musicTitle);

            JLabel musicArtist = new JLabel(searchedMusic.get(i).getMusicArtist());
            musicArtist.setBounds(0, 15, 150, 20);
            musicArtist.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            musicArtist.setForeground(new Color(125, 125, 125));
            musicContainer.add(musicArtist);

            JLabel albumName = new JLabel(searchedMusic.get(i).getMusicAlbum());
            albumName.setBounds(100, 0, 100, 40);
            albumName.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            albumName.setForeground(new Color(125, 125, 125));
            albumName.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(albumName);

            JLabel musicDuration = new JLabel(searchedMusic.get(i).getMusicDuration());
            musicDuration.setBounds(200, 0, 100, 40);
            musicDuration.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            musicDuration.setForeground(new Color(125, 125, 125));
            musicDuration.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(musicDuration);

            JButton playButton = new JButton("Play");
            playButton.setBounds(300, 10, 70, 20);
            playButton.setForeground(Color.WHITE); 
            playButton.setBackground(new Color(80, 196, 237)); 
            playButton.setFont(new Font("Arial", Font.BOLD, 12));
            playButton.setBorderPainted(false); 
            playButton.setFocusPainted(false); 
            playButton.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(playButton);

            playButton.addActionListener(e -> {
                new Music(searchedMusic.get(index));
                window.dispose();
            });
        }
    }

    private void buttonFunction(){
        searchButton.addActionListener(e -> {
            String search = searchTextField.getText();
            if(search.isEmpty()){
                errorText.setText("Please fill the search field");
                return;
            }else {
                new Search(search);
                window.dispose();
            }

        });

        backButton.addActionListener(e -> {
            new Home();
            window.dispose();
        });
    }

    public List<MusicModel> searchMusic() {
        String lowerCaseSearchText = searchText.toLowerCase();
        return MusicModel.musicList.stream()
            .filter(music -> music.getMusicTitle().toLowerCase().contains(lowerCaseSearchText) || music.getMusicArtist().toLowerCase().contains(lowerCaseSearchText))
            .collect(Collectors.toList());
    }
}
