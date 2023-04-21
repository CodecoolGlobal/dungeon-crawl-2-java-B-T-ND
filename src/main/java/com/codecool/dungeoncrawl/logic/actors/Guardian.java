package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.Util;

public class Guardian extends Actor{
    public Guardian(Cell cell) {
        super(cell, 45, 2, 0);
    }

    @Override
    public String getTileName() {
        return "guardian";
    }

    @Override
    public void move(int dx, int dy) {
        int randomNumber = Util.getRandomHealth(0,101);
        if(randomNumber <= 1){
        }
    }
}
