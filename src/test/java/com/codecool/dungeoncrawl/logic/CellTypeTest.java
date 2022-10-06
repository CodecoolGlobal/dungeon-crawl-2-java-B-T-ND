package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTypeTest {

    @Test
    void getTileName() {
        assertEquals("empty", CellType.EMPTY.getTileName());
    }
}