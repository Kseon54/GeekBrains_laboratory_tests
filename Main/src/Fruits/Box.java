package Fruits;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private final ArrayList<T> array = new ArrayList<>();

    public void add(T element) {
        array.add(element);
    }

    public void transfer(Box<T> box){
        array.addAll(box.getArray());
        box.clear();
    }

    public float getWeight() {
        float weight = 0;
        for (T t : array) {
            weight += t.getWeight();
        }
        return weight;
    }

    public void clear() {
        array.clear();
    }

    public ArrayList<T> getArray() {
        return array;
    }
}
