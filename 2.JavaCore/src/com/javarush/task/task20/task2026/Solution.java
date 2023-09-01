package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        System.out.println(a1.length);
        System.out.println(a1[0].length);

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {

        int count = 0;

        if( (a == null) || (a.length == 0) || (a[0].length == 0) )
            return 0;

        for( int row = 0; row < a.length; row++)
            for( int col = 0; col < a[0].length; col++){
                if(     (a[row][col] == 1) &&
                        ( (row == 0) || (a[row - 1][col] == 0) ) &&
                        ( (col == 0) || (a[row][col - 1] == 0) ) )
                {
                    count++;
                }
            }

        return count;
    }
}
