package Animals;

public class Cat extends Animal{

    private static int count = 0;

    public Cat(String name) {
        this.name = name;
        init();
        newAnimal();
    }

    private void init() {
        this.maxRun = 500;
        this.maxSwim = 0;
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
