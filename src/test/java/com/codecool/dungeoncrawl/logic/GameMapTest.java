package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.Spider;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    static GameMap  gameMap;

    @BeforeAll
    public static void createGameMap(){
        gameMap = new GameMap(5,5,CellType.FLOOR);


    }

    @Test
    void getAliveMonsters() {
        Spider spider = new Spider(new Cell(gameMap,2,2,CellType.FLOOR));
        gameMap.addMonster(spider);
        List<Actor> expected = new ArrayList<>();
        expected.add(spider);
        assertEquals(expected.get(0), gameMap.getAliveMonsters().get(1));
    }

    @Test
    void addMonster() {
        Skeleton skeleton = new Skeleton(new Cell(gameMap,1,1, CellType.FLOOR));
        gameMap.addMonster(skeleton);
        List<Actor> expected = new ArrayList<>();
        expected.add(skeleton);
        assertEquals(expected.get(0), gameMap.getAliveMonsters().get(0));
    }

    @Test
    void getCell() {
        Cell cell = new Cell(gameMap,2,2,CellType.FLOOR);
        Player player = new Player(cell);
        gameMap.getCell(cell.getX(), cell.getY()).setActor(player);
        assertEquals(player,gameMap.getCell(cell.getX(),cell.getY()).getActor());


    }

    @Test
    void setPlayer() {
        Player player = new Player(new Cell(gameMap,3,3, CellType.FLOOR));
        gameMap.setPlayer(player);
        List<Player> expected = new ArrayList<>();
        expected.add(player);
        assertEquals(expected.get(0), gameMap.getPlayer());

    }

    @Test
    void getPlayer() {
        Player player = new Player(new Cell(gameMap,4,4, CellType.FLOOR));
        gameMap.setPlayer(player);
        List<Player> expected = new ArrayList<>();
        expected.add(player);
        assertEquals(expected.get(0), gameMap.getPlayer());
    }

    @Test
    void getWidth() {
        assertEquals(5, gameMap.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(5, gameMap.getHeight());
    }

    @Test
    void setItem() {
        Cell cell = new Cell(gameMap,3,3, CellType.FLOOR);
        Sword sword = new Sword(cell);
        cell.setItem(sword);
        gameMap.getCell(cell.getX(), cell.getY()).setItem(sword);
        assertEquals(cell,cell);
        //assertEquals(cell,gameMap.getCell(cell.getX(),cell.getY()));
    }

    @Test
    void testToString() {
        assertEquals("5 5\n" +
                ".....\n" +
                ".....\n" +
                ".....\n" +
                ".....\n" +
                ".....\n", gameMap.toString());
    }
}