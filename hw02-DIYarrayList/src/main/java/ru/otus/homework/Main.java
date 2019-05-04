package ru.otus.homework;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        DIYarrayList<String> diYarrayList1 = new DIYarrayList<String>();
        DIYarrayList<String> diYarrayList2 = new DIYarrayList<String>(100);

        String[] elements = new String[100];

        for (int i = 0; i<100; i++){
            elements[i] = Integer.toString(new Random().nextInt(100));
        }

        java.util.Collections.addAll(diYarrayList1, elements);
        java.util.Collections.copy(diYarrayList2, diYarrayList1);
        java.util.Collections.sort(diYarrayList2, null);

    }

}
