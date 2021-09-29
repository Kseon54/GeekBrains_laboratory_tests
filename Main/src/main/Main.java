package main;

import Fruits.Apple;
import Fruits.Box;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Apple[] apples = {new Apple(), new Apple(), new Apple()};
        String str = apples[0].toString();
        replacingElements(apples, 0, 2);
        System.out.println(str.equals(apples[0].toString()));
        ArrayList list = arrayToArrayList(apples);


        Box<Apple> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();

        for (int i = 0; i < 10; i++) box1.add(new Apple());
        System.out.println("Вес первой коробки: " + box1.getWeight());
        for (int i = 0; i < 5; i++) box2.add(new Apple());
        System.out.println("Вес второй коробки: " + box2.getWeight());
        box1.transfer(box2);
        System.out.println("Вес первой коробки: " + box1.getWeight());
    }

    public static <T> void replacingElements(T[] array, int item_number_one, int item_number_twy) {
        T temp = array[item_number_one];
        array[item_number_one] = array[item_number_twy];
        array[item_number_twy] = temp;
    }

    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}

