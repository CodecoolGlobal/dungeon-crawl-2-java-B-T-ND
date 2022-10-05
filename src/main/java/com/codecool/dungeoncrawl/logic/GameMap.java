package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Item item;

    private List<Actor> monsters;

    private List<Actor> aliveMonsters;


    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        monsters = new ArrayList<>();
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public List<Actor> getAliveMonsters() {
        aliveMonsters = new ArrayList<>();
        for (Actor monster : monsters) {
            if (monster.getHealth() > 0) {
                aliveMonsters.add(monster);
            }
        }
        return aliveMonsters;
    }

    public void addMonster(Actor monster) {
        monsters.add(monster);
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(width).append(" ").append(height).append("\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell currentCell = getCell(x, y);
                stringBuilder.append(currentCell.getCharacterRepresentation());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
