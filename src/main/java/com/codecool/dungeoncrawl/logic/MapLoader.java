package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.util.Util;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(int level) {
        InputStream is;
        if (level == 1) {
            is = MapLoader.class.getResourceAsStream("/map.txt");
        } else {
            is = MapLoader.class.getResourceAsStream("/map2.txt");
        }
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.CLOSEDDOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Sword(cell) {
                            });
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Key(cell));
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Armor(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Spider(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Guardian(cell));
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Apple(cell, 5));
                            break;
                        case '*':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new SpiderWeb(cell));
                            break;
                        case 't':
                            cell.setType(CellType.TOMBSTONE);
                            break;
                        case 'b':
                            cell.setType(CellType.BONEPILE);
                            break;
                        case 'e':
                            cell.setType(CellType.EXIT);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Friendly(cell,"cat"));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
