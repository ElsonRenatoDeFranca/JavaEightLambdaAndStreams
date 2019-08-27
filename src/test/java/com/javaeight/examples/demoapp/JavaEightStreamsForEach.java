package com.javaeight.examples.demoapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by e068635 on 8/26/2019.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
/**
 *Intermediate operations return the stream itself
 * so you can chain multiple method calls in a row. Let’s learn important ones.
 */

public class JavaEightStreamsForEach {


    private List<String> memberNames;

    @Before
    public void setup(){
        memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

    }

    /**
     * Filter accepts a predicate to filter all elements of the stream.
     * This operation is intermediate which enables us to call another stream operation (e.g. forEach) on the result.
     */
    @Test
    public void displayAllNamesThatStartsWithLetterA() {
        memberNames.stream().filter((s) -> s.startsWith("A"))
                .forEach(System.out::println);
    }

    /**
     *
     The intermediate operation map converts each element into another object via the given function.
     The following example converts each string into an upper-cased string.
     But you can also use map to transform each object into another type.
     */
    @Test
    public void convertAllNamesThatStartsWithLetterAToUpperCase() {
        memberNames.stream().filter((s) -> s.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        assertThat(memberNames,is(notNullValue()));
    }

    @Test
    public void sortAllNamesFromTheMemberNames() {
        memberNames.stream().sorted()
                .map(String::toUpperCase)
                .forEach(System.out::println);

    }

    @Test
    public void sortAllNamesFromTheMemberNamesAndConvertAllNamesToUpperCase() {
         memberNames.stream().sorted()
                 .map(String::toUpperCase)
                 .forEach(System.out::println);
    }

    @Test
    public void findAllDistinctElements() {
        memberNames.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void findAllDistinctElementsLimitedToFive() {
        memberNames.stream().limit(5).forEach(System.out::println);
    }

    /**
     *
     * Terminal operations return a result of a certain type instead of again a Stream.
     *
     * -- Stream.forEach()
     * -- Stream.collect()
     * -- Stream.match()
     *
     *
     This method helps in iterating over all elements of a stream and perform some operation on each of them.
     The operation is passed as lambda expression parameter.
     */
    @Test
    public void useForEach() {
        memberNames.stream().forEach(System.out::println);
    }


    @Test
    public void useCollectors() {
        List<String> memberNameList = memberNames.stream().collect(Collectors.toList());
        assertThat(memberNameList, is(notNullValue()));
    }

    /**
     * Various matching operations can be used to check whether a
     * certain predicate matches the stream. All of those operations are terminal and return a boolean result.
     */
    @Test
    public void anyMatch() {

        boolean matchedResult = memberNames.stream()
                .anyMatch((s) -> s.startsWith("A"));

        assertThat(matchedResult, is(equalTo(true)));
    }

    @Test
    public void noneMatch() {
        boolean matchedResult = memberNames.stream()
                .noneMatch((s) -> s.startsWith("R"));

        assertThat(matchedResult, is(equalTo(false)));
    }


    /**
     *
     It will return first element from stream and then will not process any more element.
     */
    @Test
    public void findFirst() {

        String firstMatchedName = memberNames.stream()
                .filter((s) -> s.startsWith("L"))
                .findFirst().get();

        System.out.println(firstMatchedName);

    }

    @Test
    public void getMaxNumber() {

        Integer maxNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .max(Comparator.comparing(Integer::valueOf))
                .get();

        assertThat(maxNumber, is(equalTo(9)));
    }

    @Test
    public void getMinNumber() {
        Integer minNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .min(Comparator.comparing(Integer::valueOf))
                .get();
        assertThat(minNumber, is(equalTo(1)));
    }







}
