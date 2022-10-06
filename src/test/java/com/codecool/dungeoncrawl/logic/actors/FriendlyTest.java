package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FriendlyTest {
    Friendly friendly;

    @BeforeEach
    void createFriendly(){
        friendly = new Friendly(new Cell(new GameMap(10,10,CellType.FLOOR),5,5,CellType.FLOOR),"cat");

    }

    @Test
    void getTileName() {
        assertEquals("cat", friendly.getTileName());
    }

    @Test
    void moveForwardPlayerMinusX() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),6,3,CellType.FLOOR));
        int xBefore = friendly.getX();
        friendly.move(player.getX(), player.getY());
        int xAfter = friendly.getX();
        int actual = xBefore-xAfter;
        int expected = -1;
        assertEquals(expected,actual);
    }
    @Test
    void moveForwardPlayerPlusX() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),2,3,CellType.FLOOR));
        int xBefore = friendly.getX();
        friendly.move(player.getX(), player.getY());
        int xAfter = friendly.getX();
        int actual = xBefore-xAfter;
        int expected = 1;
        assertEquals(expected,actual);
    }
    @Test
    void moveForwardPlayerMinusY() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),5,7,CellType.FLOOR));
        int yBefore = friendly.getY();
        friendly.move(player.getX(), player.getY());
        int yAfter = friendly.getY();
        int actual = yBefore-yAfter;
        int expected = -1;
        assertEquals(expected,actual);
    }

    @Test
    void moveForwardPlayerPlusY() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),5,3,CellType.FLOOR));
        int yBefore = friendly.getY();
        friendly.move(player.getX(), player.getY());
        int yAfter = friendly.getY();
        int actual = yBefore-yAfter;
        int expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    void moveRandomOneBlockAway() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),0,0,CellType.FLOOR));
        int xBefore = friendly.getX();
        int yBefore = friendly.getY();
        friendly.move(player.getX(), player.getY());
        int xAfter = friendly.getX();
        int yAfter = friendly.getY();
        int actual = Math.abs(xBefore-xAfter) + Math.abs(yBefore-yAfter);
        int expected = 1;
        assertEquals(expected,actual);
    }


}