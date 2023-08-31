package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) {
        HashMap<String, Double> people = new HashMap<>();
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
            TreeSet<Map.Entry<String, Double>> values = new TreeSet<>( (x1, x2) -> {
                double comparedValue = x2.getValue() - x1.getValue();
                if( comparedValue < 0.0 )
                    return -1;
                if( comparedValue > 0.0 )
                    return 1;
                return x1.getKey().compareToIgnoreCase(x2.getKey());
            });
            values.addAll(people.entrySet());

            Map.Entry<String, Double> maxElement = values.pollFirst();
            System.out.println(maxElement.getKey());
            while( true ){
                Map.Entry<String, Double> element = values.pollFirst();
                if( element.getValue() < maxElement.getValue() )
                    break;
                System.out.println(element.getKey());
            }
        }
        catch( IOException e){
            System.out.println(e.getMessage());
            return;
        }
    }
}
