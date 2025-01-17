package de.tum.cit.fop.maze.gameobject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Maze {
    private float mapWidth, mapHeight;
    private Character character;
    private Entrance entrance;
    private List<Wall> walls = new ArrayList<>();
    private List<Exit> exits = new ArrayList<>();
    private List<Trap> traps = new ArrayList<>();
    private List<Mob> mobs = new ArrayList<>();
    private List<Key> keys = new ArrayList<>();

    private final String mapFile;
    private Properties properties;

    public Maze(String mapFile) {
        if (mapFile == null || mapFile.isEmpty()) {
            throw new IllegalArgumentException("The map file path cannot be empty!");
        }
        this.mapFile = mapFile;
        this.properties = loadProperties(this.mapFile);
        this.mapWidth = 1f; // 默认宽度
        this.mapHeight = 1f; // 默认高度
    }

    private Properties loadProperties(String file) {
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
        } catch (IOException e) {
            System.err.println("Error occurred while loading the configuration file: " + e.getMessage());
            throw new RuntimeException("Unable to load the map configuration file: " + file, e);
        } catch (Exception e) {
            System.err.println("An unknown error occurred: " + e.getMessage());
            throw new RuntimeException("An unknown error caused the file loading to fail", e);
        }
        return properties;
    }

    public void readMoreProperties() {
        for (String propertyName : properties.stringPropertyNames()) {
            String propertyValue = properties.getProperty(propertyName);

            if (propertyName.equals("Width")) {
                mapWidth = Integer.parseInt(propertyValue);
            } else if (propertyName.equals("Height")) {
                mapHeight = Integer.parseInt(propertyValue);
            } else {
                processMapElement(propertyName, propertyValue);
            }
        }
    }

    private void processMapElement(String propertyName, String propertyValue) {
        try {
            String[] coordinate = propertyName.split(",");
            int x = Integer.parseInt(coordinate[0]) * 16;
            int y = Integer.parseInt(coordinate[1]) * 16;

            mapWidth = Math.max(mapWidth, x);
            mapHeight = Math.max(mapHeight, y);

            int value = Integer.parseInt(propertyValue);
            createMazeElement(x, y, value);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid map element: " + propertyName + "=" + propertyValue);
        }
    }

    private void createMazeElement(int x, int y, int value) {
        switch (value) {
            case 0:
                walls.add(new Wall(x, y));
                break;
            case 1:
                entrance = new Entrance(x, y);
                character = new Character(x, y, 1f, 2f);
                break;
            case 2:
                exits.add(new Exit(x, y));
                break;
            case 3:
                traps.add(new Trap(x, y));
                break;
            case 4:
                mobs.add(new Mob(x, y, 2));
                break;
            case 5:
                keys.add(new Key(x, y));
                break;
            default:
                System.err.println("Unknown element type: " + value);
        }
    }

    public Character getCharacter() {
        return character;
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public List<Trap> getTraps() {
        return traps;
    }

    public List<Mob> getMobs() {
        return mobs;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public float getMapWidth() {
        return mapWidth;
    }

    public float getMapHeight() {
        return mapHeight;
    }

    public String getMapFile() {
        return mapFile;
    }

    public Properties getProperties() {
        return properties;
    }
}
