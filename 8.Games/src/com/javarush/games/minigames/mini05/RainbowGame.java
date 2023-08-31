package com.javarush.games.minigames.mini05;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Color;

/* 
Цвета радуги
*/

public class RainbowGame extends Game {

    //напишите тут ваш код
    public static final Color[] COLORS = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.INDIGO,
            Color.VIOLET
    };
    public void initialize(){
        setScreenSize(10, 7);
        for(int y = 0; y < 7; y++)
            for(int x = 0; x < 10; x++)
                setCellColor(x, y, COLORS[y]);
    }
}
