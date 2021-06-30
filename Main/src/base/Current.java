package base;

import actions.Jumping;
import actions.Running;

public abstract class Current implements Running, Jumping {
    private String name;

    private float maxRun;
    private float maxJump;

    private boolean isParticipates = true;

    public Current(String name) {
        this.name = name;
    }

    public Current() {
    }

    @Override
    public String jump(float sizeObstacle) {
        if (!isParticipates) return String.format("%s %s больще не учавствует", getClass().getName(), name);

        isParticipates = maxJump >= sizeObstacle;
        return String.format("%s %s %s перепрыгнул через препятствие(%f)",
                getClass().getName(),
                name == null ? "" : name,
                isParticipates ? "" : "не",
                sizeObstacle);
    }

    @Override
    public String run(float sizeObstacle) {
        if (!isParticipates) return String.format("%s %s больще не учавствует", getClass().getName(), name);

        isParticipates = maxRun >= sizeObstacle;
        return String.format("%s %s %s пробежал препятствие(%f)",
                getClass().getName(),
                name == null ? "" : name,
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Current{" +
                "name='" + name + '\'' +
                ", maxRun=" + maxRun +
                ", maxJump=" + maxJump +
                ", isParticipates=" + isParticipates +
                '}';
    }
}
