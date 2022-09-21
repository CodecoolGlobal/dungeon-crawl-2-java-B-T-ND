package com.codecool.dungeoncrawl.util;


import java.rmi.server.RemoteRef;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    static int randomNumberDirection;
    static int[][] randomCoordinates = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int getRandomHealth(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    public static int[] getRandomDirection(){
        randomNumberDirection = getRandomHealth(0,4);
        return randomCoordinates[randomNumberDirection];
    }
}
