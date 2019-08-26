package com.javaeight.examples.demoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by e068635 on 7/26/2019.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CollectorsJavaEightExampleTest {

    private static final String BLANK_SPACE = " ";
    private static final String COMMA_DELIMITER = ",";


    @Test
    public void AddSuffixToItemListSorted(){
        List<String> fruits = new LinkedList<>(Arrays.asList("Pineaple","Strawberry","Apple", "Banana", "Cherry", "Orange", "Blueberry"));

        fruits = fruits.stream().
                map(fruit -> fruit.concat("-sweet")).
                sorted(String::compareToIgnoreCase).
                collect(Collectors.toList());

        assertThat(fruits, hasItems("Apple-sweet","Banana-sweet", "Blueberry-sweet", "Cherry-sweet", "Orange-sweet","Pineaple-sweet","Strawberry-sweet"));

    }

    @Test
    public void AddSuffixToItemListSortedNoDuplicates(){
        List<String> fruits = new LinkedList<>(Arrays.asList("Pineaple","Strawberry","Apple", "Banana", "Cherry", "Orange", "Blueberry","Banana"));

        fruits = fruits.stream().
                map(fruit -> fruit.concat("-sweet")).
                distinct().
                sorted(String::compareToIgnoreCase).
                collect(Collectors.toList());

        assertThat(fruits, hasItems("Apple-sweet","Banana-sweet", "Blueberry-sweet", "Cherry-sweet", "Orange-sweet","Pineaple-sweet","Strawberry-sweet"));
    }


    @Test
    public void testNotOrangeWithPredicate(){
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Orange");
        Predicate<String> fruitFilterExample = (String fruit)  -> !fruit.equals("Orange");
        List<String> filteredFruits = fruits.stream().filter(fruitFilterExample).collect(Collectors.toList());
        assertThat(filteredFruits, hasItems("Apple","Banana","Cherry"));
    }



    @Test
    public void filterEvenNumbersFromList_shouldReturnEvenNumbersSeparatedByCommas(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        String expectedText ="2,4,6,8";

        String returnedText = numbers.stream().filter(number -> isEven(number)).
                map(number -> number.toString()).
                collect(Collectors.joining(COMMA_DELIMITER));

        assertThat(returnedText, is(equalTo(expectedText)));
    }


    @Test
    public void filterOddNumbersFromList_shouldReturnOddNumbersSeparatedByCommas(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        String expectedText ="1,3,5,7";

        String returnedText = numbers.stream().filter(number -> !isEven(number)).
                map(number -> number.toString()).collect(Collectors.joining(COMMA_DELIMITER));

        assertThat(returnedText, is(equalTo(expectedText)));
    }

    @Test
    public void sumOfListItems(){
        List<Integer> numbers = Arrays.asList(1,2,3);
        Long expectedSum = 6L;
        IntSummaryStatistics sum = numbers.stream().collect(Collectors.
                                  summarizingInt(number -> number));

        assertThat(sum.getSum(), is(equalTo(expectedSum)));
    }

    @Test
    public void averageOfListItems(){
        List<String> numbers = Arrays.asList("1","2","3");
        double expectedAverageValue = 2.0;
        Double average = numbers.stream().collect(Collectors.averagingDouble(Double::parseDouble));
        assertThat(average, is(equalTo(expectedAverageValue)));
    }

    @Test
    public void maxNumberOfListItemsUsingIntStream(){
        List<Integer> numbers = Arrays.asList(1,2,22,3,27,5);
        Integer expectedValue = 27;
        OptionalInt max = numbers.stream().mapToInt(Integer::intValue).max();
        assertThat(max.getAsInt(), is(equalTo(expectedValue)));
    }

    @Test
    public void minNumberOfListItemsUsingIntStream(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,5);
        Integer expectedValue = 1;
        OptionalInt max = numbers.stream().mapToInt(Integer::intValue).min();
        assertThat(max.getAsInt(), is(equalTo(expectedValue)));
    }

    @Test
    public void minimumNumberOfListItemsUsingSummaryStatistics(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,5);
        Integer expectedValue = 1;
        int minimumNumber = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMin();
        assertThat(minimumNumber, is(equalTo(expectedValue)));
    }

    @Test
    public void maximumNumberOfListItemsUsingSummaryStatistics(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,31,5);
        Integer expectedValue = 31;
        int maximumNumber = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
        assertThat(maximumNumber, is(equalTo(expectedValue)));
    }
    @Test
    public void maximumNumberOfListItemsUsingCollector(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,31,5);
        Integer expectedValue = 31;
        Optional<Integer> maximumNumber = numbers.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        assertThat(maximumNumber.get(), is(equalTo(expectedValue)));
    }


    @Test
    public void minimumNumberOfListItemsUsingCollector(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,31,5);
        Integer expectedValue = 1;
        Optional<Integer> minimumNumber = numbers.stream().collect(Collectors.minBy(Comparator.naturalOrder()));
        assertThat(minimumNumber.get(), is(equalTo(expectedValue)));
    }

    @Test
    public void sortListItemsUsingCollectorAscendingOrder(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,31,5);
        List<Integer> sortedList = numbers.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        assertThat(sortedList, hasItems(1,2,3,5,22,27,31));
    }

    @Test
    public void sortListItemsUsingCollectorDescendingOrder(){
        List<Integer> numbers = Arrays.asList(2,22,3,27,1,31,5);
        List<Integer> sortedList = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertThat(sortedList, hasItems(31,27,22,5,3,2));
    }

    @Test
    public void testCapitalizeLettersJava8(){
        String originalString = "lorem ipsum dolor sit amet";
        String expectedString = "Lorem Ipsum Dolor Sit Amet";

        String strings = Stream.of(originalString.split(BLANK_SPACE)).
                map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase()).
                collect(Collectors.joining(BLANK_SPACE));

        assertThat(expectedString, is(equalTo(strings)));

    }






    private boolean isEven(Integer number){
        return number % 2 == 0;
    }


}
