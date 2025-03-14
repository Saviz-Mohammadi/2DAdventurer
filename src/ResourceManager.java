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

            // IMAGES:
            BufferedImage image1 = ImageIO.read(new File("./resources/image_files/other/characters/player/player_IDLE_0.png"));
            BufferedImage image2 = ImageIO.read(new File("./resources/image_files/other/characters/player/player_IDLE_1.png"));
            BufferedImage image3 = ImageIO.read(new File("./resources/image_files/other/characters/player/player_MOVING_0.png"));
            BufferedImage image4 = ImageIO.read(new File("./resources/image_files/other/characters/player/player_MOVING_1.png"));

            this.imageMap.put("player_IDLE_0", image1);
            this.imageMap.put("player_IDLE_1", image2);
            this.imageMap.put("player_MOVING_0", image3);
            this.imageMap.put("player_MOVING_1", image4);
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
