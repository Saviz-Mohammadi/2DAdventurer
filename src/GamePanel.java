import javax.swing.*;
import java.awt.*;

/**
 * This class provides a base for our game to draw components.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class GamePanel extends JPanel {

    // DEPENDENCIES:
    Game game = null;

    public GamePanel() {

    }

    public void init(Game game) {

        // DEPENDENCIES:
        this.game = game;

        // SETTINGS:
        this.setPreferredSize(new Dimension(1280, 800));
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        this.game.getPlayer().render(graphics);
    }
}
