package com.javaeight.examples.demoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Comparator;

/**
 * Created by e068635 on 6/6/2019.
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MethodReferenceTest {

    @Test
    public void compareOneStringToAnother(){
        Comparator<String> lambdaComparator = (String first, String second) -> first.compareToIgnoreCase(second);


        Comparator<String> methodReferenceComparator = String::compareToIgnoreCase;

    }



}
