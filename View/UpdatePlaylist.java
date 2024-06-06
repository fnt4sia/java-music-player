package View;

import java.io.File;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;


public class UpdatePlaylist {
     JFrame window = new JFrame("Add Playlist");
    JLabel titleLabel = new JLabel("Update Your Beloved Playlist!");

    JLabel nameLabel = new JLabel("Playlist Name");
    JLabel descriptionLabel = new JLabel("Description");
    JLabel imageLabel = new JLabel("Image URL");

    JTextField jtPlaylistName = new JTextField("DEFAULT VALUE NAME");
    JTextField jtPlaylistDescription = new JTextField("DEFAULT VALUE DESCRIPTION");
    JTextField jtPlaylistImage = new JTextField("DEFAULT VALUE URL");

    JButton addPlaylist = new JButton("Update Playlist");
    JButton backButton = new JButton();

    public UpdatePlaylist() {
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
        window.add(titleLabel);
        window.add(jtPlaylistName);
        window.add(jtPlaylistDescription);
        window.add(jtPlaylistImage);
        window.add(nameLabel);
        window.add(descriptionLabel);
        window.add(imageLabel);
        window.add(addPlaylist);
        window.add(backButton);
    }

    private void setBounds() {
        backButton.setBounds(25, 20, 30, 30);

        titleLabel.setBounds(50, 75, 500, 30);
        nameLabel.setBounds(50, 125, 100, 30);
        jtPlaylistName.setBounds(50, 155, 400, 30);

        descriptionLabel.setBounds(50, 205, 100, 30);
        jtPlaylistDescription.setBounds(50, 235, 400, 30);

        imageLabel.setBounds(50, 285, 100, 30);
        jtPlaylistImage.setBounds(50, 315, 400, 30);

        addPlaylist.setBounds(50, 400, 400, 30);

    }

    private void customComponents() {
        window.getContentPane().setBackground(new Color(238, 249, 253));
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 25));
        titleLabel.setForeground(new Color(80, 196, 237));

        nameLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        descriptionLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        imageLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));

        jtPlaylistDescription.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
        jtPlaylistDescription.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));
        jtPlaylistDescription.setBackground(new Color(238, 249, 253));

        jtPlaylistName.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
        jtPlaylistName.setBackground(new Color(238, 249, 253));
        jtPlaylistName.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));

        jtPlaylistImage.setBackground(new Color(238, 249, 253));
        jtPlaylistImage.setBorder(new javax.swing.border.LineBorder(Color.BLACK));
        jtPlaylistImage.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 15));

        addPlaylist.setBackground(new Color(80, 196, 237));
        addPlaylist.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        addPlaylist.setForeground(Color.WHITE);
    }

    public void buttonFunction() {
        backButton.addActionListener(e -> {
            window.dispose();
            new Playlist();
        });
        // error handling kalau jt kosong
        addPlaylist.addActionListener(e -> {
            if (jtPlaylistName.getText().isEmpty() || jtPlaylistDescription.getText().isEmpty()
                    || jtPlaylistImage.getText().isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // controller disini tot
                JOptionPane.showMessageDialog(window, "Playlist Added Successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                window.dispose();
                new Playlist();
            }
        });
    }

}
