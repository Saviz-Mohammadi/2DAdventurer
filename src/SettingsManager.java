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

    // CONSTANTS:
    // NOTE(SAVIZ): Simply changing the settings here won’t be sufficient, as we also need to adjust the level image to reflect the correct ratios.
    public final static int TILE_DEFAULT_SIZE = 8;
    public final static int SCALE = 4;
    public final static int TILE_SCALED_SIZE = TILE_DEFAULT_SIZE * SCALE;
    // TODO(SAVIZ): We could have a piece of code in our 'Game' class that can determine this based on witdth and height:
    public final static int COLUMNS = 16;
    public final static int ROWS = 9;
    public final static int GAME_WIDTH = COLUMNS * TILE_SCALED_SIZE;
    public final static int GAME_HEIGHT = ROWS * TILE_SCALED_SIZE;

    // PROPERTIES:
    private int musicVolume = 50;
    private int sfxVolume = 50;

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

    public void increaseMusicVolume(int amount) {

        this.setMusicVolume(this.musicVolume + amount);
    }

    public void increaseSFXVolume(int amount) {

        this.setSFXVolume(this.sfxVolume + amount);
    }

    public void decreaseMusicVolume(int amount) {

        this.setMusicVolume(this.musicVolume - amount);
    }

    public void decreaseSFXVolume(int amount) {

        this.setSFXVolume(this.sfxVolume - amount);
    }

    // GETTERS:
    public int getMusicVolume() {

        return(this.musicVolume);
    }

    public int getSFXVolume() {

        return(this.sfxVolume);
    }

    // SETTERS:
    public void setMusicVolume(int newVolume) {

        if(this.musicVolume == newVolume) {

            return;
        }

        if(newVolume < 0) {

            this.musicVolume = 0;

            return;
        }

        if(newVolume > 100) {

            this.musicVolume = 100;

            return;
        }

        this.musicVolume = newVolume;
    }

    public void setSFXVolume(int newVolume) {

        if(this.sfxVolume == newVolume) {

            return;
        }

        if(newVolume < 0) {

            this.sfxVolume = 0;

            return;
        }

        if(newVolume > 100) {

            this.sfxVolume = 100;

            return;
        }

        this.sfxVolume = newVolume;
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
