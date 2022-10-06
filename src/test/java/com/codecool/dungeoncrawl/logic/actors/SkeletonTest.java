package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkeletonTest {
    Skeleton skeleton;

    @BeforeEach
    void generateSkeleton(){
        skeleton = new Skeleton(new Cell(new GameMap(10,10, CellType.FLOOR),5,5,CellType.FLOOR));
    }

    @Test
    void move() {
        int beforeX = skeleton.getX();
        int beforeY = skeleton.getY();
        skeleton.move(1,0);
        int afterX = skeleton.getX();
        int afterY = skeleton.getY();
        int actual = Math.abs(beforeX-afterX) + Math.abs(beforeY-afterY);
        assertEquals(1,actual);
    }

    @Test
    void getTileName() {
        assertEquals("skeleton", skeleton.getTileName());

    }
}