package Tools;

public class Vector {
    public double x;
    public double y;

    @Override
    public String toString() {
        return String.format("(%.6f, %.6f)", x, y);
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector plus(Vector v){
        return new Vector(x + v.x, y + v.y);
    }

    public void plusMe(Vector v){
        x+=v.x;
        y+=v.y;
    }

    public Vector minus(Vector v){
        return new Vector(x - v.x, y - v.y);
    }

    public void minusMe(Vector v){
        x-=v.x;
        y-=v.y;
    }

    public Vector x(double n){
        return new Vector(x*n, y*n);
    }

    public void xMe(double n){
        x*=n;
        y*=n;
    }

    public double distanceTo(Vector v){
        return Math.sqrt((v.y - this.y)*(v.y - this.y) + (v.x - this.x)*(v.x - this.x));
    }

    public Vector norm(){
        double length = this.length();
        if (length < 1e-3){
            return new Vector(0,0);
        }else{
            return new Vector(x/length, y/length);
        }
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

    public Vector rotate(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector(cos*x - sin*y, sin*x + cos*y);
    }

    public void rotateMe(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double xN = cos*x - sin*y;
        double yN = sin*x + cos*y;
        x = xN;
        y = yN;
    }

    public double project(Vector toP){
        double proj = this.length()*Math.cos(this.findAngle(toP));
        return  proj;
    }

    public double findAngle(Vector b){
        return Math.acos(this.dot(b)/(this.length()*b.length()));
    }

    public double dot(Vector b){
        return this.x*b.x + this.y*b.y;
    }

}