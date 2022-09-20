package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item{
    private int damageReduction;
    public Armor(Cell cell) {
        super(cell);
        damageReduction = 1;
    }

    @Override
    public String getTileName() {
        return "armor";
    }

    public int getDamageReduction() {
        return damageReduction;
    }
}
