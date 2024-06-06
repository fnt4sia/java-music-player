package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import Model.MusicModel;
import Services.MusicService;
import java.awt.*;

public class Home {

    JFrame window = new JFrame("Home");
    JTextField searchTextField = new JTextField();
    JButton searchButton = new JButton("Search");
    JLabel titleLabel = new JLabel("Java Music Player");
    JLabel recommendedLabel = new JLabel("For You!!!");
    JButton playlistButton = new JButton("Playlist");
    JLabel errorText = new JLabel("");

    MusicService firebaseService = new MusicService();

    public Home() {
        window.setSize(500, 600);
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
        window.add(playlistButton);

        musicContainer();
        buttonFunction();
    }

    private void setBounds() {
        titleLabel.setBounds(50, 20, 350, 30);
        searchTextField.setBounds(50, 60, 290, 25);
        errorText.setBounds(50, 85, 300, 20);
        searchButton.setBounds(350, 60, 100, 25);
        recommendedLabel.setBounds(50, 110, 450, 30);
        playlistButton.setBounds(350, 500, 100, 30);
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

        playlistButton.setForeground(Color.WHITE);
        playlistButton.setBackground(new Color(80, 196, 237));
        playlistButton.setFont(new Font("Arial", Font.BOLD, 12));
        playlistButton.setBorderPainted(false);
        playlistButton.setFocusPainted(false);
        playlistButton.setVerticalAlignment(SwingConstants.CENTER);

    }

    private void musicContainer() {
        JPanel musicPanel = new JPanel();
        musicPanel.setLayout(new GridBagLayout());
        musicPanel.setOpaque(true); 
        // musicPanel.setBackground(new Color(238, 249, 253));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); //  padding
        gbc.gridx = 0;

        for (int i = 0; i < MusicModel.musicList.size(); i++) {
            final int index = i;
            JPanel musicContainer = new JPanel();

            musicContainer.setLayout(null);
            musicContainer.setOpaque(false);
            musicContainer.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
            musicContainer.setPreferredSize(new Dimension(380, 55)); // harus ada ini jir biar bsa scroll

            window.add(musicContainer);

            JLabel musicTitle = new JLabel(MusicModel.musicList.get(i).getMusicTitle());
            // JLabel musicTitle = new JLabel("Music Title");
            musicTitle.setBounds(0, 0, 500, 20);
            musicTitle.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            musicTitle.setForeground(new Color(20, 20, 20));
            musicContainer.add(musicTitle);

            JLabel musicArtist = new JLabel(MusicModel.musicList.get(i).getMusicArtist());
            // JLabel musicArtist = new JLabel("Music Artist");
            musicArtist.setBounds(0, 15, 500, 20);
            musicArtist.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
            musicArtist.setForeground(new Color(125, 125, 125));
            musicContainer.add(musicArtist);

            JLabel albumName = new JLabel(MusicModel.musicList.get(i).getMusicAlbum());
            // JLabel albumName = new JLabel("Album Name");
            albumName.setBounds(0, 25, 500, 40);
            albumName.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            albumName.setForeground(new Color(125, 125, 125));
            albumName.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(albumName);

            JLabel musicDuration = new JLabel(MusicModel.musicList.get(i).getMusicDuration());
            // JLabel musicDuration = new JLabel("Music Duration");
            musicDuration.setBounds(320, 25, 100, 40);
            musicDuration.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            musicDuration.setForeground(new Color(125, 125, 125));
            musicDuration.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(musicDuration);

            JButton playButton = new JButton("Play");
            playButton.setBounds(310, 10, 70, 20);
            playButton.setForeground(Color.WHITE);
            playButton.setBackground(new Color(80, 196, 237));
            playButton.setFont(new Font("Arial", Font.BOLD, 12));
            playButton.setBorderPainted(false);
            playButton.setFocusPainted(false);
            playButton.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(playButton);

            musicPanel.add(musicContainer, gbc);

            playButton.addActionListener(e -> {
                window.dispose();
                new Music(MusicModel.musicList.get(index));
            });
        }
        // push all items to the top
        JPanel glue = new JPanel();
        glue.setOpaque(false);
        gbc.weighty = 1; 
        musicPanel.add(glue, gbc);

        JScrollPane scrollPane = new JScrollPane(musicPanel);
        scrollPane.setBounds(50, 140, 400, 350);
        scrollPane.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        window.add(scrollPane);
    }

    private void buttonFunction() {
        searchButton.addActionListener(e -> {
            String search = searchTextField.getText();
            if (search.isEmpty()) {
                errorText.setText("Please enter a search term");
            } else {
                window.dispose();
                new Search(search);
            }
        });
        playlistButton.addActionListener(e -> {
            window.dispose();
            new Playlist();
        });
    }
}