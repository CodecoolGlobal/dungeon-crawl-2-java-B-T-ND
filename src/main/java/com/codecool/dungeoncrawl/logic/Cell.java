package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.Objects;

public class Cell implements Drawable {
    private Item item;
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void removeActor() {
        this.actor = null;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void removeItem() {
        this.item = null;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "gameMap=" + gameMap +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public char getCharacterRepresentation() {
        switch (getType()) {
            case EMPTY:
                return ' ';
            case WALL:
                return '#';
            case OPENDOOR:
            case CLOSEDDOOR:
                return 'd';
            case TOMBSTONE:
                return 't';
            case BONEPILE:
                return 'b';
            case EXIT:
                return 'e';
            case BUSH:
                return 'i';
            case FLOOR:
                Item itemOnCell = getItem();
                Actor actorOnCell = getActor();
                if (itemOnCell != null) {
                    if (itemOnCell instanceof Apple) {
                        return 'h';
                    } else if (itemOnCell instanceof Armor){
                        return 'a';
                    } else if (itemOnCell instanceof Crown){
                        return '%';
                    } else if (itemOnCell instanceof Key) {
                        return 'k';
                    }else if (itemOnCell instanceof SpiderWeb){
                        return '*';
                    } else if (itemOnCell instanceof Sword) {
                        return 'w';
                    }
                } else if (actorOnCell != null) {
                    if (actorOnCell instanceof Guardian) {
                        return 'g';
                    } else if (actorOnCell instanceof Friendly) {
                        if (Objects.equals(actorOnCell.getTileName(), "cat")){
                            return 'c';
                        } else if (Objects.equals(actorOnCell.getTileName(), "dog")) {
                            return 'o';
                        }
                    } else if (actorOnCell instanceof Player) {
                        return '@';
                    } else if (actorOnCell instanceof Skeleton) {
                        return 's';
                    } else if (actorOnCell instanceof Spider) {
                        return 'p';
                    }
                } else {
                    return '.';
                }
        }
        return ' ';
    }
}
