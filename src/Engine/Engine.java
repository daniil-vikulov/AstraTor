package Engine;

import Physics.*;
import Tools.*;

import java.awt.*;
import java.util.ArrayList;

public class Engine {
    public ArrayList<Body3d> data = new ArrayList<>();//TODO
    public static double G = 0;

    public Engine() { //TODO
        data.add(new Ball3d(new Vector3d(100,300,0), 1, 10, Color.gray));
        data.add(new Ball3d(new Vector3d(200,300, 0), 1, 10, Color.gray));
        data.get(data.size()-1).cV = new Vector3d(-10, 0, 0);
    }

    public void update(double dt){
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                data.get(i).attract(data.get(j));
            }
        }

        for (int i = 0; i < data.size() ; i++) {
            for (int j = 0; j < data.size(); j++) {
                if (i !=j){
                    data.get(i).collision(data.get(j));
                }
            }
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).updateC(dt);
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).cV.plusMe(new Vector3d(0, G, 0).x(dt));
            data.get(i).cF.xMe(0);
            data.get(i).cA.xMe(0);
        }
        System.out.println();
    }
}
