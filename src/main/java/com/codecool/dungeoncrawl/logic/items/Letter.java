package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Letter extends Item {
    private String name;
    public Letter(Cell cell, String name) {
        super(cell);
        this.name = name;
    }

    @Override
    public String getTileName() {
        return name;
    }
}
