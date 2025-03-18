import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class MainMenu {

    // DEPENDENCIES:
    Game game = null;

    // PROPERTIES:

    public MainMenu() {

    }

    public void init(Game game) {

        this.game = game;
    }

    public void update() {

    }

    public void render(Graphics graphics) {

        graphics.setColor(Color.ORANGE);
        graphics.drawString("MENU", SettingsManager.getInstance().GAME_WIDTH / 2, SettingsManager.getInstance().GAME_HEIGHT / 2);
    }
}
