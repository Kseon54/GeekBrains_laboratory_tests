package currents;

import base.Current;

public class Cat implements Current {
    public Cat(float maxRun, float maxJump) {
        setMaxJump(maxJump);
        setMaxRun(maxRun);
    }

    private float maxRun;
    private float maxJump;

    private boolean isParticipates = true;

    @Override
    public String jump(float sizeObstacle) {
        if (!isParticipates) return String.format("%s больще не учавствует", getClass().getName());

        isParticipates = maxJump >= sizeObstacle;
        return String.format("%s %s перепрыгнул через препятствие(%f)",
                getClass().getName(),
                isParticipates ? "" : "не",
                sizeObstacle);
    }

    @Override
    public String run(float sizeObstacle) {
        if (!isParticipates) return String.format("%s больще не учавствует", getClass().getName());

        isParticipates = maxRun >= sizeObstacle;
        return String.format("%s %s пробежал препятствие(%f)",
                getClass().getName(),
                isParticipates ? "" : "не",
                sizeObstacle);
    }

    public float getMaxRun() {
        return maxRun;
    }

    protected void setMaxRun(float maxRun) {
        if (isNegativeNumber(maxJump)) throw new IllegalArgumentException();
        this.maxRun = maxRun;
    }

    public float getMaxJump() {
        return maxJump;
    }

    protected void setMaxJump(float maxJump) {
        if (isNegativeNumber(maxJump)) throw new IllegalArgumentException();
        this.maxJump = maxJump;
    }

    private boolean isNegativeNumber(float number) {
        return number < 0;
    }

    public boolean isParticipates() {
        return isParticipates;
    }

    public void setParticipates(boolean participates) {
        this.isParticipates = participates;
    }
}
