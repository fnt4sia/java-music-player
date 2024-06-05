package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import Controller.MusicPlayerController;
import Model.MusicModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music {
    
    JFrame window = new JFrame("Music Player");
    JPanel imagePanel = new JPanel();
    JLabel musicTitle = new JLabel("Music Title");
    JLabel musicArtist = new JLabel("Music Artist");
    JButton nextButton = new JButton();
    JButton previousButton = new JButton();
    JButton playButton = new JButton();
    JButton backButton = new JButton("Back");
    
    public Boolean isPlaying = true;

    public final MusicPlayerController playerController;
    public final MusicModel musicModel;    

    public Music(MusicModel musicModel){

        try {
            BufferedImage nextImg = ImageIO.read(new File("D:\\ProjectTemp\\final_project_oop\\Assets\\next.png"));
            BufferedImage previousImg = ImageIO.read(new File("D:\\ProjectTemp\\final_project_oop\\Assets\\back.png"));
            BufferedImage playImg = ImageIO.read(new File("D:\\ProjectTemp\\final_project_oop\\Assets\\pause-button.png"));

            ImageIcon nextIcon = new ImageIcon(nextImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            ImageIcon previousIcon = new ImageIcon(previousImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            ImageIcon playIcon = new ImageIcon(playImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            nextButton.setIcon(nextIcon);
            previousButton.setIcon(previousIcon);
            playButton.setIcon(playIcon);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL linkImage = new URI(musicModel.getMusicImage()).toURL();
            BufferedImage image = ImageIO.read(linkImage);
            imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image scaledImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(scaledImage, 0, 0, this);
                }
            };
        }catch(Exception e){
            e.printStackTrace();
        }

        this.musicModel = musicModel;

        window.setSize(500, 500);
        window.setBounds(0, 0, 500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setVisible(true);

        musicTitle.setText(musicModel.getMusicTitle());
        musicArtist.setText(musicModel.getMusicArtist());

        addComponents();
        setBounds();
        customComponents();
        buttonFunction();

        playerController = new MusicPlayerController(musicModel.getMusicPath());
        playerController.play();
    }

    private void addComponents(){
        window.add(imagePanel);
        window.add(musicTitle);
        window.add(musicArtist);
        window.add(nextButton);
        window.add(previousButton);
        window.add(playButton);
        window.add(backButton);
    }

    private void setBounds(){
        imagePanel.setBounds(100, 50, 300, 200);
        musicTitle.setBounds(0, 260, 500, 20);
        musicArtist.setBounds(0, 280, 500, 20);
        nextButton.setBounds(350, 320, 100, 20);
        previousButton.setBounds(50, 320, 100, 20);
        playButton.setBounds(200, 320, 100, 20);
        backButton.setBounds(10, 10, 75, 20);
    }

    private void customComponents(){
        window.getContentPane().setBackground(new Color(238, 249, 253));

        imagePanel.setBackground(new Color(0, 0, 0));

        musicTitle.setHorizontalAlignment(SwingConstants.CENTER);
        musicTitle.setFont(new Font("Arial", Font.BOLD, 20));
        musicTitle.setForeground(new Color(0, 0, 0));

        musicArtist.setHorizontalAlignment(SwingConstants.CENTER);
        musicArtist.setFont(new Font("Arial", Font.PLAIN, 15));
        musicArtist.setForeground(new Color(140, 140, 140));

        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);

        previousButton.setOpaque(false);
        previousButton.setContentAreaFilled(false);
        previousButton.setBorderPainted(false);

        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);

        backButton.setForeground(Color.WHITE); 
        backButton.setBackground(new Color(80, 196, 237)); 
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBorderPainted(false); 
        backButton.setFocusPainted(false); 
        backButton.setVerticalAlignment(SwingConstants.CENTER);


    }

    private void buttonFunction(){
        backButton.addActionListener(e -> {
            playerController.stop();
            window.dispose();
            new Home();
        });

        playButton.addActionListener(e -> {
            if(isPlaying){

                playerController.stop();
                isPlaying = false;

                try {
                    BufferedImage playImg = ImageIO.read(new File("D:\\ProjectTemp\\final_project_oop\\Assets\\play-button-arrowhead.png"));

                    playButton.setIcon(new ImageIcon(playImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                }catch(Exception ex){
                    ex.printStackTrace();
                }

                
            } else {
                playerController.play();
                isPlaying = true;
                
                try {
                    BufferedImage pauseImg = ImageIO.read(new File("D:\\ProjectTemp\\final_project_oop\\Assets\\pause-button.png"));
                    playButton.setIcon(new ImageIcon(pauseImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
