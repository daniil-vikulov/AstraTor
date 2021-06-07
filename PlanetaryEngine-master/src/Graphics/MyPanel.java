package Graphics;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel{
    double lastTime;
    double timePast;
    double currentTime;
    public Engine engine;
    public RenderEngine renderEngine;

    public MyPanel() {
        engine = new Engine(this);
        renderEngine = new RenderEngine(engine);
        engine.renderEngine = renderEngine;
        this.addMouseListener(engine);
        this.addMouseMotionListener(engine);
        this.addMouseWheelListener(engine);
        this.addKeyListener(engine);
        engine.planetaryEngine.renderEngine = renderEngine;
        new Thread(() -> {
            try {
                Thread.sleep(6800);//todo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            engine.inMainMenu = true;
            /*add(engine.mode1);
            add(engine.mode2);
            add(engine.mode3);
            add(engine.exit1);*/
            add(engine.play);
            add(engine.settings);
            add(engine.exit);
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        engine.g = g;
        var t1 = System.nanoTime();
            currentTime = System.currentTimeMillis();
        if (currentTime - engine.startingTime > 7000){//TODO
            if (engine.inMainMenu){
                if (!engine.inSettingsMenu){
                    engine.mainMenu();
                }else{
                    engine.settingsMenu();
                }
            }else{
                g.drawImage(engine.planetaryEngine.space,0,0,null);
                timePast = (currentTime - lastTime)/1000.0;
                lastTime = currentTime;
                for (int i = 0; i < engine.speed; i++) {
                    engine.updateAll(timePast*engine.planetaryEngine.speedCoeff);
                }
                ArrayList<Ball> b = new ArrayList<>();
                for (int i = 0 ; i < engine.planetaryEngine.balls.size(); ++i) {
                    b.add(new Ball(engine.planetaryEngine.balls.get(i)));
                }
                renderEngine.renderingData = b;
                renderEngine.relocate();
                renderEngine.rotate();
                g.setColor(Color.WHITE);
                g.drawString(String.format("%.2f", 1000d / ((System.nanoTime() - t1) / 1000000d)), 10, 10);
                g.drawString(String.format("x%.2f",  engine.speed*engine.planetaryEngine.speedCoeff), 10, 30);
                renderEngine.drawForPE(g);
                if (engine.inInfoMode){
                    engine.infoMode();
                }
                repaint();
            }
        }else{
            engine.introAnimation(g);
            lastTime = System.currentTimeMillis();
        }

    }
}
