package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Controller.MusicPlayerController;
import Model.MusicModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music {
    public final MusicPlayerController playerController;
    public final MusicModel musicModel;

    JFrame window = new JFrame("Music Player");
    JPanel imagePanel = new JPanel();
    JLabel musicTitle = new JLabel("Music Title");
    JLabel musicArtist = new JLabel("Music Artist");
    JButton nextButton = new JButton();
    JButton previousButton = new JButton();
    JButton playButton = new JButton();
    JButton backButton = new JButton();

    ImageIcon playIcon;
    ImageIcon pauseIcon;
    

    JSlider playhead = new JSlider();
    

    public Boolean isPlaying = true;
    private Timer timer;


    public Music(MusicModel musicModel) {
        setImageIcon();
        try {
            BufferedImage nextImg = ImageIO.read(new File("Assets/next.png"));
            BufferedImage previousImg = ImageIO.read(new File("Assets/prev.png"));

            ImageIcon nextIcon = new ImageIcon(nextImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            ImageIcon previousIcon = new ImageIcon(previousImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            nextButton.setIcon(nextIcon);
            previousButton.setIcon(previousIcon);

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
        } catch (Exception e) {
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
        startPlayhead();

        nextButton.addActionListener(e -> {
            playerController.stop();
            window.dispose();
            playerController.nextMusic(musicModel.getMusicTitle());
        });

        previousButton.addActionListener(e -> {
            playerController.stop();
            window.dispose();
            playerController.previousMusic(musicModel.getMusicTitle());
        });
    }

    private void setImageIcon() {
        try {
            BufferedImage nextImg = ImageIO.read(new File("Assets/next.png"));
            BufferedImage previousImg = ImageIO.read(new File("Assets/prev.png"));
            BufferedImage playImg = ImageIO.read(new File("Assets/play-button-arrowhead.png"));
            BufferedImage pauseImg = ImageIO.read(new File("Assets/pause-button.png"));
            BufferedImage backImg = ImageIO.read(new File("Assets/back.png"));

            ImageIcon backIcon = new ImageIcon(backImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            ImageIcon nextIcon = new ImageIcon(nextImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            ImageIcon previousIcon = new ImageIcon(previousImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            playIcon = new ImageIcon(playImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            pauseIcon = new ImageIcon(pauseImg.getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            backButton.setIcon(backIcon);
            nextButton.setIcon(nextIcon);
            previousButton.setIcon(previousIcon);
            playButton.setIcon(pauseIcon);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addComponents() {
        window.add(imagePanel);
        window.add(musicTitle);
        window.add(musicArtist);
        window.add(nextButton);
        window.add(previousButton);
        window.add(playButton);
        window.add(backButton);
        window.add(playhead);
    }

    private void setBounds() {
        imagePanel.setBounds(100, 50, 300, 200);
        musicTitle.setBounds(0, 260, 500, 20);
        musicArtist.setBounds(0, 280, 500, 20);
        nextButton.setBounds(350, 320, 100, 20);
        previousButton.setBounds(50, 320, 100, 20);
        playButton.setBounds(200, 320, 100, 20);
        backButton.setBounds(10, 10, 30, 30);
        playhead.setBounds(50, 350, 400, 20);
    }

    private void customComponents() {
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

        backButton.setOpaque(false);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(80, 196, 237));
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setVerticalAlignment(SwingConstants.CENTER);

        window.getContentPane().setBackground(new Color(238, 249, 253));

        musicTitle.setHorizontalAlignment(SwingConstants.CENTER);
        musicTitle.setFont(new Font("Arial", Font.BOLD, 20));
        musicTitle.setForeground(new Color(0, 0, 0));

        musicArtist.setHorizontalAlignment(SwingConstants.CENTER);
        musicArtist.setFont(new Font("Arial", Font.PLAIN, 15));
        musicArtist.setForeground(new Color(140, 140, 140));

    }

    private void buttonFunction() {
        backButton.addActionListener(e -> {
            playerController.stop();
            window.dispose();
            new Home();
        });

        playButton.addActionListener(e -> {
            if (isPlaying) {
                playerController.stop();
                isPlaying = false;
                timer.stop();
                try {
                    playButton.setIcon(playIcon);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                playerController.play();
                isPlaying = true;
                timer.start();
                try {
                    playButton.setIcon(pauseIcon);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        
    }

    private void startPlayhead() {
        int duration = musicModel.getMusicDurationSeconds();
        timer = new Timer(1000, e -> {
            playhead.setValue(playhead.getValue() + 1);
            if (playhead.getValue() >= duration) {
                timer.stop();
            }
        });
        playhead.setOpaque(false);
        playhead.setMaximum(duration);
        playhead.setValue(0);
        playhead.setPaintTicks(false);
        playhead.setPaintLabels(false);
        playhead.setSnapToTicks(false);
        timer.start();

        // mouse klik
        playhead.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int mouseX = e.getX();
                int progressBarVal = (int) Math
                        .round(((double) mouseX / (double) playhead.getWidth()) * playhead.getMaximum());
                playhead.setValue(progressBarVal);
                playerController.seek(playhead.getValue());
            }
        });

        // mouse geser
        playhead.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!playhead.getValueIsAdjusting()) {
                    playerController.seek(playhead.getValue());
                }
            }
        });

    }
}
