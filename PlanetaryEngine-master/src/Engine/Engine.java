package Engine;

import Graphics.*;
import PlanetaryEngine.PlanetaryEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Engine implements MouseListener, KeyEventDispatcher, MouseMotionListener, MouseWheelListener, ActionListener, KeyListener{
    public PlanetaryEngine planetaryEngine;
    public static boolean TIME_STOPPED = false;
    public RenderEngine renderEngine;
    public double startingTime;
    BufferedImage beforeIntro, intro, mainMenu, settingsMenu;
    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    public double speed = 1;
    public boolean inMainMenu = false, inSettingsMenu = false;
    public Graphics g;
    public MyPanel myPanel;
    int mX = 0;
    int mY = 0;
    public JButton mode1 = new JButton("Efficient mode");
    public JButton mode2 = new JButton("Balanced mode");
    public JButton mode3 = new JButton("High-quality mode");
    public JButton play = new JButton("Play");
    public JButton settings = new JButton("Settings");
    public JButton exit = new JButton("Exit");
    public JButton exit1 = new JButton("Exit");
    public JButton info = new JButton("i");
    public boolean inInfoMode = false;

    public Engine(MyPanel myPanel){
        this.myPanel = myPanel;
        play.setBounds(20,480,150,50);
        settings.setBounds(20,540,150,50);
        exit.setBounds(20,600,150,50);
        play.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);
        mode1.setBounds(20,480,150,50);
        mode2.setBounds(20,540,150,50);
        mode3.setBounds(20,600,150,50);
        exit1.setBounds(630, 600, 150, 50);
        mode1.addActionListener(this);
        mode2.addActionListener(this);
        mode3.addActionListener(this);
        exit1.addActionListener(this);
        info.setBounds(746,0,40,40);
        info.addActionListener(this);

        try {
            manager.addKeyEventDispatcher(this);

            /*this.beforeIntro = ImageIO.read(new File("res\\beforelogo.png"));
            this.intro = ImageIO.read(new File("res\\logo.png"));
            this.mainMenu = ImageIO.read(new File("res\\menuBackground.jpg"));
            this.settingsMenu = ImageIO.read(new File("res\\menuBackground.jpg"));*/

            this.beforeIntro = ImageIO.read(getClass().getClassLoader().getResourceAsStream("beforelogo.png"));
            this.intro = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo.png"));
            this.mainMenu = ImageIO.read(getClass().getClassLoader().getResourceAsStream("menuBackground.jpg"));
            this.settingsMenu = ImageIO.read(getClass().getClassLoader().getResourceAsStream("menuBackground.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Audio introSound = new Audio("IntroSound.wav", 0.7f);
        //introSound.run();

        startingTime = System.currentTimeMillis();
        planetaryEngine = new PlanetaryEngine();
        for (int i = 0; i < planetaryEngine.balls.size(); i++) {
            try {
                if (planetaryEngine.balls.get(i).number == 1){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("mercury.png"));
                           // ImageIO.read(new File("res\\mercury.png"));
                }
                if (planetaryEngine.balls.get(i).number == 3){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("earth.png"));
                          //  ImageIO.read(new File("res\\earth.png"));
                }
                if (planetaryEngine.balls.get(i).number == 0){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sun.png"));
                          //  ImageIO.read(new File("res\\sun.png"));
                }
                if (planetaryEngine.balls.get(i).number == 2){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("venus.png"));
                           // ImageIO.read(new File("res\\venus.png"));
                }

                if (planetaryEngine.balls.get(i).number == 4){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("mars.png"));
                            //ImageIO.read(new File("res\\mars.png"));
                }
                if (planetaryEngine.balls.get(i).number == 5){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("jupiter.png"));
                            //ImageIO.read(new File("res\\jupiter.png"));
                }
                if (planetaryEngine.balls.get(i).number == 6){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("saturn.png"));
                            //ImageIO.read(new File("res\\saturn.png"));
                }
                if (planetaryEngine.balls.get(i).number == 7){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("uran.jpg"));
                           // ImageIO.read(new File("res\\uran.jpg"));
                }
                if (planetaryEngine.balls.get(i).number == 8){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("neptune.png"));
                            //ImageIO.read(new File("res\\neptune.png"));
                }
                if (planetaryEngine.balls.get(i).number == 9){
                    planetaryEngine.balls.get(i).info = ImageIO.read(getClass().getClassLoader().getResourceAsStream("moon.png"));
                            //ImageIO.read(new File("res\\moon.png"));
                }
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
    }

    public void updateAll(double timePast) {
        if (!Engine.TIME_STOPPED) {
            planetaryEngine.updateAll(timePast);
        }
    }

    public void introAnimation(Graphics g){
        if (System.currentTimeMillis() - startingTime > 2000){
            g.drawImage(beforeIntro,0,0,null);
            g.drawImage(intro,-311,0, null);
        }else{
            g.drawImage(beforeIntro,-311,0,null);
        }
        myPanel.repaint();
    }

    public void mainMenu(){
        g.drawImage(mainMenu, -400,0, null);
    }

    public void settingsMenu(){
        g.drawImage(mainMenu, -400,0, null);
    }

    public void infoMode(){
            g.drawImage(planetaryEngine.balls.get(RenderEngine.INDEX_OF_FOCUSED_PLANET).info, 0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1){
                planetaryEngine.refocus(mouseEvent.getX(), mouseEvent.getY());
            }else if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                planetaryEngine.mousePressed = true;
                mX = mouseEvent.getX();
                mY = mouseEvent.getY();
            }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        planetaryEngine.mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
           speed *= 1.1;
            planetaryEngine.speedCoeff *= 1.1;
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            speed /= 1.1;
            planetaryEngine.speedCoeff /= 1.1;
            if (speed*planetaryEngine.speedCoeff < 1){
                speed = 1;
                planetaryEngine.speedCoeff = 1;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER
        ){
            inMainMenu = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_W){
            RenderEngine.angleX-=Math.PI/16;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            RenderEngine.angleX+=Math.PI/16;
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            RenderEngine.angleY+=Math.PI/16;
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            RenderEngine.angleY-=Math.PI/16;
        }

        return false;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        /*if (Math.abs(RenderEngine.angleY) > Math.PI/2){
            RenderEngine.angleY = Math.signum(RenderEngine.angleY)*Math.PI/2;
        }else if (Math.abs(RenderEngine.angleY - (mouseEvent.getX() - mX)*0.0005) < Math.PI/2) {
            RenderEngine.angleY -= (mouseEvent.getX() - mX)*0.0005;
        }

        if (Math.abs(RenderEngine.angleX) > Math.PI/2){
            RenderEngine.angleX = Math.signum(RenderEngine.angleX)*Math.PI/2;
        }else if (Math.abs(RenderEngine.angleX - (mouseEvent.getY() - mY)*0.0005) < Math.PI/2) {
            RenderEngine.angleX -= (mouseEvent.getY() - mY)*0.0005;
        }*/
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        planetaryEngine.zoom *= Math.pow(1.03, -mouseWheelEvent.getWheelRotation());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == play){
            inMainMenu = false;
            inSettingsMenu = false;
            Engine.TIME_STOPPED = false;
            myPanel.remove(play);
            myPanel.remove(settings);
            myPanel.remove(exit);
            myPanel.add(info);
        }

        if (actionEvent.getSource() == settings){
            inSettingsMenu = true;
            myPanel.remove(play);
            myPanel.remove(settings);
            myPanel.remove(exit);
            myPanel.add(mode1);
            myPanel.add(mode2);
            myPanel.add(mode3);
            myPanel.add(exit1);
            settingsMenu();
        }


        if (actionEvent.getSource() == exit){
            System.exit(1);
        }

        if (actionEvent.getSource() == exit1){
            inSettingsMenu = false;
            myPanel.remove(mode1);
            myPanel.remove(mode2);
            myPanel.remove(mode3);
            myPanel.remove(exit1);
            myPanel.add(play);
            myPanel.add(settings);
            myPanel.add(exit);
            mainMenu();
        }

        if (actionEvent.getSource() == info){
            if (!inInfoMode){
                Engine.TIME_STOPPED = true;
                inInfoMode = true;
                infoMode();
                System.out.println("info mode");
            }else{
                Engine.TIME_STOPPED = false;
                inInfoMode = false;
                System.out.println("continue");
            }
        }

        if (actionEvent.getSource() == mode1){
            speed = 1e-2;
            planetaryEngine.speedCoeff = 1e2;
        }

        if (actionEvent.getSource() == mode2){
            speed = 1;
            planetaryEngine.speedCoeff = 1;
        }

        if (actionEvent.getSource() == mode3){
            speed = 1e2;
            planetaryEngine.speedCoeff = 1e-2;
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}