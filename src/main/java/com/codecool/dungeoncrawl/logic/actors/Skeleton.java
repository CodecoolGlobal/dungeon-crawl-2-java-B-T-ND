package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.Util;

import java.util.Random;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell, Util.getRandomHealth(5, 11), 2, 0);
    }

    @Override
    public void move(int dx, int dy) {

    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
