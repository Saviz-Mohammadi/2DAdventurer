import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

/**
 * This class acts as a central unit for anything that has to do with settings.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class SettingsManager {

    // PROPERTIES:
    // NOTE(SAVIZ): Simply changing the settings here won’t be sufficient, as we also need to adjust the level image to reflect the correct ratios.
    public final static int TILE_DEFAULT_SIZE = 16;
    public final static int SCALE = 1;
    public final static int TILE_SCALED_SIZE = TILE_DEFAULT_SIZE * SCALE;
    public final static int COLUMNS = 80;
    public final static int ROWS = 45;
    public final static int GAME_WIDTH = COLUMNS * TILE_SCALED_SIZE;
    public final static int GAME_HEIGHT = ROWS * TILE_SCALED_SIZE;

    // NOTE (SAVIZ): Volatile ensures thread safety:
    private static volatile SettingsManager instance;

    private SettingsManager() {

    }

    public void init() {

    }

    public static SettingsManager getInstance() {

        if (instance == null) {

            synchronized (SettingsManager.class) {

                if (instance == null) {

                    instance = new SettingsManager();
                }
            }
        }

        return (instance);
    }

    // TODO(SAVIZ): I need to create observer pattern with these:

//     import java.util.List;
//     import java.util.function.Function;
//
//     List<Function<Integer, String>> functions;
//     for (Function<Integer, String> func : functions) {
//             System.out.println(func.apply(input));
//         }

    // I also need to make a register that takes a funcciotn and registeres it.
    // An unregister
    // A setter and invoker

    // To differentiate between what we are registering for, we can make an Enum right in here and when calling register or unregister we select what are targetting for.
}
