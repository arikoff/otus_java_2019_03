package ru.otus.homework;

import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        List<String> diYarrayList1 = DIYarrayListProxy.createProxedDIYarrayList();
        List<String> diYarrayList2 = DIYarrayListProxy.createProxedDIYarrayList(20);

        String[] elements = new String[10];

        for (int i = 0; i<10; i++){
            elements[i] = Integer.toString(new Random().nextInt(20));
        }

        java.util.Collections.addAll(diYarrayList1, elements);

        for (int i = 0; i<10; i++){
            diYarrayList1.add(5, elements[i]);
        }

        java.util.Collections.copy(diYarrayList2, diYarrayList1);
        java.util.Collections.sort(diYarrayList2, null);

    }

}
