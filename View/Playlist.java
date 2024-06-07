package View;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.*;

import Model.PlaylistModel;

public class Playlist {
    JFrame window = new JFrame("Playlist");
    JLabel titleLabel = new JLabel("Your Playlist");
    JButton backButton = new JButton();
    JButton addPlaylist = new JButton("Add Playlist");

    public Playlist() {
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setVisible(true);

        setImageIcon();
        addComponents();
        setBounds();
        customComponents();
    }

    private void setImageIcon() {
        try {
            BufferedImage backImg = ImageIO.read(new File("Assets/back.png"));
            ImageIcon backIcon = new ImageIcon(backImg.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
            backButton.setIcon(backIcon);
            backButton.setOpaque(false);
            backButton.setContentAreaFilled(false);
            backButton.setBorderPainted(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addComponents() {
        window.add(titleLabel);
        window.add(backButton);
        window.add(addPlaylist);
        buttonFunction();
        playlistContainer();
    }

    private void setBounds() {
        // centering the title
        titleLabel.setBounds(450, 20, 200, 30);
        backButton.setBounds(50, 20, 40, 40);
        addPlaylist.setBounds(800, 20, 150, 40);
    }

    private void customComponents() {
        window.getContentPane().setBackground(new Color(238, 249, 253));

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(80, 196, 237));

        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setVerticalAlignment(SwingConstants.CENTER);

        addPlaylist.setForeground(Color.WHITE);
        addPlaylist.setBackground(new Color(80, 196, 237));
        addPlaylist.setFont(new Font("Arial", Font.BOLD, 16));
        addPlaylist.setBorderPainted(false);
        addPlaylist.setFocusPainted(false);
        addPlaylist.setVerticalAlignment(SwingConstants.CENTER);

    }

    private void playlistContainer() {
        JPanel playlistController = new JPanel();
        playlistController.setLayout(new GridBagLayout());
        playlistController.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // padding
        gbc.gridx = 0;

        
        for (int i = 0; i < PlaylistModel.playlist.size(); i++) {
            final int index = i;
            JPanel songPanel = new JPanel();

            songPanel.setLayout(null);
            songPanel.setOpaque(false);
            songPanel.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));
            songPanel.setPreferredSize(new Dimension(860, 120));

            window.add(songPanel);
            JLabel playlistImage = new JLabel();
            JButton playlistUpdate = new JButton();

            try {
                BufferedImage playlistLogo = ImageIO.read(new File("Assets/song_logo.jpg"));
                ImageIcon playlistIcon = new ImageIcon(playlistLogo.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                playlistImage.setIcon(playlistIcon);

                BufferedImage updatePlaylistLogo = ImageIO.read(new File("Assets/edit.png"));
                ImageIcon updatePlaylistIcon = new ImageIcon(updatePlaylistLogo.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                playlistUpdate.setIcon(updatePlaylistIcon);

            } catch (Exception e) {
                e.printStackTrace();
            }

            playlistImage.setBounds(10, 10, 100, 100);
            playlistImage.setBackground(new Color(80, 196, 237));
            playlistImage.setOpaque(false);
            playlistImage.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));
            songPanel.add(playlistImage);


            String title = PlaylistModel.playlist.get(i).getPlaylistName();
            JButton playlistTitle = new JButton(title);
            playlistTitle.setFont(new Font("Arial", Font.BOLD, 24));
            playlistTitle.setForeground(new Color(20, 20, 20));
            playlistTitle.setContentAreaFilled(true);  
            playlistTitle.setBorderPainted(true);    
            playlistTitle.setHorizontalAlignment(SwingConstants.LEFT);

            FontMetrics fm = playlistTitle.getFontMetrics(playlistTitle.getFont());
            int titleWidth = fm.stringWidth(title);
            playlistTitle.setBounds(120, 10, titleWidth + 40, 30); 
            songPanel.add(playlistTitle);

            JTextArea playlistDescription = new JTextArea(PlaylistModel.playlist.get(i).getDescription());
            playlistDescription.setLineWrap(true);
            playlistDescription.setWrapStyleWord(true);
            playlistDescription.setBounds(120, 40, 700, 80);
            playlistDescription.setFont(new Font("Arial", Font.PLAIN, 16));
            playlistDescription.setForeground(new Color(20, 20, 20));
            playlistDescription.setOpaque(false);
            playlistDescription.setEditable(false);
            playlistDescription.setFocusable(false);
            songPanel.add(playlistDescription);

            playlistUpdate.setBounds(800, 10, 30, 30);  
            songPanel.add(playlistUpdate);


            playlistController.add(songPanel, gbc);

            playlistTitle.addActionListener(e -> {
                window.dispose();
                new DetailPlaylist();
            });
            
            playlistUpdate.addActionListener(e -> {
                window.dispose();
                new UpdatePlaylist(
                    PlaylistModel.playlist.get(index).getPlaylistName(),
                    PlaylistModel.playlist.get(index).getDescription(),
                    PlaylistModel.playlist.get(index).getPlaylistImage()
                );
            });
        }
        // push all items to the top
        JPanel glue = new JPanel();
        glue.setOpaque(false);
        gbc.weighty = 1;
        playlistController.add(glue, gbc);

        JScrollPane scrollPane = new JScrollPane(playlistController);
        scrollPane.setBounds(50, 70, 900, 680);
        scrollPane.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        // scrollPane.getVerticalScrollBar().setValue(0);
        window.add(scrollPane);
    }

    private void buttonFunction() {
        backButton.addActionListener(e -> {
            window.dispose();
            new Home();
        });
        addPlaylist.addActionListener(e -> {
            window.dispose();
            new AddPlaylist();
        });

    }
}
