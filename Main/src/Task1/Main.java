package Task1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private final ABCPint abcPint = new ABCPint();

    public static void main(String[] args){
        new Main().task1();
    }

    public void task1(){
        createTread("A", "B").start();
        createTread("B", "C").start();
        createTread("C", "A").start();
    }

    private Thread createTread(String string, String nextString) {
        return new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                abcPint.print(string, nextString);
            }
        });
    }
}
