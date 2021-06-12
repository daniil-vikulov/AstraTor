package Physics;

import Tools.*;
import java.awt.*;

public abstract class Body3d {
    public Vector3d c;
    public Vector3d cA;
    public Vector3d cV;
    public double m;

    public Body3d(Vector3d c, double m) {
        this.c = c;
        this.cA = new Vector3d(0,0, 0);
        this.cV = new Vector3d(0,0, 0);
        this.m = m;
    }

    public void updateC(double dt){
        cV.plusMe(cA.x(dt));
        c.plusMe(cV.x(dt));
    }

    abstract public void draw(Graphics g);
}