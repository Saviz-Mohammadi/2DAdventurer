import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class LevelManager {

    // DEPENDENCIES:
    private Game game;

    // PROPERTIES:
    private Tile[][] tiles;
    // we can do something similar for enemies and collectables where we have an array of them.


    public LevelManager() {

        this.tiles = new Tile[SettingsManager.getInstance().COLUMNS][SettingsManager.getInstance().ROWS];
    }

    public void init(Game game) {

        this.game = game;
    }

    public void loadLevel(BufferedImage levelImage) {

        // NOTE(SAVIZ): It is assumed that each image consists of a grid of one-pixel-sized cells, arranged in rows and columns, where each cell contains an RGB value for red, green, or blue, ranging from 0 to 255.

        // NOTE(SAVIZ): This part is tricky, so let me explain. We have two nested for-loops that iterate through each coordinate tile color in our image. Based on the color, we determine what type of element is at that position—whether it's a tile, an enemy, or a collectable. Once identified, we need to create these elements and potentially manage their rendering, spawning, and other properties. The most important aspect is their location, which each element will store internally. However, the key challenge is that we cannot simply assign the x and y coordinates directly from our loops, as these values only represent the row and column indices of the elements within the grid. Instead, we must adjust their position accurately by applying an offset equal to their size, ensuring proper alignment. Since all elements should share a uniform size, this offset will be consistent across all of them.

        // TODO(SAVIZ): Currently there is a problem, what if at a certain position x, y there are enemies or something else and tile cannot be created at that point. this creates a problem where we cannot properyl check for collision. The answer is very simple, since we only care about Tiles, we can make a qucik null check in collision and not care at all if there are no tiles there.
        int offest = SettingsManager.getInstance().TILE_SCALED_SIZE;

        for (int yCoordinate = 0; yCoordinate < levelImage.getHeight(); yCoordinate++) {

            for (int xCoordinate = 0; xCoordinate < levelImage.getWidth(); xCoordinate++) {

                int encodedColor = levelImage.getRGB(xCoordinate, yCoordinate);

                // Something that we could do is since we know for a fact that all enemies and collectable swill be placed in a tile that will be of type Air. just create an Air tile in their positions anyway to avoid nell references.
                // NOTE(SAVIZ): The second parameter 'true' indicates that the pixel may have an alpha component.
                Color color = new Color(encodedColor, true);

                int red   = color.getRed();
                int green = color.getGreen();
                int blue  = color.getBlue();

                // TILES (GREEN):
                if (green >= red && green >= blue) {

                    Tile tile = new Tile();

                    switch(green) {

                        case 1:
                            tile.xCoordinate = xCoordinate * offest;
                            tile.yCoordinate = yCoordinate * offest;
                            tile.imageKey = "tile_0000";
                            tile.isSolid = false;
                            break;
                        case 50:
                            tile.xCoordinate = xCoordinate * offest;
                            tile.yCoordinate = yCoordinate * offest;
                            tile.imageKey = "tile_0034";
                            tile.isSolid = true;
                            break;
                        default: // If the number is outside of this range, then it is an incorrect value and we will assign some default tile to this coordinate:
                            break;
                    }

                    tiles[xCoordinate][yCoordinate] = tile;
                }

                // ENEMIES (RED):
                else if (red >= green && red >= blue) {

                    // For enemeies you can simply set their type and starting location and let them handle their drawing.
                    // for default for enemias you can just opt for not creating them or something else...
                }

                // COLLECTABLES (BLUE):
                else if (blue >= red && blue >= green) {

                    // I have on idea what we plan for collecatbles such as keys and other items so let it just be here for now.
                    // for default for collecables you can just opt for not creating them or something else...
                }
            }
        }
    }

    public void update() {

    }

    public void render(Graphics graphics) {

        // TODO(SAVIZ): For transitioning between levels, what you can do is add a boolean 'isLoading' to the levelmanager and have it be changed during teh loading method. Here you can check it and fully withdraw if we are in loading phase.

        int height = SettingsManager.getInstance().ROWS;
        int width = SettingsManager.getInstance().COLUMNS;
        int scale = SettingsManager.getInstance().TILE_SCALED_SIZE;

        for (int yCoordinate = 0; yCoordinate < height; yCoordinate++) {

            for (int xCoordinate = 0; xCoordinate < width; xCoordinate++) {

                Tile tile = tiles[xCoordinate][yCoordinate];

                graphics.drawImage(ResourceManager.getInstance().getImageUsingKey(tile.imageKey), tile.xCoordinate, tile.yCoordinate, scale, scale, null);
            }
        }
    }

    public Tile[][] getTiles() {

        return(this.tiles);
    }
}
