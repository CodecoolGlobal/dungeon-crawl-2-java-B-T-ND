package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Cell cell;

    @BeforeEach
    void generatePlayer(){
        cell = new Cell(new GameMap(10,10, CellType.FLOOR),5,5,CellType.FLOOR);
        player = new Player(cell);
    }

    @Test
    void getInfo() {
        assertEquals("", player.getInfo());
    }

    @Test
    void pickUpSword() {
        Sword sword = new Sword(cell);
        cell.setActor(player);
        cell.setItem(sword);
        player.pickUpItem();
        assertEquals("- sword\n", player.getInventoryToString());
    }

    @Test
    void pickUpApple() {
        Apple apple = new Apple(cell, 2);
        cell.setActor(player);
        cell.setItem(apple);
        player.pickUpItem();
        assertEquals(17, player.getHealth());
    }

    @Test
    void pickUpArmor() {
        Armor armor = new Armor(cell);
        cell.setActor(player);
        cell.setItem(armor);
        player.pickUpItem();
        assertEquals("- armor\n", player.getInventoryToString());
    }

    @Test
    void pickUpKey() {
        Key key = new Key(cell);
        cell.setActor(player);
        cell.setItem(key);
        player.pickUpItem();
        assertEquals("- key\n", player.getInventoryToString());
    }

    @Test
    void pickUpCrown() {
        Crown crown = new Crown(cell);
        cell.setActor(player);
        cell.setItem(crown);
        player.pickUpItem();
        assertTrue(player.hasCrown());
    }

    @Test
    void getInventory() {
        Sword sword = new Sword(cell);
        cell.setActor(player);
        cell.setItem(sword);
        player.pickUpItem();
        List<String> testInventory = new ArrayList<>();
        testInventory.add("sword");
        assertEquals(testInventory.get(0), player.getInventory().get(0));

    }

    @Test
    void setInfo() {
        player.setInfo("hello");
        assertEquals("hello", player.getInfo());
    }

    @Test
    void moveUp() {
        int xBefore = player.getX();
        player.move(1,0);
        int xAfter = player.getX();
        assertEquals(1,xAfter-xBefore);
    }

    @Test
    void getTileNamePlayerWithSword() {
        assertEquals("playerWithSword", player.getTileName());
    }

    @Test
    void getInventoryToString() {
        Sword sword = new Sword(cell);
        cell.setActor(player);
        cell.setItem(sword);
        player.pickUpItem();
        assertEquals("- sword\n", player.getInventoryToString());
    }

    @Test
    void hasCrown() {
        assertEquals(false, player.hasCrown());
    }

    @Test
    void setName() {
        player.setName("Béla");
        assertEquals("Béla", player.getName());
    }

    @Test
    void getName() {
        player.setName("Béla");
        assertEquals("Béla", player.getName());
    }
}