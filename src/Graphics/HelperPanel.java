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
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ready = true;
        }).start();
    }

    float alpha = 0f;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g.setColor(Color.black);
        g.fillRect(0,0,800,450);
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2D.drawImage(logo, 0,0, null);
        alpha+=0.01f;
        if (alpha > 1f) {
            alpha = 1f;
        }
        repaint();
    }

    public boolean isReady(){
        return ready;
    }
}