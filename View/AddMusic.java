package View;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controller.AddMusicController;
import Model.MusicModel;
import Model.PlaylistModel;

import java.io.File;
import javax.imageio.ImageIO;

public class AddMusic {
    JFrame window = new JFrame("Add Playlist");
    JButton backButton = new JButton();

    PlaylistModel playlist;

    public AddMusic(PlaylistModel playlist) {
        this.playlist = playlist;
        window.setSize(500, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setVisible(true);

        setImageIcon();
        addComponents();
        setBounds();
        customComponents();
        buttonFunction();
        musicContainer();
    }

    private void setImageIcon() {
        try {
            BufferedImage backImg = ImageIO.read(new File("Assets/back.png"));
            ImageIcon backIcon = new ImageIcon(backImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
           
            backButton.setIcon(backIcon);
            backButton.setOpaque(false);
            backButton.setContentAreaFilled(false);
            backButton.setBorderPainted(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addComponents() {
        window.add(backButton);
    }

    private void setBounds() {
        backButton.setBounds(25, 20, 30, 30);
    }

    private void customComponents() {
        window.getContentPane().setBackground(new Color(238, 249, 253));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void musicContainer() {
        JPanel musicPanel = new JPanel();
        musicPanel.setLayout(new GridBagLayout());
        musicPanel.setOpaque(true);
        musicPanel.setBackground(new Color(238, 249, 253));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
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
            musicDuration.setBounds(330, 25, 100, 40);
            musicDuration.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            musicDuration.setForeground(new Color(125, 125, 125));
            musicDuration.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(musicDuration);

            JButton addButton = new JButton();
            try {
                BufferedImage addImg = ImageIO.read(new File("Assets/add.png"));
                ImageIcon addIcon = new ImageIcon(addImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                addButton.setIcon(addIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            addButton.setBounds(328, 5, 30, 30);
            addButton.setFont(new Font("Arial", Font.BOLD, 12));
            backButton.setContentAreaFilled(false);
            addButton.setFocusPainted(false);
            addButton.setVerticalAlignment(SwingConstants.CENTER);
            musicContainer.add(addButton);

            musicPanel.add(musicContainer, gbc);

            addButton.addActionListener(e -> {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this music to the playlist?", "Confirmation", JOptionPane.NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    new AddMusicController().addMusic(playlist, MusicModel.musicList.get(index));
                    JOptionPane.showMessageDialog(null, "Music added to playlist");
                    window.dispose();
                    new Playlist();
                }
            });
        }

        JPanel glue = new JPanel();
        glue.setOpaque(false);
        gbc.weighty = 1;
        musicPanel.add(glue, gbc);

        JScrollPane scrollPane = new JScrollPane(musicPanel);
        scrollPane.setBounds(25, 75, 425, 350);
        scrollPane.setBorder(new LineBorder(new Color(80, 196, 237), 1, true));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        window.add(scrollPane);
    }

    private void buttonFunction() {
        backButton.addActionListener(e -> {
            window.dispose();
            new Playlist();
        });
    }
}