package Animals;

public class Cat extends Animal{

    private static int count = 0;

    public Cat(String name) {
        this.name = name;
        init();
        count++;
    }

    private void init() {
        this.maxRun = 500;
        this.maxSwim = 0;
    }

    public static int count() {
        return count;
    }
}
