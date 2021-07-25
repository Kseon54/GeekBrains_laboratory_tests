package main;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    final int size = 10000000;
    final int h = size / 2;


    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        long start, end;

        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        start = System.currentTimeMillis();
        work(arr, 1);
        end = System.currentTimeMillis();
        System.out.println("1 поток " + (end - start));

        start = System.currentTimeMillis();
        work(arr, 2);
        end = System.currentTimeMillis();
        System.out.println("2 потока " + (end - start));

    }

    private float[] work(float[] arr, int countThreads) {
        if (countThreads < 1) throw new RuntimeException("Должен быть хотябы 1 поток");

        List<Thread> list = new ArrayList<>();
        int n = arr.length / countThreads;
        int unaccounted = arr.length % countThreads;

        for (int j = 0; j < countThreads; j++) {
            int start = n * j + (j == 0 ? 0 : 1);
            int end = n * (j + 1) + (j + 1 == countThreads ? unaccounted : 0);
            list.add(new Thread(() -> {
                for (int i = start; i < end; i++) {
                    arr[i] = (float) (arr[i] * Math.sin(0.2f + (float) i / 5) *
                            Math.cos(0.2f + (float) i / 5) *
                            Math.cos(0.4f + (float) i / 2));
                }
            }));
        }

        list.forEach(Thread::start);
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
