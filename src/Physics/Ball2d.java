package Physics;

import Tools.Vector2d;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball2d extends Body2d{
    private double radius;
    private Color color;

    public Ball2d(Vector2d c, double m, double radius, Color color) {
        super(c, m);
        ball2d = true;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void collision(Body2d body2d) {
        if (body2d.ball2d){
            Ball2d b = (Ball2d)body2d;
            if (c.distanceTo(b.c) < radius + b.radius){
                double push = (radius + b.radius - c.distanceTo(b.c))/2;
                Vector2d axis = new Vector2d(b.c.x - c.x, b.c.y - c.y);
                Vector2d an = new Vector2d(axis.x/Math.sqrt(axis.x*axis.x + axis.y*axis.y),axis.y/Math.sqrt(axis.x*axis.x + axis.y*axis.y));
                double a = Math.sqrt(an.x*an.x + an.y*an.y);
                double cos = an.x/a;
                double sin = an.y/a;
                c.x-=push*cos;
                c.y-=push*sin;
                b.c.x+=push*cos;
                b.c.y+=push*sin;
                Vector2d vR1 = new Vector2d(cV.x*cos + cV.y*sin, -cV.x*sin + cV.y*cos);
                Vector2d vR2 = new Vector2d(b.cV.x*cos + b.cV.y*sin, -b.cV.x*sin + b.cV.y*cos);
                Vector2d uR1 = new Vector2d((vR1.x*(m - b.m) + 2*b.m*vR2.x)/(m + b.m), vR1.y);
                Vector2d uR2 = new Vector2d((vR2.x*(b.m - m) + 2*m*vR1.x)/(m + b.m), vR2.y);
                Vector2d u1 = new Vector2d(uR1.x*cos - uR1.y*sin, uR1.x*sin + uR1.y*cos);
                Vector2d u2 = new Vector2d(uR2.x*cos - uR2.y*sin, uR2.x*sin + uR2.y*cos);
                cV = u1.x(0.996);
                b.cV = u2.x(0.996);
            }
        }
    }

    @Override
    public void attract(Body2d b) {
        if (ball2d){
            if (b.ball2d){
                double distance = c.distanceTo(b.c);
                double G = 1e4;
                Vector2d a = b.c.minus(c).x(G*m*b.m/Math.pow(distance, 3));
                cA.plusMe(a);
                b.cA.minusMe(a);
            }else{
                //Will be added soon
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        Ellipse2D.Double circle;
            circle = new Ellipse2D.Double((int)(c.x - radius), (int)(c.y - radius), (int)(2*radius), (int)(2*radius));
        g2d.fill(circle);
    }
}
