package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
    }
    public void updatePlayer( Player player){
        PlayerModel model = new PlayerModel(player);
        playerDao.update(model);
    }

    public List<PlayerModel> getAllPlayers(){
        return playerDao.getAll();
    }

    public void saveMap(int currentMap, GameMap map, Player player){
        PlayerModel playerModel = new PlayerModel(player);
        GameState gameModel = new GameState(currentMap, map.toString(), playerModel);
        gameStateDao.add(gameModel);
    }

    public void updateMap(int currentMap, GameMap map, Player player){
        PlayerModel playerModel = new PlayerModel(player);
        GameState gameModel = new GameState(currentMap, map.toString(), playerModel);
        gameStateDao.update(gameModel);
    }

    public PlayerModel getPlayer(String name){
        PlayerModel player =  playerDao.get(name);
        return  player;
    }

    public GameState getGameState(PlayerModel player){
        GameState gamestate = gameStateDao.get(player);
        return gamestate;
    }



    public boolean doesExist(String name){
        return playerDao.doesExist(name);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "dungeoncrawl";
        String user = "abukfa97";
        String password = "Smileyskater97#";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
