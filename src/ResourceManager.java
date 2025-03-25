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

            // UI:
            BufferedImage ui_image_0000 = ImageIO.read(new File("./resources/image_files/ui/buttons/apply.png"));
            BufferedImage ui_image_0001 = ImageIO.read(new File("./resources/image_files/ui/buttons/cancel.png"));
            BufferedImage ui_image_0002 = ImageIO.read(new File("./resources/image_files/ui/buttons/ok.png"));
            BufferedImage ui_image_0003 = ImageIO.read(new File("./resources/image_files/ui/buttons/play.png"));
            BufferedImage ui_image_0004 = ImageIO.read(new File("./resources/image_files/ui/buttons/back.png"));
            BufferedImage ui_image_0005 = ImageIO.read(new File("./resources/image_files/ui/buttons/main_menu.png"));
            BufferedImage ui_image_0006 = ImageIO.read(new File("./resources/image_files/ui/buttons/options.png"));
            BufferedImage ui_image_0007 = ImageIO.read(new File("./resources/image_files/ui/buttons/quit.png"));
            BufferedImage ui_image_0008 = ImageIO.read(new File("./resources/image_files/ui/buttons/increase.png"));
            BufferedImage ui_image_0009 = ImageIO.read(new File("./resources/image_files/ui/buttons/decrease.png"));
            BufferedImage ui_image_0010 = ImageIO.read(new File("./resources/image_files/ui/backgrounds/main_menu.png"));
            BufferedImage ui_image_0011 = ImageIO.read(new File("./resources/image_files/ui/backgrounds/options_menu.png"));

            this.imageMap.put("ui_button_apply", ui_image_0000);
            this.imageMap.put("ui_button_cancel", ui_image_0001);
            this.imageMap.put("ui_button_ok", ui_image_0002);
            this.imageMap.put("ui_button_play", ui_image_0003);
            this.imageMap.put("ui_button_back", ui_image_0004);
            this.imageMap.put("ui_button_main_menu", ui_image_0005);
            this.imageMap.put("ui_button_options", ui_image_0006);
            this.imageMap.put("ui_button_quit", ui_image_0007);
            this.imageMap.put("ui_button_increase", ui_image_0008);
            this.imageMap.put("ui_button_decrease", ui_image_0009);
            this.imageMap.put("ui_background_main_menu", ui_image_0010);
            this.imageMap.put("ui_background_options_menu", ui_image_0011);

            // LEVEL IMAGES:
            BufferedImage level_image_0000 = ImageIO.read(new File("./resources/image_files/levels/complete/level_0001.png"));

            this.imageMap.put("level_0000", level_image_0000);

            // TILE IMAGES:
            BufferedImage tile_image_0000 = ImageIO.read(new File("./resources/image_files/tiles_default/tile_0000.png")); // Air
            BufferedImage tile_image_0001 = ImageIO.read(new File("./resources/image_files/tiles_default/tile_0034.png")); // Ground

            this.imageMap.put("tile_0000", tile_image_0000);
            this.imageMap.put("tile_0034", tile_image_0001);

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
