import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.util.function.*;
import java.io.*;

public class MenuButton {

    // DEPENDENCIES:
    private Game game = null;

    // PROPERTIES:
    public boolean isClicked = false;
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private int width = 0;
    private int height = 0;
    private String imageKey = "";
    private Rectangle bounds = null;

    public MenuButton() {

        this.bounds = new Rectangle();
    }

    public void init(Game game, int xCoordinate, int yCoordinate, int width, int height, String imageKey) {

        // DEPENDENCIES:
        this.game = game;

        // PROPERTIES:
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
        this.imageKey = imageKey;
        this.bounds.setBounds(xCoordinate, yCoordinate, width, height);
    }

    public void update() {

        // NOTE(SAVIZ): Typically, an event system would handle this. However, since we're required to develop the game using a GUI framework like Swing, the circumstances are unusual. Given these constraints, this approach seems to be the best way to replicate that behavior:
        this.isClicked = false;

        this.isClicked = this.bounds.contains(this.game.getMouseInputs().xCoordinate, this.game.getMouseInputs().yCoordinate);
    }

    public void render(Graphics graphics) {

        graphics.drawImage(ResourceManager.getInstance().getImageUsingKey(this.imageKey), this.xCoordinate, this.yCoordinate, this.width, this.height, null);
    }
}
