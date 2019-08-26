package com.javaeight.examples.demoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by e068635 on 7/29/2019.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ExercisesJavaEight {


    private static final String BLANK_SPACE = " ";
    private static final String COMMA_DELIMITER = ",";


    @Test
    public void capitalizeLetters(){
        String originalString = "lorem ipsum dolor sit amet";
        String expectedString = "Lorem Ipsum Dolor Sit Amet";

        String strings = Stream.of(originalString.split(BLANK_SPACE)).
                map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase()).
                collect(Collectors.joining(BLANK_SPACE));

        assertThat(expectedString, is(equalTo(strings)));

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
    public void simpleAdding(){
        int value = 5;
        assertThat(15,is(equalTo(this.simpleAdding(value))));
    }

    @Test
    public void findLongestWord(){
        List<String> words = Arrays.asList("wing","backend","table","ice-cream","helicopter","Franklin Delano Roosevelt","airplane","mainstream","wall-street");
        String longestWordJava6 = findLongestWord(words);
        Optional<String> longestWordJava8 = words.stream().max(Comparator.comparingInt(String::length));
        System.out.println("Java 8 result: "+longestWordJava8.get()+", Jave 6 Result: "+longestWordJava6);
        assertThat(longestWordJava8.get(), is(equalTo("Franklin Delano Roosevelt")));
        assertThat(longestWordJava6, is(equalTo("Franklin Delano Roosevelt")));
    }

    @Test
    public void findVowelsInString(){
        String text = "All cows eat grass";

        assertThat(5L, is(equalTo(findVowels(text))));
    }

    private long findVowels(String text){

        IntPredicate vowel = (int value) -> value =='a' || value =='e' || value =='i' || value =='o' || value =='u' ||
                value == 'A' || value =='E' || value =='I' || value =='O' || value =='U';

        long numberOfVowels = text.chars().filter(vowel).count();

        return numberOfVowels;
    }

    private String findLongestWord(List<String> words){
        int longest = 0;
        String word = "";
        if(words != null){
            for(int i =0; i< words.size(); i++){
                String listItem = words.get(i);
                if(listItem.length() > longest){
                    longest = listItem.length();
                    word = listItem;
                }
            }

        }
        return word;
    }


    private boolean isEven(Integer number){
        return number % 2 == 0;
    }

    private int simpleAdding(int number){
        int returnedValue = 0;

        for(int i = number; i > 0; i --){
            returnedValue = i + returnedValue;
        }
        return returnedValue;
    }

}
