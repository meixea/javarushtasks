package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution {
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
            while(file.ready()) {
                StringBuilder result = new StringBuilder(file.readLine());
                System.out.println(result.reverse().toString());
            }
        }
        catch( IOException e ){
            System.out.println(e.getMessage());
            return;
        }
    }
}
