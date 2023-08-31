package com.javarush.task.task19.task1925;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) {
        try(    BufferedReader fileIn = new BufferedReader(new FileReader(args[0]));
                FileWriter fileOut = new FileWriter(args[1]) )
        {
            StringJoiner result = new StringJoiner(",");
            while(fileIn.ready()){
                String[] words = fileIn.readLine().split(" ");
                for (int i = 0; i < words.length; i++) {
                    if( words[i].length() > 6 )
                        result.add(words[i]);
                }
            }
            fileOut.write(result.toString());
        }
        catch( IOException e ){
            System.out.println(e.getMessage());
            return;
        }
    }
}
