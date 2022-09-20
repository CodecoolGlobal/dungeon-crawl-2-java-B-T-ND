package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.util.Util;

public class Spider extends Actor{
    public Spider(Cell cell) {
        super(cell, Util.getRandomHealth(10, 21), 3, 0);
    }

    @Override
    public String getTileName() {
        return "spider";
    }

    @Override
    public void move(int playerX, int playerY) {
        System.out.println(getHealth());
        Cell nextCell;
        if(playerX-getCell().getX()<0){
            nextCell = getCell().getNeighbor(-1, 0);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else if (playerX-getCell().getX()>0) {
            nextCell = getCell().getNeighbor(1, 0);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else if (playerY-getCell().getY()<0) {
            nextCell = getCell().getNeighbor(0, -1);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else if (playerY-getCell().getY()>0) {
            nextCell = getCell().getNeighbor(0, 1);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        }


    }

    private void moveSpider(Cell nextCell) {
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
    }
}
