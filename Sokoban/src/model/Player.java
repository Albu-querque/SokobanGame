package model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        graphics.fillOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }


}
