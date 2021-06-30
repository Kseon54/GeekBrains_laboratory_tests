package Obstacles;

import base.Current;
import base.ObstacleOneParameter;

public class Treadmill extends ObstacleOneParameter {
    public Treadmill(float length) {
        setObstacleParameter(length);
    }

    @Override
    public String accomplishment(Current current) {
        return current.run(getObstacleParameter());
    }
}
