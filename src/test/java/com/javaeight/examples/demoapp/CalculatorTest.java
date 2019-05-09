package com.javaeight.examples.demoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.BiFunction;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
/**
 * Created by e068635 on 5/8/2019.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    @Test
    public void testAddition(){
        Calculator addition = (firstValue, secondValue) -> firstValue + secondValue;
        Integer result = addition.calculate(4, 2);
        assertThat(6, is(equalTo(result)));
    }

    @Test
    public void testSubtraction(){
        Calculator subtraction = (firstValue, secondValue) -> firstValue - secondValue;
        Integer result = subtraction.calculate(4, 4);
        assertThat(0, is(equalTo(result)));
    }

    @Test
    public void testMultiplication(){
        Calculator multiplication = (firstValue, secondValue) -> firstValue * secondValue;
        Integer result = multiplication.calculate(5, 4);
        assertThat(20, is(equalTo(result)));
    }

    @Test
    public void testDivision(){
        Calculator division = (firstValue, secondValue) -> firstValue / secondValue;
        Integer result = division.calculate(8, 4);
        assertThat(2, is(equalTo(result)));
    }



    @Test
    public void testAdditionWithBiFunction(){
        BiFunction<Double, Double,Double> addition = (x, y) -> x + y;
        Double result = addition.apply(2.0,4.0);
        assertThat(6.0, is(equalTo(result)));
    }

    @Test
    public void testSubtractionWithBiFunction(){
        BiFunction<Double, Double,Double> subtraction = (x, y) -> x - y;
        Double result = subtraction.apply(2.0,4.0);
        assertThat(-2.0, is(equalTo(result)));
    }

    @Test
    public void testMultiplicationWithBiFunction(){
        BiFunction<Double, Double,Double> multiplication = (x, y) -> x * y;
        Double result = multiplication.apply(2.0,4.0);
        assertThat(8.0, is(equalTo(result)));
    }

    @Test
    public void testDivisionWithBiFunction(){
        BiFunction<Double, Double,Double> multiplication = (x, y) -> x / y;
        Double result = multiplication.apply(2.0,4.0);
        assertThat(0.5, is(equalTo(result)));
    }

}
