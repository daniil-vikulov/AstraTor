package Engine;

import Physics.*;
import Tools.*;

import java.awt.*;
import java.util.ArrayList;

public class Engine {
    public ArrayList<Body2d> data = new ArrayList<>();//TODO
    public static double G = 0;

    public Engine() { //TODO
        data.add(new Ball2d(new Vector2d(100,200), 1, 10, Color.gray));
        data.add(new Ball2d(new Vector2d(200,200), 1, 10, Color.gray));
        //data.get(data.size()-1).cV = new Vector2d(-20, -2);
    }

    public void update(double dt){
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                data.get(i).attract(data.get(j));
            }
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).updateC(dt);
        }

        for (int i = 0; i < data.size() ; i++) {
            for (int j = 0; j < data.size(); j++) {
                if (i !=j){
                    data.get(i).collision(data.get(j));
                }
            }
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).cV.plusMe(new Vector2d(0, G).x(dt));
            data.get(i).cA.xMe(0);
        }
    }
}
