package Animals;

public class Dog extends Animal {

    private static int count = 0;

    public Dog(String name) {
        this.name = name;
        init();
        count++;
    }

    private void init() {
        this.maxRun = 500;
        this.maxSwim = 10;
    }

    public static int count() {
        return count;
    }
}
