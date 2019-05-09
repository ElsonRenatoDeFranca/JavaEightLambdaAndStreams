package com.javaeight.examples.demoapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;


/**
 * Created by e068635 on 3/12/2019.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JavaEightMethodReferences {


    @Before
    public void setup(){

    }

    @Test
    public void testWhenIntFunction(){
        IntFunction<String> intToString = num  -> Integer.toString(num);
        System.out.println("expected value 3, actual value: "+ intToString.apply(123).length());
    }

    @Test
    public void testStaticMethodReference(){
        IntFunction<String> intToString2 = Integer::toString;
        System.out.println("expected value 4, actual value: "+ intToString2.apply(4567).length());
    }

    @Test
    public void testLambdaMadeUsingContructor(){
        Function<String, BigInteger> newBigInt = BigInteger::new;
        System.out.println("expected value: 12345678, actual value: "+ newBigInt.apply("12345678"));
    }

    @Test
    public void testUnaryOperator(){
        UnaryOperator<String> makeGreeting = "Hello "::concat;
        System.out.println(makeGreeting.apply("Peggy"));
    }
;

}
