package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/* 
Алгоритмы-числа
*/

public class Solution {
    private static final int THREADS_NUMBER = 9;

    private static long[][] digitPowers;
    private static final int MAX_DIGITS = 19;
    protected static TreeSet<Long> results = new TreeSet<>();

    static {
        digitPowers = new long[10][MAX_DIGITS + 1];
        for(int power = 0; power <= MAX_DIGITS; power++)
            for(long number = 0; number < 10L; number++)
                digitPowers[(int)number][power] = pow(number, power);
    }
    protected static class CalculationUnit extends Thread {
        private int[] digits;
        private int[] test_digits;
        private int endDigit;
        public CalculationUnit(int M, int startDigit, int endDigit){
            super();

            digits = new int[M];
            test_digits = new int[M+1];
            this.endDigit = endDigit;

            for (int i = 0; i < M; i++)
                digits[i] = startDigit;
        }
        @Override
        public void run(){
            int M1 = digits.length - 1;
            while( digits[M1] > endDigit ){

                testCombo(digits, test_digits, digits.length);

                int not_null_index = 0;
                while( digits[not_null_index] == 0 )
                    not_null_index++;
                digits[not_null_index]--;
                for(int i = 0; i < not_null_index; i++)
                    digits[i] = digits[not_null_index];
            }
        }
        protected void testCombo(int[] digits, int[] test_digits, int M){
            int from = 0;
            long value;
            while(true){
                value = composeNumber(digits, from, M);
                int testM = decomposeNumber(value, test_digits);
                if(testM == (M - from)){
                    long value2 = composeNumber(test_digits, 0, testM);
                    if( value == value2 )
                        synchronized(Solution.results) {
                            results.add(value);
                        }
                }
                if(digits[from] > 0)
                    break;
                from++;
            }
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
    }

    public static long[] getNumbers(long N) {

        if( N < 1 )
            return new long[0];

        int[] digits = new int[MAX_DIGITS];

        int M = CalculationUnit.decomposeNumber(N, digits);

        List<Thread> threads = new ArrayList<>();

        int full_size = 9;
        for( int i = THREADS_NUMBER; i > 0; i--){
            int current_size = full_size / i;
            Thread thread = new CalculationUnit(M, full_size, full_size - current_size);
            threads.add(thread);
            thread.start();
            full_size -= current_size;
        }
        try {
            for (Thread thread : threads)
                thread.join();
        }
        catch( InterruptedException e ){}

        long[] result = results.stream()
                .mapToLong(i -> i)
                .filter(i -> (N - i) > 0)
                .toArray();
        return result;
    }
    protected static long pow(long number, int power){
        long result = 1;
        for (int i = 0; i < power; i++)
            result *= number;
        return result;
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
