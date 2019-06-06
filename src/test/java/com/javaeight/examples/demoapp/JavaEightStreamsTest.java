package com.javaeight.examples.demoapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * Created by e068635 on 3/12/2019.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JavaEightStreamsTest {



    private List<String> infoList;
    private static final String BLANK_SPACE = " ";


    @Before
    public void setup(){
        infoList = new ArrayList<>();
        infoList.add("Card");
        infoList.add("Cell phone");
        infoList.add("Laptop LatitudeE5579");
        infoList.add("Space aircraft");
        infoList.add("Corner belt");
        infoList.add("pneumonoultramicroscopicsilicovolcanoconiosis,");


    }

    @Test
    public void test_forEach_shouldReturnAListOfAllWordsThatStartsWithALetter(){
        List<String> list = infoList.stream().
                filter(word -> word.startsWith("C")).
                collect(Collectors.toList());

        list.forEach(System.out::println);
            

    }

    @Test
    public void test_forEach_shouldReturnAListOfAllWordsThatEndsWithALetter(){
        List<String> list = infoList.stream().
                filter(word -> word.endsWith("t")).
                collect(Collectors.toList());

        list.forEach(System.out::println);


    }

    @Test
    public void test_shouldReturnAListOfAllWordsThatAreSmallerThan10Characters(){
        List<String> list = infoList.stream().
                filter(word -> word.length() < 10).
                collect(Collectors.toList());

        list.forEach(System.out::println);

        assertThat(list,is(notNullValue()));
        assertThat(list,hasSize(1));
        assertThat(list,hasItems("Card"));

    }

    @Test
    public void count_occurrencesOfALetterInAWord(){
        String someString="Cell Phone";
        char letter = 'n';

        long occurrences = someString.chars().filter(ch -> ch == letter).count();
        assertThat(occurrences,is(equalTo(1L)));
    }

    @Test
    public void testConvertToMapMethod(){

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        //get list of unique squares
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }

    @Test
    public void testCountNumberOfCapitalLettersInAStringUsingJavaEight(){
        String originalString ="Elson Renato de Franca";
        long count = originalString.codePoints().filter(p -> Character.isUpperCase(p)).count();
        System.out.println(count);
        assertThat(count, is(equalTo(3L)));
    }

    @Test
    public void testCountNumberOfLowerCaseLettersInAStringUsingJavaEight(){
        String originalString ="Elson Renato de Franca";
        long count = originalString.chars().filter(p -> Character.isLowerCase(p)).count();
        System.out.println(count);
        assertThat(count, is(equalTo(16L)));
    }

    @Test
    public void testCountNumberOfBlankSpacesInAStringUsingJavaEight(){
        String originalString ="Elson Renato de Franca";
        long count = originalString.chars().filter(p -> Character.isSpaceChar(p)).count();
        System.out.println(count);
        assertThat(count, is(equalTo(3L)));
    }

    @Test
    public void testCountNumberOfVowelsInTheString(){
        String originalString ="Elson Renato de Franca";
        IntPredicate vowel = (int value) -> value =='a' || value =='e' || value =='i' || value =='o' || value =='u' ||
                                            value == 'A' || value =='E' || value =='I' || value =='O' || value =='U';

        long count = originalString.chars().filter(vowel).distinct().count();
        assertThat(count, is(equalTo(4L)));
    }


    @Test
    public void testCountNumberOfConsonantsInAStringUsingLambda(){
        String originalString ="Elson Renato de Franca";

        IntPredicate consonant = (int value)  -> {
            if(value == 'a' || value == 'e' || value == 'i' || value == 'o' || value == 'u' ||
                    value =='A' || value =='E' || value =='I' || value =='O' || value =='U')
                return false;
            else
                return true;

        };

        long consonantCounter = originalString.chars().filter(consonant).distinct().count();
        assertThat(consonantCounter, is(equalTo(10L)));
    }

    @Test
    public void testCountNumberOfUnderAgePeopleInAList(){
        List<Integer> ages = Arrays.asList(23, 22, 12, 43, 17, 25, 15);
        Predicate<Integer> underages = (age) -> age < 18;
        long underagesCounter = ages.stream().filter(underages).count();
        assertThat(underagesCounter, is(equalTo(3L)));
    }

    @Test
    public void testListAllNumberOfUnderAgePeople(){
        List<Integer> ages = Arrays.asList(23, 22, 12, 43, 17, 25, 15);
        Predicate<Integer> underages = (age) -> age < 18;
        List<Integer> underagesList = ages.stream().filter(underages).collect(Collectors.toList());
        assertThat(underagesList, hasSize(3));
    }

    @Test
    public void testCapitalizeLettersJava6(){
        String originalString ="Lorem ipsum dolor sit amet";
        List<String> paragraph = Arrays.asList(originalString.split(Pattern.quote(BLANK_SPACE)));
        StringBuffer capitalizedString = new StringBuffer();

        for(int i=0; i< paragraph.size(); i++){
            String word = paragraph.get(i);
            for(int j=0; j < word.length(); j++){
                Character letter = word.charAt(j);
                if(j==0){
                    capitalizedString.append(word.replace(letter,Character.toUpperCase(letter)));
                    capitalizedString.append(BLANK_SPACE);
                }
            }

        }

    }

    @Test
    public void testCapitalizeLettersJava8(){
        String originalString = "Lorem ipsum dolor sit amet";
        String expectedString = "Lorem Ipsum Dolor Sit Amet";
        String strings = Stream.of(originalString.split(BLANK_SPACE)).
                         map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase()).
                         collect(Collectors.joining(BLANK_SPACE));

        assertThat(expectedString, is(equalTo(strings)));

    }



    @Test
    public void testNotOrangeWithPredicate(){
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry", "Orange");
        Predicate<String> fruitFilterExample = (String fruit)  -> !fruit.equals("Orange");
        List<String> filteredFruits = fruits.stream().filter(fruitFilterExample).collect(Collectors.toList());
        assertThat(filteredFruits, hasItems("Apple","Banana","Cherry"));
    }

    @Test
    public void removeItemFromList(){
        List<String> fruits = new LinkedList<>(Arrays.asList("Apple", "Banana", "Cherry", "Orange"));
        Predicate<String> predicate = (String fruit)  -> fruit.equals("Cherry");
        fruits.removeIf(predicate);
    }

    @Test
    public void AddSuffixToItemList(){
        List<String> fruits = new LinkedList<>(Arrays.asList("Apple", "Banana", "Cherry", "Orange"));
        fruits = fruits.stream().map(fruit -> fruit.concat("-sweet")).collect(Collectors.toList());
        assertThat(fruits, hasItems("Apple-sweet","Banana-sweet", "Cherry-sweet", "Orange-sweet"));
        fruits.forEach(System.out::println);
        //fruits.stream().map(fruit -> fruit.concat("-sweet")).forEach(System.out::println);
        //Expected result: Apple-sweet,Banana-sweet, Cherry-sweet, Orange-sweet
    }



}


