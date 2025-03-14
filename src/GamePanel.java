import javax.swing.*;
import java.awt.*;

/**
 * This class provides a base for our game to draw components and perform logic.
 *
 * @author Saviz
 * @version 1.0
 */
public class GamePanel extends JPanel {

    // DEPENDENCIES:
    KeyboardInputs keyboardInputs = null;
    MouseInputs mouseInpus = null;
    Player palyer = null;

    public GamePanel() {

    }

    public Outcome<Void> init() {

        Outcome<Void> outcome = new Outcome<Void>();

        // SETTINGS:
        this.setPreferredSize(new Dimension(1280, 800));
        this.setDoubleBuffered(true);

        // INPUTS:
        this.keyboardInputs = new KeyboardInputs();
        addKeyListener(this.keyboardInputs);

        this.mouseInpus = new MouseInputs();
        addMouseListener(this.mouseInpus);
        addMouseMotionListener(this.mouseInpus);

        // PLAYER:
        this.player = new Player();
        player.init(this, 100, 100, 5);

        return(outcome);
    }

    public void update() {

        player.update();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        player.render(graphics);

//         graphics.drawImage(ResourceManager.getInstance().getImageUsingKey("up_1"), 0, 0, null);
    }

    // GETTERS
    public KeyboardInputs getKeyboardInputs() {

        return(this.keyboardInputs);
    }

    public MouseInputs getMouseInputs() {

        return(this.mouseInpus);
    }
}
