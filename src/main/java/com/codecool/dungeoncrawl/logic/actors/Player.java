package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.LinkedList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;
    public Player(Cell cell) {
        super(cell);
        this.inventory = new LinkedList<>();
    }

    public void addToInventory(Item item) {
        this.inventory.add(item);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        if (getCell().getItem() != null){

            // ask player if they want it
            // if yes
            this.addToInventory(getCell().getItem());
            getCell().removeItem();
            // else nothing
        }
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
