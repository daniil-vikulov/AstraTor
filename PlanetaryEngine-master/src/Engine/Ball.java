package Engine;
import Graphics.*;
import PlanetaryEngine.PlanetaryEngine;
import com.sun.source.doctree.AttributeTree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

public class Ball extends Body {
    public Ball(double x, double y, double z, double vX, double vY, double vZ, int number) {
        super();
        c = new Vector(x, y, z);
        cV = new Vector(vX, vY, vZ);
        this.number = number;

    }

    public Ball(Ball ball) {
        super(ball);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (radius*PlanetaryEngine.zoom < 4){
            radius = 4/PlanetaryEngine.zoom;
        }
        Ellipse2D.Double circle = new Ellipse2D.Double(RenderEngine.engineToWindowX(c.x - radius),
                RenderEngine.engineToWindowY(c.y - radius),
                2 * radius*PlanetaryEngine.zoom,
                2 * radius*PlanetaryEngine.zoom
        );

        if (number == 0) {
            g2d.setColor(new Color(255, 255, 150));
        }
        if (number == 1){
            g2d.setColor(new Color(235,116,24));
        }
        if (number == 2){
            g2d.setColor(new Color(165,116,75));
        }
        if (number == 3){
            g2d.setColor(new Color(126, 142, 158));
        }
        if (number == 4){
            g2d.setColor(new Color(234,110,77));
        }
        if (number == 5){
            g2d.setColor(new Color(173,147,130));
        }
        if (number == 6){
            g2d.setColor(new Color(196,173,119));
        }
        if (number == 7){
            g2d.setColor(new Color(186,224,227));
        }
        if (number == 8){
            g2d.setColor(new Color(67,107,255));
        }
        if (number == 9){
            g2d.setColor(Color.gray);
        }


        g2d.fill(circle);
        g2d.setColor(Color.WHITE);

        for (int i = 0; i < trajectory.size() - 1; i++) {
            g2d.drawLine(RenderEngine.engineToWindowX(trajectory.get(i).x),
                    RenderEngine.engineToWindowY(trajectory.get(i).y),
                    RenderEngine.engineToWindowX(trajectory.get(i+1).x),
                    RenderEngine.engineToWindowY(trajectory.get(i+1).y));
        }//TODO
    }

    public boolean checkCollision(Ball b) {
        return radius + b.radius > c.distanceTo(b.c);
    }
}

