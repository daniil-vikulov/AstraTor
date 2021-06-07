package Graphics;

import Engine.Engine;

import javax.swing.*;

import java.awt.event.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static int WIDTH = 800;
    public static int HEIGHT = 800;
    private static JFrame frame = new JFrame();
    private static MyPanel panel = new MyPanel();

    public static void main(String[]args) {

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                    Engine.TIME_STOPPED = !Engine.TIME_STOPPED;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        JMenuBar menuBar = new JMenuBar(); // Создание строки главного меню
        menuBar.add(createFileMenu()); // Добавление в главное меню выпадающих пунктов
        frame.setJMenuBar(menuBar); // Подключаем меню к интерфейсу приложения
        frame.add(panel);
        frame.setVisible(true);
        while (true) {
            frame.repaint();
        }
    }

    private static JMenu createFileMenu() // создание меню приложения
    {
        JMenu file = new JMenu("Menu"); // Создание выпадающего меню
        JMenuItem exit = new JMenuItem("Menu"); // Пункт меню "Выход"
        file.add(exit); // Добавим в меню пункт "Выход"
        exit.addActionListener(new ActionListener() { // Действие при выборе меню "Выход"
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Engine.TIME_STOPPED = true;
                panel.engine.inMainMenu = true;
                panel.remove(panel.engine.info);
                panel.add(panel.engine.play);
                panel.add(panel.engine.settings);
                panel.add(panel.engine.exit);
            }
        });
        return file; // возвращаем построенное меню как результат метода
    }
}