package main;

import Test.annotations.AfterSuite;
import Test.annotations.BeforeSuite;
import Test.annotations.Test;
import Test.TestStarted;

public class Main {
    public static void main(String[] args) {
        TestStarted.start(Main.class);
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test(priority = 9)
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 4)
    public void test3() {
        System.out.println("test3");
    }

    @Test
    public void test4() {
        System.out.println("test4");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }
}
