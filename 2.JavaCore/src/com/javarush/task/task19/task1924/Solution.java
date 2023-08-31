package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        String filename;
        try( BufferedReader console = new BufferedReader(new InputStreamReader(System.in)) ){
            filename = console.readLine();
        }
        catch( IOException e ){
            System.out.println(e.getMessage());
            return;
        }
        try( BufferedReader file = new BufferedReader(new FileReader(filename))){
            while(file.ready()){
                String[] line = file.readLine().split(" ");
                for (int i = 0; i < line.length; i++) {
                    String[] parts;
                    if( line[i].matches("\\d+\\p{Punct}*")) {
                        parts = divideString(line[i]);
                        int number = Integer.parseInt(parts[0]);
                        if ((number >= 0) && (number <= 12))
                            parts[0] = map.get(number);
                        line[i] = parts[0] + parts[1];
                    }
                }
                System.out.println(String.join(" ", line));
            }
        }
        catch( IOException e ){
            System.out.println(e.getMessage());
            return;
        }
    }
    private static String[] divideString(String string){
        String[] result = new String[2];
        for (int i = 0; i < string.length(); i++) {
            if( !string.substring(i, i+1).matches("\\d") ){
                result[0] = string.substring(0, i);
                result[1] = string.substring(i, string.length());
                return result;
            }
        }
        result[0] = string;
        result[1] = "";
        return result;
    }
}
