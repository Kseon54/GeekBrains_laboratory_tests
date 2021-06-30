package Obstacles;

import base.Current;
import base.ObstacleOneParameter;

public class Wall extends ObstacleOneParameter {
    public Wall(float height) {
        setObstacleParameter(height);
    }

    @Override
    public String accomplishment(Current current) {
        return current.jump(getObstacleParameter());
    }
}
