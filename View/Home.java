package View;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Model.MusicModel;
import Services.FirebaseService;
import java.awt.*;

public class Home {

    JFrame window = new JFrame("Home");

    JTextField searchTextField = new JTextField();
    JButton searchButton = new JButton("Search");
    JLabel titleLabel = new JLabel("Java Music Player");
    JLabel recommendedLabel = new JLabel("For You!!!");
    JLabel errorText = new JLabel("");

    FirebaseService firebaseService = new FirebaseService();

    public Home() {
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setVisible(true);

        addComponents();
        setBounds();
        customComponents();
    }

    private void addComponents() {
        window.add(titleLabel);
        window.add(searchTextField);
        window.add(searchButton);
        window.add(recommendedLabel);
        window.add(errorText);
        musicContainer();
        buttonFunction();
    }

    private void setBounds(){
        titleLabel.setBounds(50, 20, 350, 30);
        searchTextField.setBounds(50, 60, 290, 25);
        errorText.setBounds(50, 85, 300, 20);
        searchButton.setBounds(350, 60, 100, 25);
        recommendedLabel.setBounds(50, 110, 450, 30);
    }

    private void customComponents() {
        window.getContentPane().setBackground(new Color(238, 249, 253));

        titleLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
        titleLabel.setForeground(new Color(80, 196, 237));

        searchTextField.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));

        searchButton.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));
        searchButton.setBackground(Color.white);
        searchButton.setForeground(new Color(80, 196, 237));

        recommendedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        recommendedLabel.setForeground(new Color(80, 196, 237));

        errorText.setFont(new Font("Arial", Font.BOLD, 12));
        errorText.setForeground(Color.RED);
    }

    private void musicContainer() {
        int initHeight = 140;
        for(int i = 0;i < MusicModel.musicList.size();i++) {
            final int index = i;
            JPanel musicContainer = new JPanel();

            musicContainer.setBounds(50, initHeight, 400, 45);
            musicContainer.setLayout(null);
            musicContainer.setOpaque(false);
            musicContainer.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

            window.add(musicContainer);
            initHeight += 50;

            JLabel musicTitle = new JLabel(MusicModel.musicList.get(i).getMusicTitle());
            musicTitle.setBounds(0, 0, 150, 20);
            musicTitle.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            musicTitle.setForeground(new Color(20, 20 ,20));
            musicContainer.add(musicTitle);

            JLabel musicArtist = new JLabel(MusicModel.musicList.get(i).getMusicArtist());
            musicArtist.setBounds(0, 15, 100, 20);
            musicArtist.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            musicArtist.setForeground(new Color(125, 125, 125));
            musicContainer.add(musicArtist);

            JLabel albumName = new JLabel(MusicModel.musicList.get(i).getMusicAlbum());
            albumName.setBounds(100, 0, 100, 40);
            albumName.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            albumName.setForeground(new Color(125, 125, 125));
            albumName.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(albumName);

            JLabel musicDuration = new JLabel(MusicModel.musicList.get(i).getMusicDuration());
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
                new Music(MusicModel.musicList.get(index));
                window.dispose();
            });
        }
    }

    private void buttonFunction() {
        searchButton.addActionListener(e -> {
            String search = searchTextField.getText();
            if(search.isEmpty()) {
                errorText.setText("Please enter a search term");
            } else {
                window.dispose();
                new Search(search);
            }

        });
    }
}