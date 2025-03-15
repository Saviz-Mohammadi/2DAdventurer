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
    public final static int TILE_DEFAULT_SIZE = 16;
    public final static int SCALE = 1;
    public final static int TILE_SCALED_SIZE = TILE_DEFAULT_SIZE * SCALE;
    public final static int COLUMNS = 80;
    public final static int ROWS = 45;

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

    // TODO(SAVIZ): I need to create observer pattern here using 'Flow' API.
}
