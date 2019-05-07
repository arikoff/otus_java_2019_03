package ru.otus.homework;

import lombok.SneakyThrows;
import ru.otus.homework.core.AnnotationsTestLauncher;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationsTestLauncher.launch("ru.otus.homework.tests.AnnotationsTest");
    }
}

