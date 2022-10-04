package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.sql.SQLException;
import java.util.List;

import static javafx.application.Platform.exit;

public class Main extends Application {
    int currentMap = 1;
    GameMap map = MapLoader.loadMap(currentMap, null);

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO setupDbManager();
        GridPane ui = new GridPane();
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
        //ui.add(new Label("Player prompt: "), 0, 3);
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
        if (map.getPlayer().hasCrown()) {
            currentMap = 3;
            map = MapLoader.loadMap(currentMap, map.getPlayer());
            refresh();
            return;
        }
        if (map.getPlayer().getHealth() > 0 && !map.getPlayer().hasCrown()) {
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
                    map = MapLoader.loadMap(currentMap, map.getPlayer());
                    refresh();
                    break;
                case S:
                    Player player = map.getPlayer();
                    dbManager.savePlayer(player);
                    break;
            }
            if (map.getPlayer().getCell().getType() == CellType.EXIT) {
                currentMap++;

                map = MapLoader.loadMap(currentMap, map.getPlayer());
                refresh();

            }
        } else {
            currentMap = 4;
            playSound("misc/lost.wav");
            map = MapLoader.loadMap(currentMap, map.getPlayer());
            refresh();
        }
    }

    private void refresh() {
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
        healthLabel.setText("" + map.getPlayer().getHealth());
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
        inventoryLabel.setText("" + map.getPlayer().getInventoryToString());
        damageLabel.setText("" + map.getPlayer().getDamage());
        armorLabel.setText("" + map.getPlayer().getProtection());
        infoLabel.setText("" + map.getPlayer().getInfo());
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("/Sound/" + url));
                    clip.open(inputStream);
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-5.0f);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
