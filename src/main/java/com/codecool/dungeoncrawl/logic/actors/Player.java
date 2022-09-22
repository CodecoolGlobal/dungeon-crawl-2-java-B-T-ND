package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private static List<Item> inventory = new ArrayList<>();

    private static String info;

    private static int stuckUntil = 0;

    private static boolean hasKey = false;

    private static boolean hasSword = false;
    private static boolean hasArmor = false;

    private static boolean hasCrown = false;
    Cell cell = getCell();

    public Player(Cell cell) {
        super(cell, 15, 5, 0);
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
                playSound("inventory/metal-small1.wav");
                this.inventory.add(cell.getItem());
            } else if (cell.getItem() instanceof Sword) {
                hasSword = true;
                playSound("inventory/metal-small3.wav");
                Sword sword = (Sword) cell.getItem();
                this.increaseDamage(sword.getDamageBonus());
                this.inventory.add(cell.getItem());
            } else if (cell.getItem() instanceof Armor) {
                hasArmor = true;
                playSound("inventory/chainmail2.wav");
                this.increaseProtection(((Armor) cell.getItem()).getDamageReduction());
                this.inventory.add(cell.getItem());
            } else if (cell.getItem() instanceof Apple) {
                playSound("misc/apple.wav");
                this.increaseHealth(((Apple) cell.getItem()).getHealth());
            } else if (cell.getItem() instanceof Crown) {
                hasCrown = true;
                playSound("misc/victory.wav");
            }
            cell.removeItem();
        }

    }

    @Override
    public void move(int dx, int dy) {
        if (stuckUntil == 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (canMove(nextCell)) {
                playSound("misc/step.wav");
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                setCell(cell);
                if (cell.getType() == CellType.CLOSEDDOOR) {
                    cell.setType(CellType.OPENDOOR);
                    playSound("world/door.wav");
                }
            } else if (nextCell.getActor() != null && !(nextCell.getActor() instanceof Friendly)) {
                // if player hits a skeleton
                Actor enemy = nextCell.getActor();
                if (hasSword){
                    playSound("battle/sword-unsheathe4.wav");
                }else {
                    playSound("battle/swing2.wav");
                }
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
        return ((nextCell.getType() == CellType.FLOOR && nextCell.getActor() == null) || nextCell.getType() == CellType.EXIT) ||
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

    public boolean hasCrown(){
        return hasCrown;
    }
}
