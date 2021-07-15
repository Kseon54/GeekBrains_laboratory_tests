package main;

import phoneDirectory.PhoneDirectory;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] strings = {"1", "1", "1", "2", "3", "4", "4", "4", "4", "5", "6"};
        uniqueWords(strings);

        System.out.print("\n\n\n");

        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.add("1", "11");
        phoneDirectory.add("1", "12");
        phoneDirectory.add("1", "13");
        phoneDirectory.add("1", "14");
        phoneDirectory.add("1", "15");
        phoneDirectory.add("2", "21");
        phoneDirectory.add("3", "31");
        phoneDirectory.add("3", "32");

        System.out.println(phoneDirectory.get("1"));

    }

    public static void uniqueWords(String[] strings) {
        Map<String, Integer> map = new HashMap<>();

        for (String s : strings) {
            map.merge(s, 1, Integer::sum);
        }

        map.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() == 1)
                .forEach(stringIntegerEntry -> System.out.print(stringIntegerEntry.getKey() + "\t"));
        System.out.println();
        map.forEach((key, value) -> System.out.print(key + " кол-во: " + value + "\n"));
    }
}
