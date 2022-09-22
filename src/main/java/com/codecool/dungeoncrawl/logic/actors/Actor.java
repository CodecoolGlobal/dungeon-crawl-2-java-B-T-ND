package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
    public void increaseHealth(int value) {
        health += value;
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

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("/Sound/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
