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

        return(outcome);
    }

    public void update() {

        // Testing...
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        graphics.drawImage(ResourceManager.getInstance().getImageUsingKey("up_1"), 0, 0, null);
    }
}
