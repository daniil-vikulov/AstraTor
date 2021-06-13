package Physics;

import java.awt.*;

public class Fluid {
    private double repRad, attRad, gravity, start, end;
    private Color color;

    public Fluid(double repRad, double attRad, Color color, double gravity, double start, double end) {
        this.repRad = repRad;
        this.attRad = attRad;
        this.color = color;
        this.gravity = gravity;
        this.start = start;
        this.end = end;
    }

    public double getRepRad() {
        return repRad;
    }

    public double getAttRad() {
        return attRad;
    }

    public Color getColor() {
        return color;
    }

    public double getGravity() {
        return gravity;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }
}
