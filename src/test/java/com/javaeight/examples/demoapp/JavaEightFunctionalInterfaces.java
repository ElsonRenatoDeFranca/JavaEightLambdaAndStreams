package com.javaeight.examples.demoapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by e068635 on 3/12/2019.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JavaEightFunctionalInterfaces {


    @Before
    public void setup(){

    }

    @Test
    public void testWhenPredicateLenghtSmallerThan10(){
        Predicate<String> stringLen = (s) -> s.length() < 10;
        System.out.println(stringLen.test("apples") + "- Apples is less than 10");
    }

    @Test
    public void testWhenConsumerAcceptsParameter(){
        Consumer<String> consumerStr = (s) -> System.out.println(s.toLowerCase());
        consumerStr.accept("ABBcceefGHiiJkkLLMNOPqrsTUVXZ");
    }

    @Test
    public void testWhenFunction(){
        Function<Integer, String> converter = (num) -> Integer.toString(num);
        System.out.println("Length of 26: "+ converter.apply(26).length());
    }

    @Test
    public void testWhenSupplier(){
        Supplier<String> s = () -> "Java is fun";
        System.out.println(s.get());
    }

    @Test
    public void testWhenBinaryOperator(){
        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("add 10 + 25: "+ add.apply(10, 25));
    }

    @Test
    public void testWhenUnaryOperator(){
        UnaryOperator<String> stringUnaryOperator = (msg) -> msg.toUpperCase();
        System.out.println(stringUnaryOperator.apply("this is my message in uppercase"));
    }

    @Test
    public void testUnaryConsumer(){
        List<String> names = Arrays.asList("John", "Freddy", "Samuel");
        names.forEach(name -> System.out.println("Hello "+ name));
    }

    @Test
    public void testBinaryConsumer(){
        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 25);
        ages.put("Freddy", 24);
        ages.put("Samuel", 30);

        ages.forEach((name,age) -> System.out.println("Name is "+ name+", age is "+age));
    }

    @Test
    public void testPredicate(){
        List<String> names = Arrays.asList("John", "Freddy", "Samuel");
        List<String> startWithA = names.stream().filter(name -> name.startsWith("J")).
                collect(Collectors.toList());
        System.out.println(startWithA);
    }



}
