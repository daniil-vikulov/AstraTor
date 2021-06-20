package Physics;

import java.awt.*;

public class Material {
    private double k;
    private Color color;

    public Material(double k, Color color) {
        this.k = k;
        this.color = color;
    }

    public double getK() {
        return k;
    }

    public Color getColor() {
        return color;
    }
}
