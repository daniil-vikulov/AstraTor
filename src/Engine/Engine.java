package Engine;

import Physics.*;
import Tools.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
    public ArrayList<Body> data = new ArrayList<>();
    public ArrayList<Line> lines = new ArrayList<>();
    public static double G = 10;

    public Engine() {
        Random random = new Random();
        Material wood = new Material(0.5, new Color(129,94,62));
        data.add(new Ball(new Vector(100,300), 1, 10, new Material(0.5, new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)))));
        //data.add(new Ball(new Vector(200,300), 1, 10, new Material(0.5, new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)))));
        data.get(data.size()-1).cV = new Vector(10, 100);
        lines.add(new Line(new Vector(0, 500), new Vector(800, 500)));
    }

    public void update(double dt){
        for (int i = 0; i < data.size() ; i++) {
            for (int j = i + 1; j < data.size(); j++) {
                    data.get(i).collision(data.get(j));
            }
            for (int k = 0; k < lines.size(); k++) {
                data.get(k).collideWithLine(lines.get(k));
            }
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).updateC(dt);
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).cV.plusMe(new Vector(0, G).x(dt));
            data.get(i).cF.xMe(0);
            data.get(i).cA.xMe(0);
        }
        //System.out.println("////////////////////////////////////////////////////////");
    }

    public void draw(Graphics g) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).draw(g);
        }
        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).draw(g);
        }
    }
}
