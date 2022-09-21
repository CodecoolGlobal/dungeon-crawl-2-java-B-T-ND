package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Apple extends Item{
    private int health;

    public Apple(Cell cell, int health) {
        super(cell);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String getTileName() {
        return "apple";
    }
}
