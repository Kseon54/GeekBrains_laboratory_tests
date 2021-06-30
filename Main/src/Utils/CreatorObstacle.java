package Utils;

import Obstacles.Wall;
import Obstacles.Treadmill;
import base.ObstacleOneParameter;

public class CreatorObstacle {
    public final float WALL_MAX_HEIGHT = 7;
    public final float Treadmill_MAX_LENGTH = 7;

    public Wall createWall(){
        return new Wall(Rnd.randomFloat(WALL_MAX_HEIGHT));
    }

    public Treadmill createThrowable(){
        return new Treadmill(Rnd.randomFloat(Treadmill_MAX_LENGTH));
    }

    public ObstacleOneParameter createObstacle(){
        float type = (float) Math.random();
        if (type <= 0.5) {
            return createWall();
        }
        return createThrowable();
    }
}
