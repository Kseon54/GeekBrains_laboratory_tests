package base;

public abstract class ObstacleOneParameter {

    private float obstacleParameter;

    public float getObstacleParameter() {
        return obstacleParameter;
    }

    protected void setObstacleParameter(float obstacleParameter) {
        if (isNegativeNumber(obstacleParameter)) throw new IllegalArgumentException();
        this.obstacleParameter = obstacleParameter;
    }

    private boolean isNegativeNumber(float number) {
        return number < 0;
    }

    public abstract String accomplishment(Current current);
}
