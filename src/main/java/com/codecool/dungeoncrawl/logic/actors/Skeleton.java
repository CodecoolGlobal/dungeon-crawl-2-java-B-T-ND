package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.util.Util;

import java.util.Random;

public class Skeleton extends Actor {
    int[] randomDirection;
    Cell cell;
    public Skeleton(Cell cell) {
        super(cell, Util.getRandomHealth(5, 11), 2, 0);
    }

    @Override
    public void move(int dx, int dy) {
        int randomNumber = Util.getRandomHealth(0,101);
        if(randomNumber <= 1){
        }
        randomDirection = Util.getRandomDirection();
        cell = getCell();
        Cell nextCell = cell.getNeighbor(randomDirection[0], randomDirection[1]);
        if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            setCell(cell);
        }

    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
