package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {
    @Test
    void loadMap1() {
        assertEquals("26 20\n" +
                "#############             \n" +
                "#.h........b#    ######## \n" +
                "#...s...s...######*h.p..# \n" +
                "#....b......g..d......p.# \n" +
                "#.....b.....######......# \n" +
                "#.....s.....#    #......# \n" +
                "#...........#    #**p...# \n" +
                "#a..........#    #h*...*# \n" +
                "#####..######    ###..### \n" +
                "    #..#           #gg#   \n" +
                "    #..#           #..#   \n" +
                "    #..#          ##..##  \n" +
                "  ###..####      #*....t# \n" +
                "  #.......#      #.t...*# \n" +
                "  #.......#      #....t.# \n" +
                "  #..@....#      #......e \n" +
                "  #.k...w.#      #..tt..##\n" +
                "  #.......#      #.....h# \n" +
                "  #..b....#      #.*...t# \n" +
                "  #########       ######  \n", MapLoader.loadMap(1,null));
    }

    @Test
    void loadMap2(){
        assertEquals("26 20\n" +
                "##########################\n" +
                "#.o.....t................#\n" +
                "#..........c.....i..%.tt.#\n" +
                "#.....ttt.......t......t.#\n" +
                "#.....tbt..t......io.....#\n" +
                "#........................#\n" +
                "#.....i.......c......i...#\n" +
                "#.........t.t.t......i...#\n" +
                "#....t.......c...........#\n" +
                "#.........t.t.t....t.....#\n" +
                "#........................#\n" +
                "#....o.......t...........#\n" +
                "#...................c....#\n" +
                "#......t.................#\n" +
                "#..i...........i.........#\n" +
                "#@.....t................t#\n" +
                "#................i.......#\n" +
                "#..........c.............#\n" +
                "#....t.o.tttt........i...#\n" +
                "##########################\n",MapLoader.loadMap(2,null));
    }

}