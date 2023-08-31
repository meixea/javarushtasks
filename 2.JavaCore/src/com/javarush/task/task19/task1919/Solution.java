package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) {

        TreeMap<String, Double> people = new TreeMap<>();
        try( BufferedReader reader = new BufferedReader(new FileReader(args[0])) ){
            while( reader.ready() ){
                String line = reader.readLine();
                if( line.length() > 0){
                    String[] elements = line.split(" ");

                    Double value;
                    try{
                        value = Double.parseDouble(elements[1]);
                    }
                    catch( NumberFormatException e ){
                        System.out.println(e.getMessage());
                        return;
                    }

                    if( people.containsKey(elements[0])) {
                        Double oldValue = people.get(elements[0]);
                        people.replace(elements[0], oldValue + value);
                    }
                    else
                        people.put(elements[0], value);
                }
            }
            while( people.size() > 0 ){
                Map.Entry<String, Double> key = people.pollFirstEntry();
                System.out.println(key.getKey() + " " + key.getValue());
            }
        }
        catch( IOException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}
