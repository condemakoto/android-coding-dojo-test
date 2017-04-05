package com.globallogic.codingdojo.testexamples;

import com.globallogic.codingdojo.testexamples.model.Adder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Julio Kun
 */

@RunWith(Parameterized.class)
public class TestWithParameters {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0, 0}, {10, 20, 100}, {0, 5, 5}, {10, 0, 10}, {20, 40, 60}
        });
    }

    @Parameterized.Parameter(0)
    public int inputA;
    @Parameterized.Parameter(1)
    public int inputB;
    @Parameterized.Parameter(2)
    public int result;

    @Test
    public void testSums() {
        Adder adder = new Adder();
        Assert.assertEquals(adder.sum(inputA, inputB), result);
    }

    /**
     * In this first approach I should develop a new method for each use case I want to test.
     */
    /*
    @Test
    public void testAdd() {
        Adder adder = new Adder();
        Assert.assertEquals(adder.sum(2, 2), 4);
    }
    */

    /*
    This second approach is not correct since the tests must be independent
    */
    /*
    @Test
    public void testAdd() {
        Adder adder = new Adder();
        int[][] parameters = new int[][] { {1, 2, 3}, {2, 2, 4} };
        for (int i = 0; i < parameters.length; i++) {
            int a = parameters[i][0];
            int b = parameters[i][1];
            int result = parameters[i][2];
            Assert.assertEquals(adder.sum(a, b), result);
        }

    }
    */

}
