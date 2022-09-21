package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    private static Map<String, Tile> tileMap2 = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("playerWithSword", new Tile(27,0));
        tileMap.put("playerWithArmor", new Tile(30,0));
        tileMap.put("playerWithArmorAndSword", new Tile(31,0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0,31));
        tileMap.put("armor", new Tile(1,23));
        tileMap.put("key", new Tile(17,23));
        tileMap.put("closedDoor", new Tile(3, 9));
        tileMap.put("openDoor", new Tile(6, 9));
        tileMap.put("spider", new Tile(29, 5));
        tileMap.put("guardian", new Tile(30, 6));
        tileMap.put("apple", new Tile(15, 29));
        tileMap.put("spiderWeb", new Tile(21, 23));
        tileMap.put("tombstone", new Tile(0, 14));
        tileMap.put("bonepile", new Tile(0, 15));
        tileMap.put("exit", new Tile(1, 11));
    }

    static {
        tileMap2.put("empty", new Tile(0, 0));
        tileMap2.put("wall", new Tile(1, 3));
        tileMap2.put("floor", new Tile(5, 0));
        tileMap2.put("player", new Tile(25, 0));
        tileMap2.put("playerWithSword", new Tile(27,0));
        tileMap2.put("playerWithArmor", new Tile(30,0));
        tileMap2.put("playerWithArmorAndSword", new Tile(31,0));
        tileMap2.put("cat", new Tile(29, 7));
        tileMap2.put("sword", new Tile(0,31));
        tileMap2.put("armor", new Tile(1,23));
        tileMap2.put("key", new Tile(17,23));
        tileMap2.put("apple", new Tile(15, 29));
        tileMap2.put("tombstone", new Tile(4, 2));
        tileMap2.put("bonepile", new Tile(18, 10));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y, int level) {
        Tile tile;
        if (level == 1){
            tile = tileMap.get(d.getTileName());
        } else {
            tile = tileMap2.get(d.getTileName());
        }
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
