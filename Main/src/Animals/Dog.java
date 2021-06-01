package Animals;

public class Dog extends Animal {

    private static int count = 0;

    public Dog(String name) {
        super(name);
        init();
        count++;
    }

    private void init() {
        this.setMaxRun(500);
        this.setMaxSwim(10);
    }

    public static int count() {
        return count;
    }
}
