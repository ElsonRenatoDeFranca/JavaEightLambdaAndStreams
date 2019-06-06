package com.javaeight.examples.demoapp;

/**
 * Created by e068635 on 6/6/2019.
 */
public class MethodReferenceExample {

    public static int compareByAge(Employee first, Employee second) {
        return Integer.compare(first.getAge(), second.getAge());
    }
}
