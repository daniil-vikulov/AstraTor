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
}
