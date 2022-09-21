package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    OPENDOOR("openDoor"),
    CLOSEDDOOR("closedDoor"),
    TOMBSTONE("tombstone"),
    BONEPILE("bonepile"),
    EXIT("exit");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
