package ru.otus.homework.tests;

import ru.otus.homework.core.annotations.After;
import ru.otus.homework.core.annotations.Before;
import ru.otus.homework.core.annotations.Test;
import ru.otus.homework.testsource.DIYarrayList;

import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

public class AnnotationsTest {

    DIYarrayList diYarrayList1;
    DIYarrayList diYarrayList2;
    int num; //для проверки работы исключения на Before

    public AnnotationsTest(int num) {
        this.num = num;
    }

    @Before
    public void BeforeTest_addAll(){
        if (num == 2) {
            int a = 1/0;
        }
        else {
            diYarrayList1 = new DIYarrayList();
            String[] elements = new String[100];
            for(int i = 0; i < 100; ++i) {
                elements[i] = Integer.toString((new Random()).nextInt(100));
            }
            Collections.addAll(diYarrayList1, elements);
        }
    }

    @Test
    public void Test_negativeSize(){
        try {
            diYarrayList2 = new DIYarrayList<String>(-1);
            System.out.println("Test_negativeSize - ok");
        }
        catch (Exception e) {
            System.out.println("Test_negativeSize - исключение: " + e.getMessage());
        }
    }

    @Test
    public void Test_copy1(){
        try {
            diYarrayList2 = new DIYarrayList<String>(90);
            Collections.copy(diYarrayList2, diYarrayList1);
            System.out.println("Test_copy1 - ok");
        }
        catch (Exception e) {
            System.out.println("Test_copy1 - исключение: " + e.getMessage());
        }
    }

    @Test
    public void Test_copy2(){
        try {
            diYarrayList2 = new DIYarrayList<String>(100);
            Collections.copy(diYarrayList2, diYarrayList1);
            System.out.println("Test_copy2 - ok");
        }
        catch (Exception e) {
            System.out.println("Test_copy2 - исключение: " + e.getMessage());
        }
    }

    @Test
    public void Test_sort(){
        try {
            diYarrayList2 = new DIYarrayList<String>(100);
            Collections.copy(diYarrayList2, diYarrayList1);
            Collections.sort(diYarrayList2, null);
            System.out.println("Test_sort - ok");
        }
        catch (Exception e) {
            System.out.println("Test_sort - исключение: " + e.getMessage());
        }
    }

    @Test
    public void Test_hasNext(){
        try {
            ListIterator iterator = diYarrayList1.listIterator();
            iterator.hasNext();
            System.out.println("Test_hasNext - ok");
        }
        catch (Exception e) {
            System.out.println("Test_hasNext - исключение: " + e.getMessage());
        }
    }

    @Test
    public void Test_hasPrevious(){
        try {
            ListIterator iterator = diYarrayList1.listIterator();
            iterator.hasPrevious();
            System.out.println("Test_hasPrevious - ok");
        }
        catch (Exception e) {
            System.out.println("Test_hasPrevious - исключение: " + e.getMessage());
        }
    }
    @After
    public void AfterTest(){
        try {
            System.out.println("After - ok");
        }
        catch (Exception e) {
            System.out.println("After - исключение: " + e.getMessage());
        }
    }

}
