package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/* 
Алгоритмы-числа
*/

public class Solution {
    private static final int THREADS_NUMBER = 2;

    private static long[][] digitPowers;
    private static final int MAX_DIGITS = 19;
    private static Set<Long> results = new TreeSet<>();

    static {
        digitPowers = new long[10][MAX_DIGITS + 1];
        for(int power = 0; power <= MAX_DIGITS; power++)
            for(long number = 0; number < 10L; number++)
                digitPowers[(int)number][power] = pow(number, power);
    }

    public static long[] getNumbers(long N) {

        if( N < 1 )
            return new long[0];

        int[] digits = new int[MAX_DIGITS];
        int[] test_digits = new int[MAX_DIGITS];

        int M = decomposeNumber(N, digits);
        int M1 = M - 1;

        for( int i = 0; i < M; i++ )
            digits[i] = 9;

        while( digits[M1] > 0 ){

            testCombo(digits, test_digits, M);

            int not_null_index = 0;
            while( digits[not_null_index] == 0 )
                not_null_index++;
            digits[not_null_index]--;
            for(int i = 0; i < not_null_index; i++)
                digits[i] = digits[not_null_index];
        }

        long[] result = results.stream()
                .mapToLong(i -> i)
                .filter(i -> (N - i) > 0)
                .toArray();
        return result;
    }
    protected static void testCombo(int[] digits, int[] test_digits, int M){
        int from = 0;
        long value;
        while(true){
            value = composeNumber(digits, from, M);
            int testM = decomposeNumber(value, test_digits);
            if(testM == (M - from)){
                long value2 = composeNumber(test_digits, 0, testM);
                if( value == value2 )
                    results.add(value);
            }
            if(digits[from] > 0)
                break;
            from++;
        }
    }
    private static long pow(long number, int power){
        long result = 1;
        for (int i = 0; i < power; i++)
            result *= number;
        return result;
    }
    protected static long composeNumber(int[] digits, int from, int to){

        int power = to - from;
        long summ = 0;
        for (int i = from; i < to; i++) {
            summ += digitPowers[digits[i]][power];
            if( summ < 0 )
                return 0;
        }
        return summ;
    }
    protected static int decomposeNumber(long number, int[] digits){
        int power = 0;
        while( number > 0 ){
            digits[power] = (int)(number % 10L);
            power++;
            number /= 10L;
        }
        return power;
    }

    public static void main(String[] args) {

        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a));

        a = System.currentTimeMillis();
        long[] result = getNumbers(Long.MAX_VALUE);
        System.out.println("values: " + result.length);
        System.out.println(Arrays.toString(result));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a));
    }
}
