import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

/**
 * This class acts as a central unit for anything that has to do with resources.
 *
 * @author Saviz
 * @version 1.0
 */
public class ResourceManager {

    // PROPERTIES:
    private HashMap<String, BufferedImage> imageMap = null;

    // Volatile ensures thread safety:
    private static volatile ResourceManager instance;

    private ResourceManager() {

        // IMAGES:
        this.imageMap = new HashMap<>();
    }

    public Outcome<Void> init() {

        Outcome<Void> outcome = new Outcome<Void>();

        try {

            // IMAGES:
            BufferedImage image1 = ImageIO.read(new File("./resources/Image_Files/Other/Characters/Player/Player_01.png"));

            this.imageMap.put("up_1", image1);

            return(outcome);
        }

        catch (IOException exception) {

            outcome.isSuccessful = false;
            outcome.message = exception.getMessage();

            exception.printStackTrace();

            return(outcome);
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
