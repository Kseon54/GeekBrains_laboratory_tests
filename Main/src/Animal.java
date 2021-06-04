

public abstract class Animal implements IEat{
    protected boolean satiety;
    protected  int consumption;

    public boolean isSatiety() {
        return satiety;
    }

    public int getConsumption() {
        return consumption;
    }

    @Override
    public abstract void eat(PlateOfFood plateOfFood);
}
