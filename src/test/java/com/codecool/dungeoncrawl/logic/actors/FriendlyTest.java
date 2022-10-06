package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendlyTest {
    Friendly friendly = new Friendly();

    @BeforeAll
    void createFriendly(){
        Friendly friendly = new Friendly(new Cell(new GameMap(5,5,CellType.FLOOR),0,1,CellType.FLOOR),"cat");

    }

    @Test
    void getTileName() {
        assertEquals("cat", friendly.getTileName());
    }

    @Test
    void move() {
        assertEquals();
    }
}