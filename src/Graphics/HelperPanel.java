package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class HelperPanel extends JPanel {
    private boolean ready;
    long start;
    BufferedImage logo;

    public HelperPanel() {
        ready = false;
        try {
            logo = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logo1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start = System.currentTimeMillis();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ready = true;
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawImage(logo, 0,0, null);
        repaint();
    }

    public boolean isReady(){
        return ready;
    }
}