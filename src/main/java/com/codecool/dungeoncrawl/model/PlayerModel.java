package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.List;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int x;
    private int y;

    private List<String> inventory;

    public PlayerModel(String playerName, int hp, int x, int y, List<String>inventory) {
        this.inventory = inventory;
        this.playerName = playerName;
        this.hp = hp;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.inventory = player.getInventory();
        this.hp = player.getHealth();

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player convertToPlayer(PlayerModel playerModel){
        Player player = new Player(new Cell(new GameMap(26, 20, CellType.FLOOR),playerModel.x, playerModel.y,CellType.FLOOR), playerModel.getHp(), playerModel.getInventory());
        player.setInventory(playerModel.getInventory());
        return player;
    }
}
