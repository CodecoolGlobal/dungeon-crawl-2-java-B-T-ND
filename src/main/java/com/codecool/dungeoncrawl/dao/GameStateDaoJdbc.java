package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState gameState) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, map, player_name, saved_at) VALUES (?, ?, ?, now())";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameState.getCurrentMap());
            statement.setString(2, gameState.getMap());
            statement.setString(3, gameState.getPlayerName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState gameState) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = " UPDATE game_state SET current_map = ?, map = ?, saved_at = now() WHERE player_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, gameState.getCurrentMap());
            statement.setString(2, gameState.getMap());
            statement.setString(3, gameState.getPlayerName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT current_map,map, player_name FROM game_state WHERE player_name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, player.getPlayerName());
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            GameState gameState = new GameState(rs.getInt(1), rs.getString(2), player);
            return gameState;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
