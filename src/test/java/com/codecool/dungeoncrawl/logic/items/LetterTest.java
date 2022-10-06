package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterTest {
    static Letter letter;

    @BeforeAll
    public static void createLetter(){
        letter = new Letter(new Cell(new GameMap(5,5, CellType.FLOOR), 0,1,CellType.FLOOR),"w");
    }

    @Test
    void getTileName() {
        assertEquals("w", letter.getTileName());
    }
}