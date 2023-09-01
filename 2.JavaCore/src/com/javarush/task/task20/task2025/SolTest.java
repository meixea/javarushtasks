package com.javarush.task.task20.task2025;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashSet;

public class SolTest {

    private static final int BENCHMARK_TESTS = 100000;
    private static int[] test_digits;
    private static int[] test_digits2;
    @BeforeAll
    public static void setupAll(){
        test_digits = new int[20];
        test_digits2 = new int[20];
    }
    @Test
    public void pow(){
        assertEquals(14348907L, Solution.pow(3L, 15));
    }
    @Test
    @DisplayName("Decompose right numbers into massive")
    public void decomposeNumber1(){
        long number = 1234567890123456L;
        int result = Solution.CalculationUnit.decomposeNumber(number, test_digits);
        assertEquals(16, result);
        for(int i = 16; i >= 1; i-- )
            assertEquals(i % 10, test_digits[16 - i], String.format("Digit index %d is wrong", i-1));
    }
    @Test
    @DisplayName("Calculating summ of digits")
    public void composeNumber() {

        int[] digits = {1, 2, 3, 4, 5, 6};
        long result = Solution.CalculationUnit.composeNumber(digits, 0, digits.length);
        assertEquals(67171L, result);
    }
    @Test
    @DisplayName("Calculating summ with trailing zeroes")
    public void composeNumber2(){
        int[] digits = {0, 0, 0, 5, 6};
        long result = Solution.CalculationUnit.composeNumber(digits, 3, digits.length);
        assertEquals(61L, result);
    }
    @Test
    public void testCombo(){
        Solution.results.clear();
        Solution.CalculationUnit unit = new Solution.CalculationUnit(4, 9, 0);
        int[] test_digits3 = {0, 0, 0, 1, 3, 5};
        unit.testCombo(test_digits3, test_digits2, test_digits3.length);
        assertEquals(1, Solution.results.size(), "Must find only 1 true number");
        assertEquals(153L, Solution.results.first(), "Wrong number founded");
    }
    @Test
    public void run(){
        Solution.results.clear();
        Solution.CalculationUnit unit = new Solution.CalculationUnit(3, 9, 0);
        unit.run();
        assertEquals(new HashSet(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 153L, 370L, 371L, 407L)),
                     Solution.results);
    }
    @Disabled
    public void getNumbers(){
        long[] result = Solution.getNumbers(Long.MAX_VALUE-1);
    }
    private static void ass(boolean condition, String message){
        if(!condition)
            System.out.println("Error: " + message);
    }
    private static void bench(){
        System.out.println("-----Benchmark-----");
        long number = 1234567890123456L;
        int[] digits = new int[20];
        long startTime = System.nanoTime();
        for( int i = 0; i < BENCHMARK_TESTS; i++){
            int result = Solution.CalculationUnit.decomposeNumber(number, digits);
        }
        long endTime = System.nanoTime();
        System.out.printf("test time: %d mcs\n", (endTime - startTime)/1000L);
    }

}
