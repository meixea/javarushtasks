package com.javarush.task.task19.task1927;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* 
Контекстная реклама
*/

public class Solution {
    private static final String Advertise = "JavaRush - курсы Java онлайн";
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(data));
        testString.printSomething();
        System.setOut(out);
        try( BufferedReader reader = new BufferedReader(
                                     new InputStreamReader(
                                     new ByteArrayInputStream(data.toByteArray()))))
        {
            int count = 0;

            while(reader.ready()) {
                String line = reader.readLine();
                System.out.println(line);
                if (count == 1) {
                    count = 0;
                    System.out.println(Advertise);
                } else
                    count++;
            }
        }
        catch( IOException e){}
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }

/*
    private static class AdvertiseStream extends PrintStream {
        private static final String Advertise = "JavaRush - курсы Java онлайн";
        private static int count = 0;
        public AdvertiseStream(OutputStream data){
            super(data);
        }
        @Override
        public void println(String x){
            super.println(x);
            count++;
            if( count == 2 ){
                super.println(Advertise);
                count = 0;
            }
        }
    }
*/
}
