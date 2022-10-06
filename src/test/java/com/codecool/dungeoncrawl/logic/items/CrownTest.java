package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrownTest {
    static Crown crown;

    @BeforeAll
    public static void createCrown(){
        crown = new Crown(new Cell(new GameMap(5,5, CellType.FLOOR), 0,1,CellType.FLOOR));
    }

    @Test
    void getTileName() {
        assertEquals("crown", crown.getTileName());
    }
}