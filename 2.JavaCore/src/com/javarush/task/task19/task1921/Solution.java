package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        try( BufferedReader reader = new BufferedReader(new FileReader(args[0])) ){
            while( reader.ready() ){
                String line = reader.readLine();
                if( line.length() > 0 ){
                    String[] elements = line.split(" ");
                    int year = Integer.parseInt(elements[elements.length - 1]);
                    int month = Integer.parseInt(elements[elements.length - 2]);
                    int day = Integer.parseInt(elements[elements.length - 3]);
                    StringJoiner name = new StringJoiner(" ");
                    for (int i = 0; i < (elements.length - 3); i++)
                        name.add(elements[i]);
                    PEOPLE.add(new Person(name.toString(),
                            new Date(year-1900, month-1, day)));
                }
            }
        }
        catch( IOException e){
            System.out.println(e.getMessage());
            return;
        }
//        for(Person i : PEOPLE)
//            System.out.println(i.getName() + i.getBirthDate().toString());
    }
}
