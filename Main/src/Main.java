import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlateOfFood plateOfFood = new PlateOfFood();
        plateOfFood.addFood(500);
        List<Animal> list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            list.add(new Cat());

        list.forEach(animal -> {
            animal.eat(plateOfFood);
            System.out.printf("Кот сыт? %b; \t В миске было: %d; \t Кот потребляет: %d\n",
                    animal.isSatiety(),
                    plateOfFood.getCountFood() + (animal.isSatiety() ? animal.getConsumption() : 0),
                    animal.getConsumption());
        });

    }
}
