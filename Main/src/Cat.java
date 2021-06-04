public class Cat extends Animal{

    public Cat() {
        satiety=false;
        consumption = Rnd.nextInt(4,25);
    }

    @Override
    public void eat(PlateOfFood plateOfFood) {
            satiety = plateOfFood.eatFoThis(this);
    }
}
