package Graphics;

import javax.swing.*;
import java.awt.*;
import Engine.*;
import Main.Main;

public class MyPanel extends JPanel {
    Engine engine;
    double lastTime;
    static double timePast;
    double currentTime;
    double dt = 0.01;

    public MyPanel() {
        lastTime = System.currentTimeMillis();
        Interface inF = new Interface();
        addMouseMotionListener(inF);
        addMouseListener(inF);
        setFocusable(true);
        addKeyListener(inF);
        ////////////////////
        engine = new Engine();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        var t1 = System.nanoTime();
        currentTime = System.currentTimeMillis();
        timePast = (currentTime - lastTime) / 1000.0;
        lastTime = currentTime;
        ///////////////////////////
        engine.update(dt);
        draw(g);
        ///////////////////////////
        g.setColor(Color.white);
        g.drawString(String.format("%.2f", 1000d / ((System.nanoTime() - t1) / 1000000d)), 10, 10);
        lastTime = System.currentTimeMillis();
        repaint();
    }

    private void draw(Graphics g) {
        for (int i = 0; i < engine.data.size(); i++) {
            engine.data.get(i).draw(g);
        }
    }
}