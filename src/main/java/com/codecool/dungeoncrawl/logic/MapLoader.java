package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(int level, Player oldPlayer, String mapString) {
        InputStream is;
        if (mapString == null) {
            if (level == 1) {
                is = MapLoader.class.getResourceAsStream("/map.txt");
            } else if (level == 2) {
                is = MapLoader.class.getResourceAsStream("/map2_canvasSized.txt");
            } else if (level == 3) {
                is = MapLoader.class.getResourceAsStream("/youWinMap.txt");
            } else {
                is = MapLoader.class.getResourceAsStream("/youLostMap.txt");
            }
        } else{
            is = new ByteArrayInputStream(mapString.getBytes());
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
                            System.out.println(cell.getType());
                            System.out.println(cell.toString());
                            if(oldPlayer==null){
                                map.setPlayer(new Player(cell));
                            } else{
                                oldPlayer.setCell(cell);
                                map.setPlayer(oldPlayer);
                            }
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
                            map.addMonster(new Friendly(cell, "cat"));
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Friendly(cell, "dog"));
                            break;
                        case 'i':
                            cell.setType(CellType.BUSH);
                            break;
                        case '%':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Crown(cell));
                            break;
                        case 'Y':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "Y"));
                            break;
                        case 'O':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "O"));
                            break;
                        case 'U':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "U"));
                            break;
                        case 'W':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "W"));
                            break;
                        case 'N':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "N"));
                            break;
                        case 'L':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "L"));
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "S"));
                            break;
                        case 'T':
                            cell.setType(CellType.FLOOR);
                            map.setItem(new Letter(cell, "T"));
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
