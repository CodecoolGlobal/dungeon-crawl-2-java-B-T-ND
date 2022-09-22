package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.util.Util;

public class Spider extends Actor{
    Cell cell;
    public Spider(Cell cell) {
        super(cell, Util.getRandomHealth(10, 21), 3, 0);
    }

    @Override
    public String getTileName() {
        return "spider";
    }

    @Override
    public void move(int playerX, int playerY) {
        Cell nextCell;
        int randomNumber = Util.getRandomHealth(0,101);
        if(randomNumber <= 1){
            playSound("misc/spider.wav");
        }
        if(playerX-getCell().getX()<0 && playerX-getCell().getX()>-4 && playerY-getCell().getY()>-4 && playerY-getCell().getY()<4){
            nextCell = getCell().getNeighbor(-1, 0);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else if (playerX-getCell().getX()>0 && playerX-getCell().getX()<4 && playerY-getCell().getY()<4 && playerY-getCell().getY()>-4) {
            nextCell = getCell().getNeighbor(1, 0);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else if (playerY-getCell().getY()<0 && playerY-getCell().getY()>-4 && playerX-getCell().getX()>-4 && playerX-getCell().getX()<4) {
            nextCell = getCell().getNeighbor(0, -1);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else if (playerY-getCell().getY()>0 && playerY-getCell().getY()<4 && playerX-getCell().getX()<4 && playerX-getCell().getX()>-4) {
            nextCell = getCell().getNeighbor(0, 1);
            if (nextCell.getType()== CellType.FLOOR && nextCell.getActor()==null){
                moveSpider(nextCell);
            }
        } else{
            int[] randomDirection;
            randomDirection = Util.getRandomDirection();
            nextCell = getCell().getNeighbor(randomDirection[0], randomDirection[1]);
            if (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null){
                getCell().setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                setCell(cell);
            }
        }
    }

    private void moveSpider(Cell nextCell) {
        getCell().setActor(null);
        nextCell.setActor(this);
        setCell(nextCell);
    }
}
