package Tools;

import java.awt.*;

public class Line2d {
    Vector2d p1, p2;

    public Line2d(Vector2d p1, Vector2d p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void draw(Graphics g){
        g.setColor(Color.gray);
        g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
    }
}
