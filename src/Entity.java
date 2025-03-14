import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;

public class Entity {

        // Properties:
        int worldXCoordinate = 0;
        int worldYCoordinate = 0;
        int movementSpeed = 4;
        HashMap<String, BufferedImage> spriteMap = new HashMap<>();
        Direction direction;
}
