package Tools;

public class Vector2d {
    public double x;
    public double y;

    @Override
    public String toString() {
        return String.format("(%.6f, %.6f)", x, y);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2d plus(Vector2d v){
        return new Vector2d(x + v.x, y + v.y);
    }

    public void plusMe(Vector2d v){
        x+=v.x;
        y+=v.y;
    }

    public Vector2d minus(Vector2d v){
        return new Vector2d(x - v.x, y - v.y);
    }

    public void minusMe(Vector2d v){
        x-=v.x;
        y-=v.y;
    }

    public Vector2d x(double n){
        return new Vector2d(x*n, y*n);
    }

    public void xMe(double n){
        x*=n;
        y*=n;
    }

    public double distanceTo(Vector2d v){
        return Math.sqrt((v.y - this.y)*(v.y - this.y) + (v.x - this.x)*(v.x - this.x));
    }

    public void normMe(){
        double length = this.length();
        if (length < 1e-3){
            x = 0;
            y = 0;
        }else{
            x/=length;
            y/=length;
        }
    }

    public double length(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2d rotate(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector2d(cos*x - sin*y, sin*x + cos*y);
    }

    public void rotateMe(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double xN = cos*x - sin*y;
        double yN = sin*x + cos*y;
        x = xN;
        y = yN;
    }

}