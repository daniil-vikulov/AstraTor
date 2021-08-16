package Tools;

import java.awt.*;
import java.util.Random;

public class Line {
    private Vector p1;
    private Vector p2;
    double k;
    double m;
    double a;
    double b;
    double c;

    public Line(Vector p1, Vector p2) {
        this.p1 = p1;
        this.p2 = p2;
        a = p1.y - p2.y;
        b = p2.x - p1.x;
        c = p1.x*p2.y - p2.x*p1.y;
        k = -a/b;
        if (Math.abs(k) == 1.0/0.0){
            Random r = new Random();
            p2.plusMe(new Vector(2*(r.nextDouble()-0.5), 2*(r.nextDouble()-0.5)));
            a = p1.y - p2.y;
            b = p2.x - p1.x;
            k = -a/b;
        }
        this.m = - c/b;
    }

    @Override
    public String toString() {
        return "Line{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", k=" + k +
                ", b=" + m +
                '}';
    }

    public Vector intersection(Line l){
        double xN = (l.m - this.m)/(this.k - l.k);
        double yN = (this.k*l.m - l.k*this.m)/(this.k - l.k);
        //System.out.println("x y " + xN + " " + yN);
        return new Vector(xN,yN);
    }

    public void draw(Graphics g){
        g.setColor(Color.gray);
        g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
    }

    public Vector getNorm(){
        Vector v = new Vector(k, -1);
        v.normMe();
        return v;
    }

    public Vector convertToVector(){
        return p2.minus(p1);
    }

    public double distanceToPoint(Vector p){
        double x = p.x;
        double y = p.y;
        double d = Math.abs(a*x + b*y + c)/Math.sqrt(a*a + b*b);
        return d;
    }
}
