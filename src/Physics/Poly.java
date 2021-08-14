package Physics;

import Tools.Line;
import Tools.Vector;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Poly extends Body{
    public ArrayList<Vector> vertexes;
    Color color;
    BasicStroke basicStroke;
    boolean isStatic = false;
    private Material material;

    public Poly(Vector c, ArrayList<Vector> vertexes) {
        super(c, 1);
        findCentre(vertexes);
        this.vertexes = vertexes;
        sortVertexes();
    }

    public Poly() {
        super(null, 1);
        vertexes = new ArrayList<>();
    }

    public Poly(ArrayList<Vector> vertexes){
        super(null, 1);
        this.vertexes = vertexes;
        sortVertexes();
        findCentre(vertexes);
    }

    private void findCentre(ArrayList<Vector> vertexes) {
        if (vertexes.size() == 3){
            c = findCentreForTriangle(vertexes);
            //System.out.println(getTriangleSquare(vertexes));
        }else if (vertexes.size() > 3){
            double square = getSquare(vertexes);
            ArrayList<Vector> triangle = new ArrayList<>();
            ArrayList<Ball> centers = new ArrayList<>();
            for (int i = 1; i < vertexes.size()-1; i++) {
                if (triangle.size() != 0){
                    System.out.println("ERROR: findCentre()");
                }
                triangle.add(vertexes.get(0));
                triangle.add(vertexes.get(i));
                triangle.add(vertexes.get(i+1));
                centers.add(new Ball(findCentreForTriangle(triangle), m*getTriangleSquare(triangle)/square));
                //System.out.println(centers.get(centers.size()-1).c);
                //System.out.println(centers.get(centers.size()-1).m);
                //System.out.println(getSquare(triangle));
                triangle.clear();
            }
            Vector sum = new Vector(0,0);
            for (int i = 0; i < centers.size(); i++) {
                sum.plusMe(centers.get(i).c.x(centers.get(i).m));
                //System.out.println("size = " + centers.size());
                //System.out.println("c(i) = " + centers.get(i).c);
                //System.out.println("m(i) = " + centers.get(i).m);
                //sum.xMe(0.5);//centers.get(i).m
            }
            //System.out.println("c = " + sum);
            //System.out.println("m = " + m);
            c = sum.x(1/m);
        }
    }

    private void sortVertexes(){
        int mI = 0;
        for (int i = 1; i < vertexes.size(); i++) {
            if (vertexes.get(mI).x > vertexes.get(i).x){
                mI = i;
            }
        }

        Vector move = new Vector(vertexes.get(mI));
        for (int i = 0; i < vertexes.size(); i++) {
            vertexes.get(i).minusMe(move);
        }

        vertexes.remove(mI);

        Collections.sort(vertexes, new Comparator<>() {
            @Override
            public int compare(Vector v1, Vector v2) {
                double atan1 = Math.atan2(v1.y, v1.x);
                double atan2 = Math.atan2(v2.y, v2.x);
                if (atan1 < atan2){
                    return 1;
                }else if (atan1 > atan2){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for (int i = 0; i < vertexes.size(); i++) {
            vertexes.get(i).plusMe(move);
        }
        vertexes.add(0, move);
    }

    private double getSquare(ArrayList<Vector> vectors) {
        double square = 0;
        ArrayList<Vector> triangle = new ArrayList<>();
        for (int i = 1; i < vertexes.size()-1; i++) {
            triangle.add(vertexes.get(0));
            triangle.add(vertexes.get(i));
            triangle.add(vertexes.get(i+1));
            square+=getTriangleSquare(triangle);
            triangle.clear();
        }
        //System.out.println("square from gS = " + square);
        return square;
    }

    private Vector findCentreForTriangle(ArrayList<Vector> vertexes) {
        Vector move = new Vector(vertexes.get(0));
        for (int i = 0; i < 3; i++) {
            vertexes.get(i).minusMe(move);
        }
        //System.out.println(vertexes.get(0));
        //System.out.println(vertexes.get(1));
        //System.out.println(vertexes.get(2));
        double l1 = vertexes.get(1).length();
        double l2 = vertexes.get(2).length();
        Vector n1 = vertexes.get(1).norm().x(l1/2);
        Vector n2 = vertexes.get(2).norm().x(l2/2);
        Line med1 = new Line(vertexes.get(2), n1);
        Line med2 = new Line(vertexes.get(1), n2);
        //System.out.println(med1);
        //System.out.println(med2);
        //System.out.println(med1.intersection(med2).plus(move));
        for (int i = 0; i < 3; i++) {
            vertexes.get(i).plusMe(move);
        }
        return med1.intersection(med2).plus(move);
    }

    private double getTriangleSquare(ArrayList<Vector> vectors){
        if (vectors.size() == 3){
            double a = vectors.get(1).minus(vectors.get(0)).length();
            double b = vectors.get(1).minus(vectors.get(2)).length();
            double c = vectors.get(0).minus(vectors.get(2)).length();
            double p = (a + b + c)/2;
            double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));
            //System.out.println("S gts = " + s);
            return s;
        }else{
            System.out.println("ERROR: getTriangleSquare()");
            return 0;
        }
    }

    @Override
    public void collision(Body body) {

    }



    private boolean collidedWithBall(){
        return false;//TODO
    }

    private boolean collidedWithPoly(){
        return false;//TODO
    }

    @Override
    public void attract(Body body2d) {

    }

    @Override
    public void draw(Graphics g) {
        if (vertexes.size() > 2){
            int[] x = new int[vertexes.size()];
            int[] y = new int[vertexes.size()];
            for (int i = 0; i < vertexes.size(); i++) {
                x[i] = (int)vertexes.get(i).x;
                y[i] = (int)vertexes.get(i).y;
            }
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(new Color(37, 146, 198));
            //g2d.fillPolygon(x, y, vertexes.size());
            //g2d.setStroke(new BasicStroke(13, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawPolygon(x, y, vertexes.size());
            g2d.setColor(new Color(37, 146, 198));
            Ellipse2D.Double circle;
            //Ellipse2D.Double c1;
            //Ellipse2D.Double c2;
            //System.out.println(c);
            circle = new Ellipse2D.Double((int)(c.x - 2), (int)(c.y - 2), (4), (4));
            g2d.fill(circle);
/*            c1 = new Ellipse2D.Double(233 - 2, 367 - 2, 4, 4);
            c2 = new Ellipse2D.Double(367 - 2, 233 - 2, 4, 4);
            g2d.fill(c1);
            g2d.fill(c2);*/
        }
    }

    @Override
    public void collideWithLine() {

    }

    public void setBasicStroke(BasicStroke basicStroke){
        this.basicStroke = basicStroke;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void makeStatic(){
        isStatic = true;
    }
}