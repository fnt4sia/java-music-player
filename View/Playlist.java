package View;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.border.*;

import Model.MusicModel;


public class Playlist {
    JFrame window = new JFrame("Playlist");
    
    JLabel titleLabel = new JLabel("Your Playlist");
    JButton backButton = new JButton("");

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addComponents() {
        window.add(titleLabel);
        window.add(backButton);
        buttonFunction();
        playlistContainer();
    }

    private void setBounds() {
        //centering the title
        titleLabel.setBounds(450, 20, 200, 30);
        backButton.setBounds(50, 20, 40, 40);
    }

    private void customComponents() {
        window.getContentPane().setBackground(new Color(238, 249, 253));

        titleLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
        titleLabel.setForeground(new Color(80, 196, 237));

        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void playlistContainer() {
        JPanel playlistController = new JPanel();
        playlistController.setLayout(new GridBagLayout());
        playlistController.setOpaque(true); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); //  padding
        gbc.gridx = 0;

        for (int i = 0; i < MusicModel.musicList.size(); i++) {
            JPanel songPanel = new JPanel();

            songPanel.setLayout(null);
            songPanel.setOpaque(false);
            songPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
            songPanel.setPreferredSize(new Dimension(380, 55)); // harus ada ini jir biar bsa scroll

            window.add(songPanel);
          
            JButton playButton = new JButton("Play");
            playButton.setBounds(310, 10, 70, 20);
            playButton.setForeground(Color.WHITE);
            playButton.setBackground(new Color(80, 196, 237));
            playButton.setFont(new Font("Arial", Font.BOLD, 12));
            playButton.setBorderPainted(false);
            playButton.setFocusPainted(false);
            playButton.setVerticalAlignment(SwingConstants.CENTER);
            songPanel.add(playButton);

            playlistController.add(songPanel, gbc);

            playButton.addActionListener(e -> {
                // window.dispose();
            });
        }
        // push all items to the top
        JPanel glue = new JPanel();
        glue.setOpaque(false);
        gbc.weighty = 1; 
        playlistController.add(glue, gbc);

        JScrollPane scrollPane = new JScrollPane(playlistController);
        scrollPane.setBounds(50, 140, 400, 350);
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
            new Home();
        });
    }
}
