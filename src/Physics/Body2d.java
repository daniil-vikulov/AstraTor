package Physics;

import Tools.*;
import java.awt.*;

public abstract class Body2d {
    public Vector2d c;
    public Vector2d cA;
    public Vector2d cV;
    public double m;

    public Body2d(Vector2d c, double m) {
        this.c = c;
        this.cA = new Vector2d(0,0);
        this.cV = new Vector2d(0,0);
        this.m = m;
    }

    public void updateC(double dt){
        cV.plusMe(cA.x(dt));
        c.plusMe(cV.x(dt));
    }

    abstract public void draw(Graphics g);
}