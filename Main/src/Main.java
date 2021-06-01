import Animals.*;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Dog");
        Cat cat = new Cat("Cat");
        dog.run(500);
        cat.run(100);
        dog.swim(10);
        cat.swim(10);
        System.out.println("Всего собак: " + Dog.count());
        System.out.println("Всего котов: " + Cat.count());
        System.out.println("Всего животных: " + Animal.count());
    }
}
