import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class Tile {

    // PROPERTIES:
    public int xCoordinate;
    public int yCoordinate;
    public String imageKey;
    public boolean isSolid = false;

    // TODO(SAVIZ): We could have 2 more properties: 1- boolean is teleporter 2- integere number (where does it teleport to (selects next level)).
    // Also make an init to init default values.

    public Tile() {

    }
}
