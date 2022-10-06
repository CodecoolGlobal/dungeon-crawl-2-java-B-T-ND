package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppleTest {
    Apple apple;
    @BeforeAll
    void createApple(){
        apple = new Apple(new Cell(new GameMap(5,5, CellType.FLOOR),0,1,CellType.FLOOR),10);
    }

    @Test
    void getHealth() {
        assertEquals(5,apple.getHealth());
    }

    @Test
    void getTileName() {
        assertEquals("apple", apple.getTileName());
    }
}