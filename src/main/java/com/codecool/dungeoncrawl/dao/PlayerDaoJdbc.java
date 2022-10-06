package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y, inventory) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            String[] getInventoryArray = new String[player.getInventory().size()];
            getInventoryArray = player.getInventory().toArray(getInventoryArray);
            Array inventoryArray = conn.createArrayOf("text",getInventoryArray);
            statement.setArray(5, inventoryArray);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET hp = ?, x = ?, y = ?, inventory = ? WHERE player_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, player.getHp());
            statement.setInt(2, player.getX());
            statement.setInt(3, player.getY());
            String[] getInventoryArray = new String[player.getInventory().size()];
            getInventoryArray = player.getInventory().toArray(getInventoryArray);
            Array inventoryArray = conn.createArrayOf("text",getInventoryArray);
            statement.setArray(4, inventoryArray);
            statement.setString(5, player.getPlayerName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PlayerModel get(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_name FROM player WHERE player_name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            PlayerModel player = new PlayerModel(rs.getString(1), rs.getInt(2), rs.getInt(3), (List<String>) rs.getArray(4));
            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        try(Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM player";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            List<PlayerModel> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                List inventory = Arrays.asList(rs.getArray(5));
                PlayerModel player = new PlayerModel(rs.getString(2), rs.getInt(3), rs.getInt(4), inventory );
                result.add(player);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean doesExist(String playerName){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT player_name FROM player WHERE player_name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, playerName);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
