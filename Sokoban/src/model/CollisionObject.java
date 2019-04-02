package model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public CollisionObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public boolean isCollision(GameObject gameObject, Direction direction) {
        if(direction.equals(Direction.LEFT)         && getX() - Model.FIELD_CELL_SIZE == gameObject.getX()
                                                    && getY() == gameObject.getY()) {
            return true;
        } else if(direction.equals(Direction.RIGHT) && getX() + Model.FIELD_CELL_SIZE == gameObject.getX()
                                                    && getY() == gameObject.getY()) {
            return true;
        } else if(direction.equals(Direction.UP)    && getX() == gameObject.getX()
                                                    && getY() - Model.FIELD_CELL_SIZE == gameObject.getY()) {
            return true;
        } else if(direction.equals(Direction.DOWN)  && getX() == gameObject.getX()
                                                    && getY() + Model.FIELD_CELL_SIZE == gameObject.getY()) {
            return true;
        }
        return false;
    }
}
