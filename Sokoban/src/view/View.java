package view;

import controller.Controller;
import controller.EventListener;
import model.GameObjects;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(
                this,
                "Level Completed!!!",
                "Completed.",
                 JOptionPane.INFORMATION_MESSAGE,
                 new ImageIcon("/home/albuquerque/Downloads/icon.png"));

        controller.startNextLevel();
    }
}
