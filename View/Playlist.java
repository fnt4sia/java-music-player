package View;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Playlist {
    JFrame window = new JFrame("Playlist");
    
    JLabel titleLabel = new JLabel("Your Playlist");
    JLabel playlistTitle = new JLabel("Playlist");
    JButton openPlaylist = new JButton("Open Playlist");
    JButton backButton = new JButton("");

    public Playlist() {
        window.setSize(500, 600);
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
            ImageIcon backIcon = new ImageIcon(backImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            backButton.setIcon(backIcon);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addComponents() {
        window.add(titleLabel);
        window.add(playlistTitle);
        window.add(openPlaylist);
        window.add(backButton);
        buttonFunction();
    }

    private void setBounds() {
        titleLabel.setBounds(50, 20, 350, 30);
        playlistTitle.setBounds(50, 60, 350, 30);
        openPlaylist.setBounds(50, 100, 100, 30);
        backButton.setBounds(10, 10, 30, 30);

    }

    private void customComponents() {
        window.getContentPane().setBackground(new Color(238, 249, 253));

        titleLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
        titleLabel.setForeground(new Color(80, 196, 237));

        playlistTitle.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        playlistTitle.setForeground(new Color(80, 196, 237));

        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(80, 196, 237));
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setVerticalAlignment(SwingConstants.CENTER);

    }

    private void buttonFunction() {
        backButton.addActionListener(e -> {
            window.dispose();
            new Home();
        });
    }
}
