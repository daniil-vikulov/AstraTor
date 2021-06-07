package PlanetaryEngine;
import Engine.Ball;
import Engine.Engine;
import Engine.Vector;
import Graphics.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlanetaryEngine {
    public final double G = 6.6743e-11;
    public static double zoom = 3.899324011660668E-9;
    public RenderEngine renderEngine;
    public static double AU = 149.6e9;
    public boolean mousePressed = true;
    public BufferedImage space;
    public double speedCoeff = 1;

    public ArrayList<Ball> balls = new ArrayList<>();

    public PlanetaryEngine(){
        createNewSystem();
        try {
            space = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundIamge1.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewSystem() {
        RenderEngine.INDEX_OF_FOCUSED_PLANET = 0;
        balls.add(new Ball(0, 0, 0,0, 0, 0, balls.size()));
        balls.get(0).m = 2e30;
        balls.get(0).radius = 7e8;
        balls.add(new Ball(0.4667*AU, 0, 0, 0, -38.7e3,0, balls.size()));
        balls.get(1).radius = 2439.7e3;
        balls.get(1).m = 3.33e23;
        balls.add(new Ball(0.72823*AU, 0, 0,0, 34.87e3,3e3, balls.size()));
        balls.get(2).radius = 6e6;
        balls.get(2).m = 4.8675e24;
        balls.add(new Ball(AU, 0, 0,0, -29e3,0, balls.size()));
        balls.get(3).radius = 6378e3;
        balls.get(3).m = 6e24;
        balls.add(new Ball(1.666*AU,0, 0, 0, -21e3, +4e3, balls.size()));
        balls.get(4).radius = 3389500;
        balls.get(4).m = 6.4171e23;
        balls.add(new Ball(5.4581*AU, 0, 0, 0, -12000,-2e3, balls.size()));
        balls.get(5).radius = 69911000;
        balls.get(5).m = 1.8986e27;
        balls.add(new Ball(10.116*AU, 0, 0, 0, -9690,0, balls.size()));
        balls.get(6).radius = 58232000;
        balls.get(6).m = 5.6846e26;
        balls.add(new Ball(20.0833*AU, 0, 0, 0, 6810,0, balls.size()));
        balls.get(7).radius = 25362000;
        balls.get(7).m = 8.6813e25;
        balls.add(new Ball(30.441252*AU, 0, 0, 0, 5.3e3,1000, balls.size()));
        balls.get(8).radius = 24622000;
        balls.get(8).m = 1.0243e26;
        balls.add(new Ball(1.00254*AU, 0, 0, 0, -28e3,0, balls.size()));
        balls.get(9).radius = 1737000;
        balls.get(9).m = 7.3477e22;
        new Thread(() -> {
            Audio backgroundMusic = new Audio("backgroundMusic.wav", 0.8f);
            backgroundMusic.playMusic();
        }).start();

    }

    public void updateAll(double timePast){
        int b = -1;
        for (int j = 0; j < balls.size(); j++) {
            for (int i = j + 1; i < balls.size();) {
                if (balls.get(j).checkCollision(balls.get(i))){
                    balls.get(j).plusMe(balls.get(i));
                    balls.remove(i);
                    b = i;
                }else{
                    i++;
                }
            }
        }

        if (b != -1){
            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).number = i;
            }
            if (b == RenderEngine.INDEX_OF_FOCUSED_PLANET){
                RenderEngine.INDEX_OF_FOCUSED_PLANET = 0;
            }
        }
        for (int g = 0; g < balls.size(); g++){
            balls.get(g).cA.xMe(0);
            for (int i = 0; i < balls.size(); i++) {
                if ( i != g){
                    double r = balls.get(g).c.distanceTo(balls.get(i).c);
                    Vector a = new Vector((balls.get(i).c.x - balls.get(g).c.x)*G*balls.get(i).m/(r*r*r),
                            (balls.get(i).c.y - balls.get(g).c.y)*G*balls.get(i).m/(r*r*r),
                            (balls.get(i).c.z - balls.get(g).c.z)*G*balls.get(i).m/(r*r*r));
                    balls.get(g).cA.plusMe(a);
                }
            }
            balls.get(g).update(timePast);
        }
    }

    public void refocus(int x, int y){
        Ball b;
        for (int i = 0; i < renderEngine.renderingData.size(); i++) {
            b = renderEngine.renderingData.get(i);
            int bX = RenderEngine.engineToWindowX(b.c.x);
            int bY = RenderEngine.engineToWindowY(b.c.y);
            double distance = (bX - x)*(bX - x) + (bY - y)*(bY - y);
            if (distance <= b.radius*b.radius*PlanetaryEngine.zoom*PlanetaryEngine.zoom){
                RenderEngine.INDEX_OF_FOCUSED_PLANET = b.number;
            }
        }
    }
}
