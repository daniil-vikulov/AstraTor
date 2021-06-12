package Main;

import javax.swing.*;
import Graphics.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    public static void main(String[]args) {
        JFrame introF = new JFrame();
        HelperPanel introP = new HelperPanel();
        introF.setSize(800, 450);
        introF.setUndecorated(true);
        introF.setLocation(300,200);
        introF.add(introP);
        introF.setVisible(true);
        while (!introP.isReady()) {
            introP.repaint();
        }
        introF.setVisible(false);
        JFrame frame = new JFrame();
        MyPanel panel = new MyPanel();
        frame.setSize(WIDTH+13, HEIGHT+37);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        while (true) {
            frame.repaint();
        }
    }
}
