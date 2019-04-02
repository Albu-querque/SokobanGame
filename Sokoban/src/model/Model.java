package model;

import controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("/home/albuquerque/Documents/Sokoban/src/res/levels.txt"));
    private EventListener eventListener;


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if(checkWallCollision(player, direction))
            return;
        else if(checkBoxCollisionAndMoveIfAvaliable(direction))
            return;


        switch (direction) {
            case LEFT  : player.move(-FIELD_CELL_SIZE, 0); break;
            case RIGHT : player.move(FIELD_CELL_SIZE, 0);  break;
            case UP    : player.move(0, -FIELD_CELL_SIZE); break;
            case DOWN  : player.move(0, FIELD_CELL_SIZE);  break;
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return gameObjects.getWalls()
                          .stream()
                          .anyMatch(i -> gameObject.isCollision(i, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){
        Box box = gameObjects.getBoxes()
                             .stream()
                             .filter(i -> gameObjects.getPlayer().isCollision(i, direction))
                             .findFirst()
                             .orElse(null);

        if(box == null)
            return false;

        boolean isCollisionBox = gameObjects.getBoxes()
                                            .stream()
                                            .filter(j -> j != box)
                                            .anyMatch(i -> box.isCollision(i, direction));

        boolean isCollisionWall = checkWallCollision(box, direction);

        if(isCollisionBox || isCollisionWall)
            return true;

        switch (direction) {
            case LEFT  : box.move(-FIELD_CELL_SIZE, 0); break;
            case RIGHT : box.move(FIELD_CELL_SIZE, 0);  break;
            case UP    : box.move(0, -FIELD_CELL_SIZE); break;
            case DOWN  : box.move(0, FIELD_CELL_SIZE);  break;
        }

        return false;
    }

    public void checkCompletion() {
        int count = 0;
        for (Box box : gameObjects.getBoxes()) {
            for (Home home : gameObjects.getHomes())
                if (box.getX() == home.getX() && box.getY() == home.getY())
                    count++;
        }
        if (count == gameObjects.getBoxes().size())
            eventListener.levelCompleted(currentLevel);

    }
}

