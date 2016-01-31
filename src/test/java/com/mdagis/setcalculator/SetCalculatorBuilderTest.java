package com.mdagis.setcalculator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author agis
 */
public class SetCalculatorBuilderTest {

    @Test
    public void testSimpleBuilder() {

        Set set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        Set set3 = new HashSet<>();
        set3.add(5);
        set3.add(6);
        set3.add(7);

        Set set4 = new HashSet<>();
        set4.add(7);
        set4.add(8);
        set4.add(9);

        // Try to test the case of: (set1 AND set2) OR (set3 AND set4)
        SetCalculatorBuilder setBuilder = new SetCalculatorBuilder();
        Set lastSet = setBuilder
                .addSet(set1).level(0)
                .and()
                .addSet(set2).level(0)
                .or()
                .addSet(set3).level(1)
                .and()
                .addSet(set4).level(1)
                .calculate();

        Set expectedSet = new HashSet<>();
        expectedSet.add(3);
        expectedSet.add(7);

        Assert.assertEquals(expectedSet, lastSet);
    }

    @Test
    public void testBigSets() {

        final int SET_SIZE = 1_000_000;
        final int RANDOM_RANGE = 10_000_000;

        Set set1 = new HashSet<>(SET_SIZE);
        Set set2 = new HashSet<>(SET_SIZE);
        Set set3 = new HashSet<>(SET_SIZE);

        Random ran = new Random();
        int randomNumber;
        for (int i = 0; i < SET_SIZE; i++) {
            randomNumber = ran.nextInt(RANDOM_RANGE) + 1;
            set1.add(randomNumber);
            randomNumber = ran.nextInt(RANDOM_RANGE) + 1;
            set2.add(randomNumber);
            randomNumber = ran.nextInt(RANDOM_RANGE) + 1;
            set3.add(randomNumber);
        }

        // Start benchmarking ...
        long startTime = System.currentTimeMillis();
        
        SetCalculatorBuilder setBuilder = new SetCalculatorBuilder();
        Set lastSet = setBuilder
                .addSet(set1)
                .and()
                .addSet(set2)
                .notIn()
                .addSet(set3)
                .calculate();
        
        long estimatedTime = System.currentTimeMillis() - startTime;
        
        
        Assert.assertTrue(estimatedTime < 1_000);

    }
}
