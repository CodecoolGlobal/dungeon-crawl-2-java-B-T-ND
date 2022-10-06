package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuardianTest {
    Guardian guardian;

    @BeforeEach
    void generateGuardian(){
        guardian = new Guardian(new Cell(new GameMap(10,10, CellType.FLOOR),5,5,CellType.FLOOR));
    }

    @Test
    void getTileName() {
        assertEquals("guardian", guardian.getTileName());
    }

    @Test
    void move() {
        int xBefore = guardian.getX();
        int yBefore = guardian.getY();
        guardian.move(1,1);
        int xAfter = guardian.getX();
        int yAfter = guardian.getY();
        int actual = (xBefore-xAfter) + (yBefore-yAfter);
        assertEquals(0,actual);
    }
}