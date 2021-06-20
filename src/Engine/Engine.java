package Engine;

import Physics.*;
import Tools.*;

import java.awt.*;
import java.util.ArrayList;

public class Engine {
    public ArrayList<Body> data = new ArrayList<>();//TODO
    public static double G = 0;
    ArrayList<Line> lines = new ArrayList<>();

    public Engine() { //TODO
        Material wood = new Material(0.5, new Color(129,94,62));
        data.add(new Ball(new Vector(100,300), 1, 10, wood));
        data.add(new Ball(new Vector(200,300), 1, 10, wood));
        data.get(data.size()-1).cV = new Vector(-10, 0);
        lines.add(new Line(new Vector(0, 500), new Vector(800, 500)));
        ArrayList<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(10, 10));
        vectors.add(new Vector( 210, 10));
        vectors.add(new Vector(110, 183.2));
        vectors.add(new Vector(20, 100));
        data.add(new Poly(vectors));
    }


    public void update(double dt){
        for (int i = 0; i < data.size() ; i++) {
            for (int j = i + 1; j < data.size(); j++) {
                    data.get(i).collision(data.get(j));
                    data.get(i).collideWithLine();
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
    }

    public void draw(Graphics g) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).draw(g);
        }
    }
}
