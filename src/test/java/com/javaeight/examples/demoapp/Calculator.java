package com.javaeight.examples.demoapp;

/**
 * Created by e068635 on 5/8/2019.
 */
@FunctionalInterface
public interface Calculator {
    /**
     *
     * @param firstValue
     * @param secondValue
     * @return
     */
    Integer calculate(Integer firstValue, Integer secondValue);
}
