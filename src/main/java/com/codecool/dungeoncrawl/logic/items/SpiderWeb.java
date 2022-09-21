package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class SpiderWeb extends Item{
    public SpiderWeb(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "spiderWeb";
    }
}
