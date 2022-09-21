package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;

    private String info;

    private int stuckUntil = 0;

    private boolean hasKey = false;

    private boolean hasSword = false;
    private boolean hasArmor = false;
    Cell cell = getCell();

    public Player(Cell cell) {
        super(cell, 15, 5, 0);
        this.inventory = new LinkedList<>();
        this.info = "";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void pickUpItem() {
        if (cell.getItem() != null) {
            if (cell.getItem() instanceof Key) {
                hasKey = true;
                this.inventory.add(cell.getItem());
            } else if (cell.getItem() instanceof Sword) {
                hasSword = true;
                Sword sword = (Sword) cell.getItem();
                this.increaseDamage(sword.getDamageBonus());
                this.inventory.add(cell.getItem());
            } else if (cell.getItem() instanceof Armor) {
                hasArmor = true;
                this.increaseProtection(((Armor) cell.getItem()).getDamageReduction());
                this.inventory.add(cell.getItem());
            } else if (cell.getItem() instanceof Apple) {
                this.increaseHealth(((Apple) cell.getItem()).getHealth());
            }
            cell.removeItem();
        }

    }

    @Override
    public void move(int dx, int dy) {
        if (stuckUntil == 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (canMove(nextCell)) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                setCell(cell);
                if (cell.getType() == CellType.CLOSEDDOOR) {
                    cell.setType(CellType.OPENDOOR);
                }
            } else if (nextCell.getActor() != null) {
                // if player hits a skeleton
                Actor enemy = nextCell.getActor();
                enemy.decreaseHealth(this.getDamage());
                if (enemy.getHealth() > 0) {
                    if (hasArmor) {
                        this.decreaseHealth(enemy.getDamage() - getProtection());
                    } else {
                        // if skeleton still has health
                        this.decreaseHealth(enemy.getDamage());
                    }
                } else {
                    nextCell.removeActor();
                }
            }
            if (cell.getItem() instanceof SpiderWeb){
               stuckUntil = 5;
               cell.removeItem();
            }
        } else {
            stuckUntil--;
        }
    }

    private boolean canMove(Cell nextCell) {
        return (nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null) ||
                (nextCell.getType() == CellType.CLOSEDDOOR && hasKey) ||
                nextCell.getType() == CellType.OPENDOOR;
    }

    public String getTileName() {
        if (hasSword && hasArmor) {
            return "playerWithArmorAndSword";
        }
        if (hasArmor) {
            return "playerWithArmor";
        }
        if (hasSword) {
            return "playerWithSword";
        }
        return "player";
    }

    public String getInventoryToString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : inventory) {
            sb.append("- ").append(item.getTileName()).append("\n");
        }
        return sb.toString();
    }
}
