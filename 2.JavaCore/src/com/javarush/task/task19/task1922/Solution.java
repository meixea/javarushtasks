package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        String filename;
        try(BufferedReader console = new BufferedReader(new InputStreamReader(System.in))){
            filename = console.readLine();
        }
        catch( IOException e ){
            System.out.println(e.getMessage());
            return;
        }

        List<String> norm = words.stream().map(i -> i.toLowerCase()).collect(Collectors.toList());

        try(BufferedReader file = new BufferedReader(new FileReader(filename))){
            while( file.ready() ){
                String line = file.readLine();
                List<String> elements = Arrays.asList(line.toLowerCase().split(" "));
                int count = 0;
                for(String word : elements)
                    if( norm.contains(word))
                        count++;
                if(count == 2)
                    System.out.println(line);
            }
        }
        catch( IOException e ){
            System.out.println(e.getMessage());
            return;
        }
    }
}
