import javax.swing.*;
import java.awt.*;

/**
 * This class provides an entry point for our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        game.init();
        game.startGameLoop();
    }
}
