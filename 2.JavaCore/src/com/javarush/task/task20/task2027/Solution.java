package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> result = detectAllWords(crossword, "home", "same", "re");
        if( result != null )
            for( Word word : result )
                System.out.println(word);
        else
            System.out.println("not found");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> result = new ArrayList<>();

        if( (crossword == null) || (crossword.length == 0) || (crossword[0].length == 0) )
            return result;

        for (int i = 0; i < words.length; i++) {
            detectWord(result, crossword, words[i]);
        }

        return result;
    }
    private static void detectWord(List<Word> result, int[][] crossword, String string){

        char[] symbols = string.toCharArray();

        int[][] directions = new int[][]{
                { 1,  0},
                { 1,  1},
                { 0,  1},
                {-1,  1},
                {-1,  0},
                {-1, -1},
                { 0, -1},
                { 1, -1},
        };

        for(int y = 0; y < crossword.length; y++)
            for(int x = 0; x < crossword[0].length; x++){
                if( crossword[y][x] == (int)symbols[0] ){
                    for(int d = 0; d < directions.length; d++)
                        if( detectWordInDirection(crossword, symbols, x, y, directions[d][0], directions[d][1]) ){
                            Word word = new Word(string);
                            word.setStartPoint(x, y);
                            word.setEndPoint(x + (symbols.length - 1)*directions[d][0],
                                    y + (symbols.length - 1)*directions[d][1]);
                            result.add(word);
                        }
                }
            }
    }
    private static boolean detectWordInDirection(int[][] crossword, char[] symbols,
                                                 int startX, int startY, int dirX, int dirY)
    {
        int endX = startX + (symbols.length - 1)*dirX;
        int endY = startY + (symbols.length - 1)*dirY;
        if(
                (endX < 0) ||
                (endX >= crossword[0].length) ||
                (endY < 0) ||
                (endY >= crossword.length)
        ){
            return false;
        }
        for(int i = 0; i < symbols.length; i++){
            int x = startX + (i * dirX);
            int y = startY + (i * dirY);
            if( crossword[y][x] != (int)symbols[i] )
                return false;
        }

        return true;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
