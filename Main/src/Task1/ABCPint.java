package Task1;

public class ABCPint {

    String nextString = "A";

    public void print(String string, String nextString) {
        synchronized (this) {
            while (!string.equals(this.nextString)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.nextString = nextString;
            System.out.print(string);
            notifyAll();
        }
    }
}
