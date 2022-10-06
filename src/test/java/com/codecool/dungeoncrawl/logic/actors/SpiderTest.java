package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpiderTest {
    Spider spider;

    @BeforeEach
    void generateSpider(){
        spider = new Spider(new Cell(new GameMap(10,10, CellType.FLOOR),5,5,CellType.FLOOR));
    }

    @Test
    void getTileName() {
        assertEquals("spider", spider.getTileName());
    }

    @Test
    void moveForwardPlayerMinusX() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),6,3,CellType.FLOOR));
        int xBefore = spider.getX();
        spider.move(player.getX(), player.getY());
        int xAfter = spider.getX();
        int actual = xBefore-xAfter;
        int expected = -1;
        assertEquals(expected,actual);
    }
    @Test
    void moveForwardPlayerPlusX() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),2,3,CellType.FLOOR));
        int xBefore = spider.getX();
        spider.move(player.getX(), player.getY());
        int xAfter = spider.getX();
        int actual = xBefore-xAfter;
        int expected = 1;
        assertEquals(expected,actual);
    }
    @Test
    void moveForwardPlayerMinusY() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),5,7,CellType.FLOOR));
        int yBefore = spider.getY();
        spider.move(player.getX(), player.getY());
        int yAfter = spider.getY();
        int actual = yBefore-yAfter;
        int expected = -1;
        assertEquals(expected,actual);
    }

    @Test
    void moveForwardPlayerPlusY() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),5,3,CellType.FLOOR));
        int yBefore = spider.getY();
        spider.move(player.getX(), player.getY());
        int yAfter = spider.getY();
        int actual = yBefore-yAfter;
        int expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    void moveRandomOneBlockAway() {
        Player player = new Player(new Cell(new GameMap(10,10,CellType.FLOOR),0,0,CellType.FLOOR));
        int xBefore = spider.getX();
        int yBefore = spider.getY();
        spider.move(player.getX(), player.getY());
        int xAfter = spider.getX();
        int yAfter = spider.getY();
        int actual = Math.abs(xBefore-xAfter) + Math.abs(yBefore-yAfter);
        int expected = 1;
        assertEquals(expected,actual);
    }
}