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
    BufferedImage before, logo;

    public HelperPanel() {
        ready = false;
        try {
            before = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("beforelogo1.png")));
            logo = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("logo1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start = System.currentTimeMillis();
        new Thread(() -> {
            try {
                Thread.sleep(4000);
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
        g.fillRect(0, 0, 800, 450);
        if (System.currentTimeMillis() - start < 1800){
            g.drawImage(before, 0,0, null);
        }else{
            g.drawImage(logo, 0,0, null);
        }
        repaint();
    }

    public boolean isReady(){
        return ready;
    }
}