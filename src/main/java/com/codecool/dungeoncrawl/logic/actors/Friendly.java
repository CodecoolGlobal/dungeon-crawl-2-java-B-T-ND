package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.util.Util;

import java.util.Random;

public class Friendly extends Actor{
    String type;
    public Friendly(Cell cell, String type) {
        super(cell, 5, 0, 100);
        this.type = type;
    }

    @Override
    public String getTileName() {
        return type;
    }

    @Override
    public void move(int playerX, int playerY) {
        Cell nextCell;
        if(playerX-getCell().getX()<0 && playerX-getCell().getX()>-4 && playerY-getCell().getY()>-4 && playerY-getCell().getY()<4){
            nextCell = getCell().getNeighbor(-1, 0);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveFriendly(nextCell);
            }
        } else if (playerX-getCell().getX()>0 && playerX-getCell().getX()<4 && playerY-getCell().getY()<4 && playerY-getCell().getY()>-4) {
            nextCell = getCell().getNeighbor(1, 0);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveFriendly(nextCell);
            }
        } else if (playerY-getCell().getY()<0 && playerY-getCell().getY()>-4 && playerX-getCell().getX()>-4 && playerX-getCell().getX()<4) {
            nextCell = getCell().getNeighbor(0, -1);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveFriendly(nextCell);
            }
        } else if (playerY-getCell().getY()>0 && playerY-getCell().getY()<4 && playerX-getCell().getX()<4 && playerX-getCell().getX()>-4) {
            nextCell = getCell().getNeighbor(0, 1);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveFriendly(nextCell);
            }
        } else{
            int[] randomDirection;
            randomDirection = Util.getRandomDirection();
            nextCell = getCell().getNeighbor(randomDirection[0], randomDirection[1]);
            if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null){
                getCell().setActor(null);
                nextCell.setActor(this);
                setCell(nextCell);
            }
        }
    }

    private void moveFriendly(Cell nextCell) {
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
    }
}
