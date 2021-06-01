package Animals;

public abstract class Animal implements ISwim, IRun {

    private static int count = 0;

    protected float maxRun;
    protected float maxSwim;
    protected String name;

    @Override
    public void swim(float distance) {
        System.out.printf("%s%s проплыл  %.2f м.\n", name, distance > maxSwim ? " не" : "", distance);
    }

    @Override
    public void run(float distance) {
        System.out.printf("%s%s бробежал  %.2f м.\n", name, distance >  maxRun? " не" : "", distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxRun() {
        return maxRun;
    }

    public float getMaxSwim() {
        return maxSwim;
    }

    protected void newAnimal() {
        count++;
    }

    public static int count() {
        return count;
    }
}
