import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class LevelManager {

    // CONSTANTS:
    private final static int TOPBORDER = (int)(0.2 * SettingsManager.getInstance().GAME_HEIGHT);
    private final static int BOTTOMBORDER = (int)(0.8 * SettingsManager.getInstance().GAME_HEIGHT);
    private final static int LEFTBORDER = (int)(0.2 * SettingsManager.getInstance().GAME_WIDTH);
    private final static int RIGHTBORDER = (int)(0.8 * SettingsManager.getInstance().GAME_WIDTH);

    // DEPENDENCIES:
    private Game game = null;

    // PROPERTIES:
    public int width = 0;
    public int height = 0;
    public int levelWidth = 0;
    public int levelHeight = 0;
    public int xLevelOffset = 0;
    public int yLevelOffset = 0;
    private int xMaxOffset = 0;
    private int yMaxOffset = 0;
    private Tile[][] tiles = null;
    // we can do something similar for enemies and collectables where we have an array of them.


    public LevelManager() {

    }

    public void init(Game game) {

        this.game = game;
    }

    public void loadLevel(BufferedImage levelImage) {

        this.width = levelImage.getWidth();
        this.height = levelImage.getHeight();
        this.levelWidth = levelImage.getWidth() * SettingsManager.getInstance().TILE_SCALED_SIZE;
        this.levelHeight = levelImage.getHeight() * SettingsManager.getInstance().TILE_SCALED_SIZE;
        this.xMaxOffset = (this.levelWidth - SettingsManager.getInstance().COLUMNS) * SettingsManager.getInstance().TILE_SCALED_SIZE;
        this.yMaxOffset = (this.levelHeight - SettingsManager.getInstance().ROWS) * SettingsManager.getInstance().TILE_SCALED_SIZE;

        this.tiles = new Tile[this.width][this.height];

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

        int playerX = (int) this.game.getPlayer().getHitBox().getX();
        int diffX = playerX - this.xLevelOffset;

        int playerY = (int) this.game.getPlayer().getHitBox().getY();
        int diffY = playerY - this.yLevelOffset;

        // X:
        if(diffX > this.RIGHTBORDER) {

            this.xLevelOffset += diffX - this.RIGHTBORDER;
        }

        else if(diffX < this.LEFTBORDER) {

            this.xLevelOffset += diffX - this.LEFTBORDER;
        }

        if(this.xLevelOffset > this.xMaxOffset) {

            this.xLevelOffset = this.xMaxOffset;
        }

        else if(this.xLevelOffset < 0) {

            this.xLevelOffset = 0;
        }


        // Y:
        if(diffY > this.TOPBORDER) {

            this.yLevelOffset += diffY - this.TOPBORDER;
        }

        else if(diffY < this.BOTTOMBORDER) {

            this.yLevelOffset += diffY - this.LEFTBORDER;
        }

        if(this.yLevelOffset > this.yMaxOffset) {

            this.yLevelOffset = this.yMaxOffset;
        }

        else if(this.yLevelOffset < 0) {

            this.yLevelOffset = 0;
        }
    }

    public void render(Graphics graphics) {

        int scale = SettingsManager.getInstance().TILE_SCALED_SIZE;

        for (int yCoordinate = 0; yCoordinate < this.height; yCoordinate++) {

            for (int xCoordinate = 0; xCoordinate < this.width; xCoordinate++) {

                Tile tile = tiles[xCoordinate][yCoordinate];

                graphics.drawImage(ResourceManager.getInstance().getImageUsingKey(tile.imageKey), tile.xCoordinate - this.xLevelOffset, tile.yCoordinate - this.yLevelOffset, scale, scale, null);
            }
        }
    }

    public Tile[][] getTiles() {

        return(this.tiles);
    }
}
