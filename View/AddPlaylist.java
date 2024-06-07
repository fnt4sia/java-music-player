package View;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import Controller.AddPlaylistController;

import java.io.File;
import javax.imageio.ImageIO;

public class AddPlaylist {
    JFrame window = new JFrame("Add Playlist");
    JLabel titleLabel = new JLabel("Create Your New Playlist!");
    JLabel nameLabel = new JLabel("Playlist Name");
    JLabel descriptionLabel = new JLabel("Description");
    JLabel imageLabel = new JLabel("Image URL");

    JTextField jtPlaylistName = new JTextField();
    JTextField jtPlaylistDescription = new JTextField();
    JTextField jtPlaylistImage = new JTextField();

    JButton addPlaylist = new JButton("Add Playlist");
    JButton backButton = new JButton();

    public AddPlaylist() {
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

        nameLabel.setBounds(50, 150, 150, 30);
        jtPlaylistName.setBounds(50, 180, 400, 30);

        descriptionLabel.setBounds(50, 230, 150, 30);
        jtPlaylistDescription.setBounds(50, 260, 400, 30);

        imageLabel.setBounds(50, 310, 150, 30);
        jtPlaylistImage.setBounds(50, 340, 400, 30);

        addPlaylist.setBounds(50, 400, 150, 40);
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
        addPlaylist.addActionListener(e -> {
            if (jtPlaylistName.getText().isEmpty() || jtPlaylistDescription.getText().isEmpty()
                    || jtPlaylistImage.getText().isEmpty()) {
                JOptionPane.showMessageDialog(window, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new AddPlaylistController().addPlaylist(jtPlaylistName.getText(), jtPlaylistDescription.getText(), jtPlaylistImage.getText());
                JOptionPane.showMessageDialog(window, "Playlist Added Successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                window.dispose();
                new Playlist();
            }
        });
    }

}
