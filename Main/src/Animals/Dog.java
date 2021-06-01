package Animals;

public class Dog extends Animal {

    private static int count = 0;

    public Dog(String name) {
        this.name = name;
        init();
        newAnimal();
    }

    private void init() {
        this.maxRun = 500;
        this.maxSwim = 10;
    }

    @Override
    protected void newAnimal() {
        super.newAnimal();
        count++;
    }

    public static int count() {
        return count;
    }
}
