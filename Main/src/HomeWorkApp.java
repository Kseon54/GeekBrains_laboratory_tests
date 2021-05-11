public class HomeWorkApp {

    public static void main(String[] args) {
        HomeWorkApp.printThreeWords();
        HomeWorkApp.checkSumSign();
        HomeWorkApp.printColor();
        HomeWorkApp.compareNumbers();
    }

    public static  void printThreeWords(){
        System.out.println("_Orange\n_Banana\n_Apple");
    }

    public static  void checkSumSign(){
        int a = 0;
        int b = 0;
        if (a+b>=0){
            System.out.println("Сумма положительная");
        }else {
            System.out.println("Сумма отрицательна");
        }
    }

    public static  void printColor(){
        int value = 0;
        if (value<=0){
            System.out.println("Красный");
            return;
        }
        if (value>0&&value<=100){
            System.out.println("Желтый");
            return;
        }
        if (value>100){
            System.out.println("Зеленый");
            return;
        }
    }

    public static  void compareNumbers(){
        int a = 0;
        int b = 0;
        if (a>=b){
            System.out.println("a>=b");
        }else {
            System.out.println("a<b");
        }
    }
}
