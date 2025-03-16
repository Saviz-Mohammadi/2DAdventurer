import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

/**
 * This class acts as a central unit for anything that has to do with resources.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class ResourceManager {

    // PROPERTIES:
    private HashMap<String, BufferedImage> imageMap = null;

    // NOTE (SAVIZ): Volatile ensures thread safety:
    private static volatile ResourceManager instance;

    private ResourceManager() {

        // IMAGES:
        this.imageMap = new HashMap<>();
    }

    public void init() {

        try {

            // LEVEL IMAGES:
            BufferedImage level_image_1 = ImageIO.read(new File("./resources/image_files/levels/level_0001.png"));

            this.imageMap.put("level_1", level_image_1);

            // TILE IMAGES:
            BufferedImage tile_image_1 = ImageIO.read(new File("./resources/image_files/tiles_transparent/tile_0017.png"));

            this.imageMap.put("tile_0017");

            // PLAYER IMAGES:
            BufferedImage player_image_1 = ImageIO.read(new File("./resources/image_files/tiles_transparent/tile_0090.png"));
            BufferedImage player_image_2 = ImageIO.read(new File("./resources/image_files/tiles_transparent/tile_0090.png"));
            BufferedImage player_image_3 = ImageIO.read(new File("./resources/image_files/tiles_transparent/tile_0091.png"));
            BufferedImage player_image_4 = ImageIO.read(new File("./resources/image_files/tiles_transparent/tile_0092.png"));

            this.imageMap.put("player_IDLE_0", player_image_1);
            this.imageMap.put("player_IDLE_1", player_image_2);
            this.imageMap.put("player_MOVING_0", player_image_3);
            this.imageMap.put("player_MOVING_1", player_image_4);
        }

        catch (IOException exception) {

            exception.printStackTrace();
        }
    }

    public static ResourceManager getInstance() {

        if (instance == null) {

            synchronized (ResourceManager.class) {

                if (instance == null) {

                    instance = new ResourceManager();

                }
            }
        }

        return (instance);
    }

    public BufferedImage getImageUsingKey(String key) {

        return(this.imageMap.get(key));
    }

    // For text files you can return 'File' type or just simply return the file path:
}
