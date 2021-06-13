package Physics;

import Tools.Vector3d;

import java.awt.*;

public class Ball3d extends Body3d{
    public Ball3d(Vector3d c, double m) {
        super(c, m);
    }

    @Override
    public void collision(Body3d body3d) {

    }

    @Override
    public void attract(Body3d body3d) {
        if (body3d.ball3d){

        }else{
            //Will be added soon
        }
    }

    @Override
    public void draw(Graphics g) {

    }
}
