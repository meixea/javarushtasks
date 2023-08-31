package com.javarush.task.task20.task2025;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

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
    @DisplayName("Decompose right numbers into massive")
    public void decomposeNumber1(){
        long number = 1234567890123456L;
        int result = Solution.decomposeNumber(number, test_digits);
        assertEquals(16, result);
        for(int i = 16; i >= 1; i-- )
            assertEquals(i % 10, test_digits[16 - i], String.format("Digit index %d is wrong", i-1));
    }
    @Test
    @DisplayName("Calculating summ of digits")
    public void composeNumber() {

        int[] digits = {1, 2, 3, 4, 5, 6};
        long result = Solution.composeNumber(digits, 0, digits.length);
        assertEquals(67171L, result);
    }
    @Test
    @DisplayName("Calculating summ with trailing zeroes")
    public void composeNumber2(){
        int[] digits = {0, 0, 0, 5, 6};
        long result = Solution.composeNumber(digits, 3, digits.length);
        assertEquals(61L, result);
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
            int result = Solution.decomposeNumber(number, digits);
        }
        long endTime = System.nanoTime();
        System.out.printf("test time: %d mcs\n", (endTime - startTime)/1000L);
    }

}
