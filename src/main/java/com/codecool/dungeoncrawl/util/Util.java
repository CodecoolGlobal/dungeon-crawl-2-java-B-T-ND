package com.codecool.dungeoncrawl.util;


import java.rmi.server.RemoteRef;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Util {
    public static int getRandomHealth(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
