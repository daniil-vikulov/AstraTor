package Physics;

import Tools.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class Ball3d extends Body3d{
    public double radius;
    private Color color;

    public Ball3d(Vector3d c, double m, double radius, Color color) {
        super(c, m);
        ball3d = true;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void collision(Body3d body3d) {
        if (body3d.ball3d) {
            Ball3d b = (Ball3d)body3d;
            double distance = c.distanceTo(b.c);
            if (distance < radius + b.radius){
                double d = distance/(radius + b.radius);
                double G = -Math.pow(d, -30) + 1;
                G*=1e3;
                Vector3d f = b.c.minus(c).x(G*m*b.m/Math.pow(distance, 3));
                cF.plusMe(f);
                b.cF.minusMe(f);
                System.out.println(f);
                cV.xMe(0.99);
                b.cV.xMe(0.99);
            }
        }
    }

    @Override
    public void attract(Body3d body3d) {
        if (body3d.ball3d){
            Ball3d b = (Ball3d)body3d;
            double distance = c.distanceTo(b.c);
            if (distance > radius + b.radius){
                double G = 1e2;
                Vector3d f = b.c.minus(c).x(G*m*b.m/Math.pow(distance, 3));
                cF.plusMe(f);
                b.cF.minusMe(f);
            }
        }else{
            //Will be added soon
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
