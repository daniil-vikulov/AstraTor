package Physics;

import Tools.*;
import java.awt.*;

public abstract class Body {
    public Vector c;
    public Vector cA;
    public Vector cF;
    public Vector cV;
    public double m;
    protected boolean ball = false;
    protected boolean poly = false;
    protected Material material;

    public Body(Vector c, double m) {
        this.c = c;
        this.cF = new Vector(0,0);
        this.cA = new Vector(0,0);
        this.cV = new Vector(0,0);
        this.m = m;
    }

    public void updateC(double dt){
        if (ball){
            cA = cF.x(1/m);
            cV.plusMe(cA.x(dt));
            c.plusMe(cV.x(dt));
            cF.xMe(0);
            cA.xMe(0);
        }
    }

    public Material getMaterial() {
        return material;
    }

    abstract public void collision(Body body);

    abstract public void attract(Body body);

    abstract public void draw(Graphics g);

    public boolean isBall(){
        return ball;
    }

    public boolean isPoly(){
        return poly;
    }

    public abstract void collideWithLine();
}