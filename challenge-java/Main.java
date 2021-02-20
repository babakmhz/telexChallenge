package com.android.telexchallenge.ui;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    private ArrayList<Integer> removeOddNumbersFromListJavaNativeWay(ArrayList<Integer> numbers) throws Exception {
        if (numbers == null || numbers.isEmpty())
            throw new RuntimeException();
        numbers.removeIf(n -> n % 2 != 0);
        return numbers;
    }

    private ArrayList<Integer> removeOddNumbersFromListCustomWay(ArrayList<Integer> numbers) throws Exception {
        if (numbers == null || numbers.isEmpty())
            throw new RuntimeException();

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 != 0)
                numbers.remove(i);
        }
        return numbers;
    }

}
