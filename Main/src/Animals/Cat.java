package Animals;

public class Cat extends Animal {

    private static int count = 0;

    public Cat(String name) {
        super(name);
        init();
        count++;
    }

    private void init() {
        this.setMaxRun(200);
        this.setMaxSwim(0);
    }

    public static int count() {
        return count;
    }
}
