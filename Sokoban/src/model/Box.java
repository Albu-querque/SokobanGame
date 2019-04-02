package model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    public Box(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawRect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        graphics.fillRect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }
}
