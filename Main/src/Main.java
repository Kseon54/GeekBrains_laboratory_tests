public class Main {
    public static void main(String[] args) {
        System.out.println(Main.AmountIsWithin10_20(10,5));
        System.out.println(Main.IsNegativeNumber(10));
        Main.WhatSignNumber(-10);
        Main.OutputSeveralTimes("Привет",5);
        System.out.println(Main.IsLeapYear(120));
    }

    public static boolean AmountIsWithin10_20(int a, int b){
        if(a+b<=10 && a+b>=20)  return true;
        else return false;
    }

    public static boolean IsNegativeNumber(int a){
        if (a<0) return true;
        else return false;
    }

    public static void WhatSignNumber(int a){
        if(Main.IsNegativeNumber(a))
            System.out.println("Число "+ a +" отрицательное");
        else
            System.out.println("Число "+ a +" положительное");
    }

    public static void OutputSeveralTimes(String str, int count){
        for (int i=0;i<count;i++){
            System.out.println(str);
        }
    }

    public static boolean IsLeapYear(int year){
        return year % 4==0 && (year % 100!=0 || year % 400==0);
    }
}
