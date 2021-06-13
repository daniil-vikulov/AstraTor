package Physics;

import Tools.*;
import java.awt.*;

public abstract class Body3d {
    public Vector3d c;
    public Vector3d cF;
    public Vector3d cA;
    public Vector3d cV;
    public double m;
    protected boolean ball3d = false;
    protected boolean poly3d = false;

    public Body3d(Vector3d c, double m) {
        this.c = c;
        this.cF = new Vector3d(0,0,0);
        this.cA = new Vector3d(0,0, 0);
        this.cV = new Vector3d(0,0, 0);
        this.m = m;
    }

    public void updateC(double dt){
        cA = cF.x(1/m);
        cV.plusMe(cA.x(dt));
        c.plusMe(cV.x(dt));
    }

    abstract public void collision(Body3d body3d);

    abstract public void attract(Body3d body3d);

    abstract public void draw(Graphics g);

    public boolean isBall3d(){
        return ball3d;
    }

    public boolean isPoly3d(){
        return poly3d;
    }
}