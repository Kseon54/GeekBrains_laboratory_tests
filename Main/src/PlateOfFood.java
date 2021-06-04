public class PlateOfFood {
    private int countFood;

    public PlateOfFood() {
        this.countFood = 0;
    }

    public void addFood(int count) {
        countFood += count;
    }

    public int getCountFood() {
        return countFood;
    }

    public boolean isTakeFood(int count) {
        return count <= countFood;
    }

    public boolean eatFoThis(Animal animal) {
        if (isTakeFood(animal.getConsumption())) {
            countFood -= animal.getConsumption();
            return true;
        }
        return false;
    }
}
