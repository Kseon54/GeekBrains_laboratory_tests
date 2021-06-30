package Utils;

public class Rnd {

    public static float randomFloat(float min, float max){
        return (float) (Math.random()*((max-min)+1))+min;
    }

    public static float randomFloat(float max){
        return (float) (Math.random()*max);
    }
}
