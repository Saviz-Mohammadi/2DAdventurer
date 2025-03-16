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

        super();
    }

    public void init(Game game) {

        // DEPENDENCIES:
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        this.game.getLevelManager().render(graphics);
        this.game.getPlayer().render(graphics);
    }
}
