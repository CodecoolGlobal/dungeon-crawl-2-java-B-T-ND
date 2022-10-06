package com.codecool.dungeoncrawl.util;


import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.model.GameState;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    static final String pathToSaves = "./saves/";
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
            File myObj = new File(pathToSaves + filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(pathToSaves + filename);
            myWriter.write(save.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static GameMap readJsonGameMap(File file){
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(file.toPath());
            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
