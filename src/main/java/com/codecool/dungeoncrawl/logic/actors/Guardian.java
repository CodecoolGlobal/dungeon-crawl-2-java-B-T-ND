package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

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

    }
}
