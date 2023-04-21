package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Player extends Actor {
    private List<String> inventory = new ArrayList<>();

    private  String info;

    private String name;

    private  int stuckUntil = 0;

    private  boolean hasKey = false;

    private  boolean hasSword = false;

    private  boolean hasArmor = false;

    private  boolean hasCrown = false;
    Cell cell = getCell();

    public Player(Cell cell) {
        super(cell, 15, 5, 0);
        this.info = "";
    }

    public Player(Cell cell, int hp, List<String> inventory) {
        super(cell, hp, 5, 0);
        this.info = "";
        setInventory(inventory);
    }

    public String getInfo() {
        return info;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
        for(String inventoryItem: inventory){
            if(Objects.equals(inventoryItem, "sword")){
                hasSword = true;
                this.increaseDamage(2);
            }
            if (Objects.equals(inventoryItem, "key")){
                hasKey = true;
            }
            if (Objects.equals(inventoryItem, "armor")){
                hasArmor = true;
                this.increaseProtection(1);
            }
        }

    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void pickUpItem() {
        if (cell.getItem() != null) {
            if (cell.getItem() instanceof Key) {
                hasKey = true;
                this.inventory.add(cell.getItem().getTileName());
            } else if (cell.getItem() instanceof Sword) {
                hasSword = true;
                Sword sword = (Sword) cell.getItem();
                this.increaseDamage(sword.getDamageBonus());
                this.inventory.add(cell.getItem().getTileName());
            } else if (cell.getItem() instanceof Armor) {
                hasArmor = true;
                this.increaseProtection(((Armor) cell.getItem()).getDamageReduction());
                this.inventory.add(cell.getItem().getTileName());
            } else if (cell.getItem() instanceof Apple) {
                this.increaseHealth(((Apple) cell.getItem()).getHealth());
            } else if (cell.getItem() instanceof Crown) {
                hasCrown = true;
            }
            cell.removeItem();
        }

    }

    @Override
    public void move(int dx, int dy) {
        if (stuckUntil == 0) {
            Cell nextCell = getCell().getNeighbor(dx, dy);
            if (canMove(nextCell)) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
                setCell(cell);
                if (cell.getType() == CellType.CLOSEDDOOR) {
                    cell.setType(CellType.OPENDOOR);
                }
            } else if (nextCell.getActor() != null && !(nextCell.getActor() instanceof Friendly)) {
                // if player hits a skeleton
                Actor enemy = nextCell.getActor();
                if (hasSword){
                }else {
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

    public boolean canMove(Cell nextCell) {
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
        for (String item : inventory) {
            sb.append("- ").append(item).append("\n");
        }
        return sb.toString();
    }

    public boolean hasCrown(){
        return hasCrown;
    }

    public void setName(String text) {
        this.name = text;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "inventory=" + inventory +
                ", name='" + name + '\'' +
                ", cell=" + cell +
                '}';
    }

    public JsonObject serializeToJSON(){
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        jsonObject.addProperty("name",getName());
        jsonObject.addProperty("hp",getHealth());
        jsonObject.addProperty("x",getX());
        jsonObject.addProperty("y",getY());
        jsonObject.addProperty("inventory", gson.toJson(inventory));
        return jsonObject;
    }
}
