package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;

    private boolean hasKey = false;
    Cell cell = getCell();
    public Player(Cell cell) {
        super(cell);
        this.inventory = new LinkedList<>();
    }

    public void pickUpItem() {
        if (cell.getItem() != null){
            this.inventory.add(cell.getItem());
            if (cell.getItem() instanceof Key){
                hasKey = true;
            }
            cell.removeItem();
        }

    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (canMove(nextCell)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            if (cell.getType() == CellType.CLOSEDDOOR){
                cell.setType(CellType.OPENDOOR);
            }
        }
    }

    private boolean canMove(Cell nextCell) {
        return (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null) ||
                (nextCell.getType() == CellType.CLOSEDDOOR && hasKey) ||
                nextCell.getType() == CellType.OPENDOOR;
    }

    public String getTileName() {
        return "player";
    }

    public String getInventoryToString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : inventory) {
            sb.append(item.getTileName()).append("\n");
        }
        return sb.toString();
    }
}
