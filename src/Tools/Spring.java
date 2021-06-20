package Tools;

import Physics.Body;
import java.awt.*;

public class Spring {
    private final double k;
    private final double length;
    private Body b1;
    private Body b2;

    public Spring(Body b1, Body b2, double k, double length) {
        this.k = k;
        this.length = length;
    }

    public double getK() {
        return k;
    }

    public double getLength() {
        return length;
    }

    public void draw(Graphics g){
        g.drawLine((int)b1.c.x, (int)b1.c.y, (int)b2.c.x, (int)b2.c.y);
    }
}
