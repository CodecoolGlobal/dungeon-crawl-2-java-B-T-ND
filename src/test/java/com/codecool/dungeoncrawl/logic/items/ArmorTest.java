package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {
    static Armor armor;

    @BeforeAll
    public static void createArmor(){
        armor = new Armor(new Cell(new GameMap(5,5, CellType.FLOOR), 0,1,CellType.FLOOR));
    }

    @Test
    void getTileName() {
        assertEquals("armor", armor.getTileName());
    }

    @Test
    void getDamageReduction() {
        assertEquals(1, armor.getDamageReduction());
    }
}