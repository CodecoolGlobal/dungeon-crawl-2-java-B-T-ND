package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public class PlayerDaoJson implements PlayerDao{
    @Override
    public void add(PlayerModel player) {

    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    public PlayerModel get(String name) {
        return null;
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }

    @Override
    public boolean doesExist(String playerName) {
        return false;
    }
}
