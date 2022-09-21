package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{
    private final int damageBonus;
    public Sword(Cell cell) {
        super(cell);
        this.damageBonus = 2;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
