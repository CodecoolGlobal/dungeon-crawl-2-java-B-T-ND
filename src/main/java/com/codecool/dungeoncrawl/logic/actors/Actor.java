package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;



    private int health;

    private int protection;

    private int damage;

    public int getDamage() {
        return damage;
    }

    public Actor(Cell cell, int health, int damage, int protection) {
        this.cell = cell;
        this.cell.setActor(this);
        this.health = health;
        this.damage = damage;
        this.protection = protection;
    }


    public abstract void move(int dx, int dy);

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int value) {
        health -= value;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void increaseDamage(int damageBonus){
        damage += damageBonus;
    }

    public int getProtection() {
        return protection;
    }

    public void increaseProtection(int protection){
        this.protection += protection;
    }
}
