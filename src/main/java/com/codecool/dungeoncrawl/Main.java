package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.util.Util;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    int currentMap = 1;
    GameMap map = MapLoader.loadMap(currentMap, null, null);

    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    GameDatabaseManager dbManager;
    Label inventoryLabel = new Label();
    Label damageLabel = new Label();
    Label infoLabel = new Label();

    Label armorLabel = new Label();
    private List<Actor> monsters;

    public static void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO setupDbManager();
        GridPane ui = new GridPane();
        dbManager = new GameDatabaseManager();
        dbManager.setup();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Player damage: "), 0, 1);
        ui.add(damageLabel, 1, 1);
        ui.add(new Label("Player armor: "), 0, 2);
        ui.add(armorLabel, 1, 2);
        ui.add(new Label("Inventory: "), 0, 3);
        ui.add(inventoryLabel, 0, 4);
        // ui.add(new Label("Player prompt: "), 0, 3);
        ui.add(infoLabel, 0, 5);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                monsters = map.getAliveMonsters();
                for (Actor monster : monsters) {
                    monster.move(map.getPlayer().getCell().getX(), map.getPlayer().getCell().getY());
                }
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                monsters = map.getAliveMonsters();
                for (Actor monster : monsters) {
                    monster.move(map.getPlayer().getCell().getX(), map.getPlayer().getCell().getY());
                }
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                monsters = map.getAliveMonsters();
                for (Actor monster : monsters) {
                    monster.move(map.getPlayer().getCell().getX(), map.getPlayer().getCell().getY());
                }
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                monsters = map.getAliveMonsters();
                for (Actor monster : monsters) {
                    monster.move(map.getPlayer().getCell().getX(), map.getPlayer().getCell().getY());
                }
                refresh();
                break;
            case E:
                map.getPlayer().pickUpItem();
                refresh();
                break;
            case R:
                map = MapLoader.loadMap(currentMap, map.getPlayer(), null);
                refresh();
                break;
            case S:
                Popup pop = new Popup();
                pop.show(createWindowForSavePopup());
                break;
            case L:
                Popup popup = new Popup();
                popup.show(createWindowForLoadPopup());
                break;

        }
    }

    private void checkNextLevel() {
        if (map.getPlayer().getCell().getType() == CellType.EXIT) {
            // is player on exit-field
            currentMap++;
            map = MapLoader.loadMap(currentMap, map.getPlayer(), null);
        }
        if (map.getPlayer().hasCrown()) {
            // win condition
            currentMap = 3;
            map = MapLoader.loadMap(currentMap, map.getPlayer(), null);
        } else if (map.getPlayer().getHealth() <= 0) {
            // lose condition
            currentMap = 4;
            map = MapLoader.loadMap(currentMap, map.getPlayer(), null);
        }
    }

    private void refresh() {
        checkNextLevel();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y, currentMap);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y, currentMap);
                } else {
                    Tiles.drawTile(context, cell, x, y, currentMap);
                }
            }
        }
        refreshDisplay();
    }

    private void refreshDisplay() {
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText("" + map.getPlayer().getInventoryToString());
        damageLabel.setText("" + map.getPlayer().getDamage());
        armorLabel.setText("" + map.getPlayer().getProtection());
        infoLabel.setText("" + map.getPlayer().getInfo());
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    public Window createWindowForSavePopup() {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        TextField nameField = new TextField("Name");
        comp.getChildren().add(nameField);
        Button cancel = new Button("Cancel");
        cancel.setOnAction((e) -> newStage.close());
        cancel.setCancelButton(true);
        cancel.setDefaultButton(true);
        Button saveToSqlBtn = new Button("Save");
        Button saveToFileBtn = new Button("Save to file");
        saveToSqlBtn.setOnAction((e) -> {
            map.getPlayer().setName(nameField.getText());
            if (dbManager.doesExist(nameField.getText())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Overwrite existing save");
                alert.setContentText("Would you like to overwrite the already existing state?");
                ButtonType btnType = alert.showAndWait().orElse(ButtonType.CANCEL);
                if (btnType == ButtonType.CANCEL)
                    newStage.close();
                else {
                    dbManager.updatePlayer(map.getPlayer());
                    dbManager.updateMap(currentMap, map, map.getPlayer());
                    newStage.close();
                }
            } else {
                dbManager.savePlayer(map.getPlayer());
                dbManager.saveMap(currentMap, map, map.getPlayer());
                newStage.close();
            }
        });
        saveToFileBtn.setOnAction((e) -> {
            map.getPlayer().setName(nameField.getText());
            JsonObject gameState = map.getPlayer().serializeToJSON();
            gameState.addProperty("current_map", currentMap);
            gameState.addProperty("map", map.toString());
            Util.createSave(map.getPlayer().getName() + ".json", gameState);
            newStage.close();
        });

        comp.getChildren().add(cancel);
        comp.getChildren().add(saveToSqlBtn);
        comp.getChildren().add(saveToFileBtn);

        Scene stageScene = new Scene(comp, 300, 300);
        newStage.setScene(stageScene);
        newStage.show();
        return newStage;
    }

    private Window createWindowForLoadPopup() {
        Stage newStage = new Stage();
        VBox comp = new VBox();
        ListView<String> listView = new ListView<>();
        List<PlayerModel> players = dbManager.getAllPlayers();
        for (PlayerModel player : players) {
            listView.getItems().add(player.getPlayerName());
        }

        Button loadBtn = new Button("LOAD");
        loadBtn.setOnAction((e) -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            PlayerModel player = dbManager.getPlayer(selected);
            GameState gameState = dbManager.getGameState(player);
            map = MapLoader.loadMap(gameState.getCurrentMap(), player.convertToPlayer(player), gameState.getMap());
            newStage.close();
            refresh();
        });

        Button loadBtnJson = new Button("LOAD LOCAL SAVE");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open save File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Json files (.json)", "*.json"));
        loadBtnJson.setOnAction((e) -> {
            File file = fileChooser.showOpenDialog(newStage);
            map = Util.readJsonGameMap(file);
            newStage.close();
        });
        comp.getChildren().add(new Label("Select save file:"));
        comp.getChildren().add(listView);
        comp.getChildren().add(loadBtn);
        comp.getChildren().add(loadBtnJson);
        Button close = new Button("CLOSE");
        close.setOnAction((e) -> newStage.close());
        comp.getChildren().add(close);
        newStage.setScene(new Scene(comp, 300, 200));
        newStage.show();
        return newStage;
    }
}
