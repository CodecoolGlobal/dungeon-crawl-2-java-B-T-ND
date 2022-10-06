package com.codecool.dungeoncrawl.util;


import com.codecool.dungeoncrawl.model.GameState;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    static int randomNumberDirection;
    static int[][] randomCoordinates = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int getRandomHealth(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int[] getRandomDirection() {
        randomNumberDirection = getRandomHealth(0, 4);
        return randomCoordinates[randomNumberDirection];
    }

    public static void createSave(String filename, JsonObject save) {
        try {
            File myObj = new File("src/main/resources/saves/" + filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(save.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static GameState readJsonGamestate(String filename){


        return null;
    }
}
