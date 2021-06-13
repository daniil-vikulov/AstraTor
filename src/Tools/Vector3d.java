package Tools;

public class Vector3d {
    public double x;
    public double y;
    public double z;


    @Override
    public String toString() {
        return String.format("(%.6f, %.6f)", x, y);
    }

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d(Vector3d v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3d plus(Vector3d v){
        return new Vector3d(x + v.x, y + v.y, z + v.z);
    }

    public void plusMe(Vector3d v){
        x+=v.x;
        y+=v.y;
        z+=v.z;
    }

    public Vector3d minus(Vector3d v){
        return new Vector3d(x - v.x, y - v.y, z - v.y);
    }

    public void minusMe(Vector3d v){
        x-=v.x;
        y-=v.y;
        z-=v.z;
    }

    public Vector3d x(double n){
        return new Vector3d(x*n, y*n, z*n);
    }

    public void xMe(double n){
        x*=n;
        y*=n;
        z*=n;
    }

    public double distanceTo(Vector3d v){
        return Math.sqrt((v.y - this.y)*(v.y - this.y) + (v.x - this.x)*(v.x - this.x) + (v.z - this.z)*(v.z - this.z));
    }

    public void normMe(){
        double length = this.length();
        if (length < 1e-3){
            x = 0;
            y = 0;
            z = 0;
        }else{
            x/=length;
            y/=length;
            z/=length;
        }
    }

    public double length(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3d rotateX(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector3d(x, cos*y + sin*z, cos*z - sin*y);
    }

    public Vector3d rotateY(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector3d(cos*x + sin*z, y, cos*z - sin*x);
    }

    public Vector3d rotateZ(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Vector3d(cos*x - sin*y, sin*x + cos*y, z);
    }

    public void rotateMeX(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double xN = x;
        double yN = cos*y + sin*z;
        double zN = cos*z - sin*y;
        x = xN;
        y = yN;
        z = zN;
    }

    public void rotateMeY(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double xN = cos*x + sin*z;
        double yN = y;
        double zN = cos*z - sin*x;
        x = xN;
        y = yN;
        z = zN;
    }

    public void rotateMeZ(double angle){
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double xN = cos*x - sin*y;
        double yN = sin*x + cos*y;
        double zN = z;
        x = xN;
        y = yN;
        z = zN;
    }
}
